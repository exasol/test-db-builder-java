package com.exasol.dbbuilder.dialects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;
import java.util.stream.Stream;

import com.exasol.errorreporting.ExaError;

/**
 * This class contains common logic for writers that persist database objects.
 */
public abstract class AbstractImmediateDatabaseObjectWriter implements DatabaseObjectWriter {
    /** Connection */
    protected final Connection connection;

    /**
     * Create a new instance of an {@link AbstractImmediateDatabaseObjectWriter}.
     *
     * @param connection JDBC connection
     */
    protected AbstractImmediateDatabaseObjectWriter(final Connection connection) {
        this.connection = connection;
    }

    /**
     * Write a given object to the database.
     *
     * @param object     the object to write
     * @param sql        the SQL statement
     * @param parameters the parameters
     */
    protected void writeToObject(final DatabaseObject object, final String sql, final Object... parameters) {
        try (final PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; ++i) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            preparedStatement.execute();
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(object, ExaError.messageBuilder("E-TDBJ-13")
                    .message("Failed to write to object: {{sql}}. Cause: {{cause}}", sql, exception.getMessage())
                    .toString(), exception);
        }
    }

    // [impl->dsn~creating-schemas~1]
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

    @Override
    public void truncate(final Table table) {
        writeToObject(table, "TRUNCATE TABLE " + table.getFullyQualifiedName());
    }

    /**
     * Get a quoted column name.
     * 
     * @param columnName name of a column
     * @return quoted column name
     */
    protected abstract String getQuotedColumnName(String columnName);

    @Override
    @SuppressWarnings("try") // autoCommit never referenced in try block by intention
    public void write(final Table table, final Stream<List<Object>> rows) {
        final String valuePlaceholders = "?" + ", ?".repeat(table.getColumnCount() - 1);
        final String sql = "INSERT INTO " + table.getFullyQualifiedName() + " VALUES(" + valuePlaceholders + ")";
        try (AutoCommit autoCommit = AutoCommit.tryDeactivate(connection);
                final PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
            rows.forEach(row -> addBatch(table, preparedStatement, row));
            preparedStatement.executeBatch();
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(table,
                    ExaError.messageBuilder("E-TDBJ-2")
                            .message("Failed to create prepared statement {{statement}} for insert.", sql).toString(),
                    exception);
        }
    }

    private void addBatch(final Table table, final PreparedStatement preparedStatement, final List<Object> row) {
        try {
            for (int i = 0; i < row.size(); ++i) {
                preparedStatement.setObject(i + 1, row.get(i));
            }
            preparedStatement.addBatch();
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(table,
                    ExaError.messageBuilder("E-TDBJ-35").message("Failed to row to batch").toString(), exception);
        }
    }

    /**
     * Join a given array for {@link GlobalPrivilege}s with {@code ,}.
     * 
     * @param privileges privileges
     * @return comma separated string
     */
    protected String createCommaSeparatedSystemPrivilegeList(final GlobalPrivilege[] privileges) {
        final StringBuilder builder = new StringBuilder();
        int i = 0;
        for (final GlobalPrivilege privilege : privileges) {
            if (i++ > 0) {
                builder.append(", ");
            }
            builder.append(privilege.getSqlName());
        }
        return builder.toString();
    }

    @Override
    public void write(final User user, final DatabaseObject object, final ObjectPrivilege... privileges) {
        writeToObject(user, "GRANT " + createCommaSeparatedObjectPrivilegeList(privileges) //
                + " ON " + object.getFullyQualifiedName() //
                + " TO " + user.getFullyQualifiedName());
    }

    /**
     * Join the given {@link ObjectPrivilege}s with a {@code ,}.
     * 
     * @param privileges privileges to join
     * @return comma separated string
     */
    protected String createCommaSeparatedObjectPrivilegeList(final ObjectPrivilege[] privileges) {
        final StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (final ObjectPrivilege privilege : privileges) {
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append(privilege.getSqlName());
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
                throw new DatabaseObjectException(
                        ExaError.messageBuilder("E-TDBJ-14")
                                .message("Unable to execute SQL from file: {{sqlFile}}", sqlFile).toString(),
                        exception);
            }
        }
    }

    @Override
    // [impl->dsn~dropping-tables~1]
    public void drop(final Table table) {
        writeToObject(table, "DROP TABLE " + table.getFullyQualifiedName());
    }

    @Override
    // [impl->dsn~dropping-users~1]
    public void drop(final User user) {
        writeToObject(user, "DROP USER " + user.getFullyQualifiedName());
    }
}
