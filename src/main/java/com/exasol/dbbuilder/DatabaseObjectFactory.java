package com.exasol.dbbuilder;

import java.sql.Connection;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;
import com.exasol.dbbuilder.objectwriter.ImmediateDatabaseObjectWriter;

/**
 * Factory for top-level a database schema.
 */
public final class DatabaseObjectFactory {
    private final DatabaseObjectWriter writer;

    /**
     * Create a new {@link DatabaseObjectFactory} instance.
     *
     * @param connection JDBC connection
     */
    public DatabaseObjectFactory(final Connection connection) {
        this.writer = new ImmediateDatabaseObjectWriter(connection);
    }

    /**
     * Create a new database schema.
     *
     * @param name name of the schema
     * @return new {@link Schema} instance.
     */
    public Schema createSchema(final String name) {
        return new Schema(this.writer, name);
    }

    /**
     * Create a new database user.
     *
     * @param name user name
     * @return new {@link User} instance
     */
    public User createUser(final String name) {
        return new User(this.writer, name);
    }

    /**
     * Create a connection without credentials.
     *
     * @param name name of the connection
     * @param to   target the connection points to
     * @return new {@link User} instance
     */
    public ConnectionDefinition createConnectionDefinition(final String name, final String to) {
        return new ConnectionDefinition(this.writer, name, to);
    }

    /**
     * Create a connection without credentials.
     *
     * @param name     name of the connection
     * @param to       target the connection points to
     * @param userName user as which to connect
     * @param password password or password-like credential
     * @return new {@link User} instance
     */
    public ConnectionDefinition createConnectionDefinition(final String name, final String to, final String userName,
            final String password) {
        return new ConnectionDefinition(this.writer, name, to, userName, password);
    }
}