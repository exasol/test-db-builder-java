package com.exasol.dbbuilder.objectwriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.exasol.dbbuilder.AdapterScript;
import com.exasol.dbbuilder.Column;
import com.exasol.dbbuilder.ConnectionDefinition;
import com.exasol.dbbuilder.DatabaseObject;
import com.exasol.dbbuilder.DatabaseObjectException;
import com.exasol.dbbuilder.ObjectPrivilege;
import com.exasol.dbbuilder.Schema;
import com.exasol.dbbuilder.SystemPrivilege;
import com.exasol.dbbuilder.Table;
import com.exasol.dbbuilder.User;
import com.exasol.dbbuilder.VirtualSchema;

/**
 * Database object writer that writes objects to the database immediately.
 */
public class ImmediateDatabaseObjectWriter implements DatabaseObjectWriter {
    private final Connection connection;

    /**
     * Create a new instance of a {@link ImmediateDatabaseObjectWriter}.
     *
     * @param connection JDBC connection
     */
    public ImmediateDatabaseObjectWriter(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void write(final AdapterScript adapterScript) {
        writeToObject(adapterScript, "CREATE " + adapterScript.getLanguage() + " ADAPTER SCRIPT "
                + adapterScript.getFullyQualifiedName() + " AS\n" + adapterScript.getContent() + "\n/");
    }

    private void writeToObject(final DatabaseObject object, final String sql, final Object... parameters) {
        try (final PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            for (int i = 1; i <= parameters.length; ++i) {
                preparedStatement.setObject(i, parameters[i]);
            }
            preparedStatement.execute();
        } catch (final SQLException exception) {
            throw new DatabaseObjectWriterException(object, sql, exception);
        }
    }

    @Override
    public void write(final ConnectionDefinition definition) {
        if (definition.hasUserName()) {
            if (definition.hasPassword()) {
                writeToObject(definition,
                        "CREATE CONNECTION " + definition.getFullyQualifiedName() + " TO '" + definition.getTo()
                                + "' USER '" + definition.getUserName() + ("' IDENTIFIED BY '")
                                + definition.getPassword() + "'");
            } else {
                throw new DatabaseObjectException(definition,
                        "Password missing when trying to write connection definition "
                                + definition.getFullyQualifiedName()
                                + ". Please alway provide user name and password together or not at all.");
            }
        } else {
            if (definition.hasPassword()) {
                throw new DatabaseObjectException(definition,
                        "User name missing when trying to write connection definition "
                                + definition.getFullyQualifiedName()
                                + ". Please alway provide user name and password together or not at all.");
            } else {
                writeToObject(definition,
                        "CREATE CONNECTION " + definition.getFullyQualifiedName() + " TO '" + definition.getTo() + "'");
            }
        }
    }

    @Override
    public void write(final Schema schema) {
        writeToObject(schema, "CREATE SCHEMA " + schema.getFullyQualifiedName());
    }

    @Override
    public void write(final Table table) {
        final StringBuilder sqlBuilder = new StringBuilder("CREATE TABLE " + table.getFullyQualifiedName() + " (");
        boolean first = true;
        for (final Column column : table.getColumns()) {
            if (first) {
                first = false;
            } else {
                sqlBuilder.append(", ");
            }
            sqlBuilder.append("\"");
            sqlBuilder.append(column.getName());
            sqlBuilder.append("\" ");
            sqlBuilder.append(column.getType());
        }
        sqlBuilder.append(")");
        writeToObject(table, sqlBuilder.toString());
    }

    @Override
    public void write(final Table table, final List<List<Object>> rows) {
        final String valuePlaceholders = "?" + ", ?".repeat(table.getColumnCount() - 1);
        try (final PreparedStatement insert = this.connection.prepareStatement(
                "INSERT INTO " + table.getFullyQualifiedName() + " VALUES(" + valuePlaceholders + ")");) {
            for (final List<Object> row : rows) {
                int columnNumber = 1;
                for (final Object value : row) {
                    insert.setObject(columnNumber, value);
                    ++columnNumber;
                }
                insert.execute();
            }
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(table, "Unable to insert rows into table.", exception);
        }
    }

    @Override
    public void write(final User user) {
        writeToObject(user,
                "CREATE USER " + user.getFullyQualifiedName() + " IDENTIFIED BY \"" + user.getPassword() + "\"");
    }

    @Override
    public void write(final User user, final SystemPrivilege... privileges) {
        writeToObject(user,
                "GRANT " + createCommaSeparatedSystemPrivilegeList(privileges) + " TO " + user.getFullyQualifiedName());
    }

    private String createCommaSeparatedSystemPrivilegeList(final SystemPrivilege[] privileges) {
        final StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (final SystemPrivilege privilege : privileges) {
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append(privilege);
        }
        return builder.toString();
    }

    @Override
    public void write(final User user, final DatabaseObject object, final ObjectPrivilege... privileges) {
        writeToObject(user, "GRANT " + createCommaSeparatedObjectPrivilegeList(privileges) //
                + " ON ? " //
                + " TO ?", //
                privileges, object.getFullyQualifiedName(), user.getFullyQualifiedName(), privileges);
    }

    private String createCommaSeparatedObjectPrivilegeList(final ObjectPrivilege[] privileges) {
        final StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (final ObjectPrivilege privilege : privileges) {
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append(privilege);
        }
        return builder.toString();
    }

    @Override
    public void write(final VirtualSchema virtualSchema) {
        final StringBuilder builder = new StringBuilder("CREATE VIRTUAL SCHEMA ");
        builder.append(virtualSchema.getFullyQualifiedName());
        builder.append(" USING ");
        builder.append(virtualSchema.getAdapterScript().getFullyQualifiedName());
        builder.append(" WITH\n");
        for (final Map.Entry<String, String> property : virtualSchema.getProperties().entrySet()) {
            builder.append(property.getKey());
            builder.append(" = '");
            builder.append(property.getValue());
            builder.append("'\n");
        }
        writeToObject(virtualSchema, builder.toString());
    }
}