package com.exasol.dbbuilder.dialects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

/**
 * This class contains common logic for writers that persist database objects.
 */
public abstract class AbstractImmediateDatabaseObjectWriter implements DatabaseObjectWriter {
    protected final Connection connection;

    /**
     * Create a new instance of an {@link AbstractImmediateDatabaseObjectWriter}.
     *
     * @param connection JDBC connection
     */
    public AbstractImmediateDatabaseObjectWriter(final Connection connection) {
        this.connection = connection;
    }

    protected void writeToObject(final DatabaseObject object, final String sql, final Object... parameters) {
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
    public void write(final Schema schema) {
        writeToObject(schema, "CREATE SCHEMA " + schema.getFullyQualifiedName());
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
            builder.append(getQuotedColumnName(column.getName())) //
                    .append(" ") //
                    .append(column.getType());
        }
        builder.append(")");
        writeToObject(table, builder.toString());
    }

    /**
     * Get a quoted column name.
     * 
     * @param columnName name of a column
     * @return quoted column name
     */
    protected abstract String getQuotedColumnName(String columnName);

    @Override
    public void write(final Table table, final Object... values) {
        final String valuePlaceholders = "?" + ", ?".repeat(table.getColumnCount() - 1);
        final String sql = "INSERT INTO " + table.getFullyQualifiedName() + " VALUES(" + valuePlaceholders + ")";
        writeToObject(table, sql, values);
    }

    protected String createCommaSeparatedSystemPrivilegeList(final GlobalPrivilege[] privileges) {
        final StringBuilder builder = new StringBuilder();
        int i = 0;
        for (final GlobalPrivilege privilege : privileges) {
            if (i++ > 0) {
                builder.append(", ");
            }
            builder.append(privilege.renderedName());
        }
        return builder.toString();
    }

    @Override
    public void write(final User user, final DatabaseObject object, final ObjectPrivilege... privileges) {
        writeToObject(user, "GRANT " + createCommaSeparatedObjectPrivilegeList(privileges) //
                + " ON " + object.getFullyQualifiedName() //
                + " TO " + user.getFullyQualifiedName());
    }

    protected String createCommaSeparatedObjectPrivilegeList(final ObjectPrivilege[] privileges) {
        final StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (final ObjectPrivilege privilege : privileges) {
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append(privilege.renderedName());
        }
        return builder.toString();
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