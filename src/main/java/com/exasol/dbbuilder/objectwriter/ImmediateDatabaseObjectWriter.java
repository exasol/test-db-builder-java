package com.exasol.dbbuilder.objectwriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.exasol.dbbuilder.AbstractDatabaseObject;
import com.exasol.dbbuilder.AdapterScript;
import com.exasol.dbbuilder.Column;
import com.exasol.dbbuilder.ConnectionDefinition;
import com.exasol.dbbuilder.DatabaseObject;
import com.exasol.dbbuilder.DatabaseObjectException;
import com.exasol.dbbuilder.ObjectPrivilege;
import com.exasol.dbbuilder.Schema;
import com.exasol.dbbuilder.Script;
import com.exasol.dbbuilder.ScriptParameter;
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
     * Create a new instance of an {@link ImmediateDatabaseObjectWriter}.
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
            for (int i = 0; i < parameters.length; ++i) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            preparedStatement.execute();
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(object, "Failed to write to object: " + sql, exception);
        }
    }

    @Override
    public void write(final ConnectionDefinition definition) {
        if (definition.hasUserName()) {
            if (definition.hasPassword()) {
                writeToObject(definition,
                        "CREATE CONNECTION " + definition.getFullyQualifiedName() + " TO '" + definition.getTarget()
                                + "' USER '" + definition.getUserName() + ("' IDENTIFIED BY '")
                                + definition.getPassword() + "'");
            } else {
                throw new DatabaseObjectException(definition,
                        "Password missing when trying to write connection definition "
                                + definition.getFullyQualifiedName()
                                + ". Please always provide user name and password together or not at all.");
            }
        } else {
            if (definition.hasPassword()) {
                throw new DatabaseObjectException(definition,
                        "User name missing when trying to write connection definition "
                                + definition.getFullyQualifiedName()
                                + ". Please always provide user name and password together or not at all.");
            } else {
                writeToObject(definition, "CREATE CONNECTION " + definition.getFullyQualifiedName() + " TO '"
                        + definition.getTarget() + "'");
            }
        }
    }

    @Override
    public void write(final Schema schema) {
        writeToObject(schema, "CREATE SCHEMA " + schema.getFullyQualifiedName());
    }

    @Override
    public void write(final Script script) {
        final StringBuilder builder = new StringBuilder("CREATE SCRIPT " + script.getFullyQualifiedName());
        final List<ScriptParameter> parameters = script.getParameters();
        if (!parameters.isEmpty()) {
            builder.append(" (");
            int i = 0;
            for (final ScriptParameter parameter : parameters) {
                if (i++ > 0) {
                    builder.append(", ");
                }
                if (parameter.isArray()) {
                    builder.append("ARRAY ");
                }
                builder.append(parameter.getName());
            }
            builder.append(")");
        }
        if (script.returnsTable()) {
            builder.append(" RETURNS TABLE");
        }
        builder.append(" AS\n") //
                .append(script.getContent()) //
                .append("\n/");
        writeToObject(script, builder.toString());
    }

    @Override
    public void write(final Table table) {
        final StringBuilder builder = new StringBuilder("CREATE TABLE ");
        builder.append(table.getFullyQualifiedName()).append(" (");
        int i = 0;
        for (final Column column : table.getColumns()) {
            if (i++ > 0) {
                builder.append(", ");
            }
            builder.append("\"") //
                    .append(column.getName()) //
                    .append("\" ") //
                    .append(column.getType());
        }
        builder.append(")");
        writeToObject(table, builder.toString());
    }

    @Override
    public void write(final Table table, final Object... values) {
        final String valuePlaceholders = "?" + ", ?".repeat(table.getColumnCount() - 1);
        final String sql = "INSERT INTO " + table.getFullyQualifiedName() + " VALUES(" + valuePlaceholders + ")";
        writeToObject(table, sql, values);
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
        int i = 0;
        for (final SystemPrivilege privilege : privileges) {
            if (i++ > 0) {
                builder.append(", ");
            }
            builder.append(privilege);
        }
        return builder.toString();
    }

    @Override
    public void write(final User user, final DatabaseObject object, final ObjectPrivilege... privileges) {
        writeToObject(user, "GRANT " + createCommaSeparatedObjectPrivilegeList(privileges) //
                + " ON " + object.getFullyQualifiedName() //
                + " TO " + user.getFullyQualifiedName());
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
        final StringBuilder builder = new StringBuilder("CREATE VIRTUAL SCHEMA ")
                .append(virtualSchema.getFullyQualifiedName()) //
                .append("\nUSING ") //
                .append(virtualSchema.getAdapterScript().getFullyQualifiedName()) //
                .append(" WITH");
        for (final Map.Entry<String, String> property : virtualSchema.getProperties().entrySet()) {
            builder.append("\n  ");
            builder.append(property.getKey());
            builder.append(" = '");
            builder.append(property.getValue());
            builder.append("'");
        }
        writeToObject(virtualSchema, builder.toString());
    }

    @Override
    public int execute(final AbstractDatabaseObject script, final Object... parameterValues) {
        try (final Statement statement = this.connection.createStatement()) {
            statement.execute(getScriptExecutionSql(script, parameterValues));
            return statement.getUpdateCount();
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(script, "Failed to execute script " + script.getFullyQualifiedName(),
                    exception);
        }
    }

    private String getScriptExecutionSql(final AbstractDatabaseObject script, final Object[] parameterValues) {
        final StringBuilder builder = new StringBuilder("EXECUTE SCRIPT ");
        builder.append(script.getFullyQualifiedName());
        if (parameterValues.length > 0) {
            builder.append(" (");
            boolean first = true;
            for (final Object parameter : parameterValues) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                appendScriptParamterValue(builder, parameter);
            }
            builder.append(")");
        }
        return builder.toString();
    }

    private void appendScriptParamterValue(final StringBuilder builder, final Object parameter) {
        if (parameter instanceof Collection) {
            builder.append(" ARRAY(");
            int i = 0;
            for (final Object arrayItem : (Collection<?>) parameter) {
                if (i++ > 0) {
                    builder.append(", ");
                }
                appendScriptScalarParameter(builder, arrayItem);
            }
            builder.append(")");
        } else {
            appendScriptScalarParameter(builder, parameter);
        }
    }

    private void appendScriptScalarParameter(final StringBuilder builder, final Object value) {
        if (value instanceof String) {
            builder.append("'").append(value).append("'");
        } else {
            builder.append(value);
        }
    }

    @Override
    public List<List<Object>> executeQuery(final AbstractDatabaseObject script, final Object... parameterValues) {
        final String sql = getScriptExecutionSql(script, parameterValues);
        try (final Statement statement = this.connection.createStatement();
                final ResultSet result = statement.executeQuery(sql)) {
            final int columnCount = result.getMetaData().getColumnCount();
            final List<List<Object>> table = new ArrayList<>();
            while (result.next()) {
                final List<Object> row = new ArrayList<>(columnCount);
                for (int columnIndex = 1; columnIndex <= columnCount; ++columnIndex) {
                    final Object value = result.getObject(columnIndex);
                    row.add(value);
                }
                table.add(row);
            }
            return table;
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(script,
                    "Failed to execute script returning table" + script.getFullyQualifiedName(), exception);
        }
    }

    @Override
    public void executeSqlFile(final Path... sqlFiles) {
        for (final Path sqlFile : sqlFiles) {
            try (final Statement statement = this.connection.createStatement()) {
                final String sql = Files.readString(sqlFile);
                statement.execute(sql);
            } catch (final IOException | SQLException exception) {
                throw new DatabaseObjectException("Unable to execute SQL from file: " + sqlFile, exception);
            }
        }
    }
}