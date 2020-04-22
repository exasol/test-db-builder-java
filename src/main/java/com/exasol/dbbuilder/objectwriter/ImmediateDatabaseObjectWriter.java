package com.exasol.dbbuilder.objectwriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.exasol.dbbuilder.Column;
import com.exasol.dbbuilder.DatabaseObject;
import com.exasol.dbbuilder.DatabaseObjectException;
import com.exasol.dbbuilder.ObjectPrivilege;
import com.exasol.dbbuilder.Schema;
import com.exasol.dbbuilder.SystemPrivilege;
import com.exasol.dbbuilder.Table;
import com.exasol.dbbuilder.User;

/**
 * Database object writer that writes objects to the database immediately.
 */
public class ImmediateDatabaseObjectWriter implements DatabaseObjectWriter {
    private final Connection connection;
    private final Statement statement;

    /**
     * Create a new instance of a {@link ImmediateDatabaseObjectWriter}.
     *
     * @param connection JDBC connection
     */
    public ImmediateDatabaseObjectWriter(final Connection connection) {
        this.connection = connection;
        try {
            this.statement = connection.createStatement();
        } catch (final SQLException exception) {
            throw new DatabaseObjectWriterException("Unable to create statement.", exception);
        }
    }

    @Override
    public void write(final Schema schema) {
        try {
            this.statement.execute("CREATE SCHEMA " + schema.getName());
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(schema, exception);
        }
    }

    @Override
    public void write(final User user) {
        try {
            this.statement
                    .execute("CREATE USER \"" + user.getName() + "\" IDENTIFIED BY \"" + user.getPassword() + "\"");
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(user, exception);
        }
    }

    @Override
    public void write(final Table table) {
        try {
            final StringBuilder sqlBuilder = new StringBuilder("CREATE TABLE \"");
            sqlBuilder.append(table.getFullyQualifiedName());
            sqlBuilder.append("\" (");
            for (final Column column : table.getColumns()) {
                sqlBuilder.append("\"");
                sqlBuilder.append(column.getName());
                sqlBuilder.append("\" ");
                sqlBuilder.append(column.getType());
            }
            sqlBuilder.append(")");
            this.statement.execute(sqlBuilder.toString());
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(table, exception);
        }
    }

    @Override
    public void write(final User user, final SystemPrivilege... privileges) {
        try {
            this.statement.execute("GRANT " + joinSystemPrivileges(privileges) + "\" TO \"" + user.getName() + "\"");
        } catch (final SQLException exception) {
            throw new DatabaseObjectException("Unable to assign system privileges to user.", user, exception);
        }
    }

    private String joinSystemPrivileges(final SystemPrivilege[] privileges) {
        return Arrays.asList(privileges) //
                .stream() //
                .map(SystemPrivilege::toString) //
                .collect(Collectors.joining(","));
    }

    @Override
    public void write(final User user, final DatabaseObject object, final ObjectPrivilege... privileges) {
        try {
            this.statement.execute("GRANT " + joinObjectPrivileges(privileges) + "\" ON \""
                    + object.getFullyQualifiedName() + "\" TO \"" + user.getName() + "\"");
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(
                    "Unable to assign privileges on object \"" + object.getFullyQualifiedName() + "\" to user.", user,
                    exception);
        }
    }

    private String joinObjectPrivileges(final ObjectPrivilege[] privileges) {
        return Arrays.asList(privileges) //
                .stream() //
                .map(ObjectPrivilege::toString) //
                .collect(Collectors.joining(","));
    }

    @Override
    public void write(final Table table, final List<List<Object>> rows) {
        final String valuePlaceholders = "?" + ", ?".repeat(table.getColumnCount() - 1);
        try (final PreparedStatement insert = this.connection
                .prepareStatement("INSERT INTO ? VALUES(" + valuePlaceholders + ")");) {
            for (final List<Object> row : rows) {
                insert.setString(1, table.getFullyQualifiedName());
                int columnNumber = 2;
                for (final Object value : row) {
                    insert.setObject(columnNumber, value);
                    ++columnNumber;
                }
                insert.execute();
            }
        } catch (final SQLException exception) {
            throw new DatabaseObjectException("Unable to insert rows into table.", table, exception);
        }
    }
}