package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.dbbuilder.dialects.*;

import java.nio.file.Path;
import java.sql.Connection;

/**
 * Factory for a top-level database object.
 */
public final class ExasolObjectFactory implements DatabaseObjectFactory {
    private final ExasolImmediateDatabaseObjectWriter writer;

    /**
     * Create a new {@link ExasolObjectFactory} instance.
     *
     * @param connection JDBC connection
     */
    public ExasolObjectFactory(final Connection connection) {
        this.writer = new ExasolImmediateDatabaseObjectWriter(connection);
    }

    /**
     * Create a connection without credentials.
     *
     * @param name name of the connection
     * @param to   target the connection points to
     * @return new {@link ConnectionDefinition} instance
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
     * @return new {@link ConnectionDefinition} instance
     */
    public ConnectionDefinition createConnectionDefinition(final String name, final String to, final String userName,
                                                           final String password) {
        return new ConnectionDefinition(this.writer, name, to, userName, password);
    }

    /**
     * Create a new database schema.
     *
     * @param name name of the schema
     * @return new {@link ExasolSchema} instance
     */
    public ExasolSchema createSchema(final String name) {
        return new ExasolSchema(this.writer, name);
    }

    @Override
    // [impl->dsn~creating-database-users~1]
    public User createUser(final String name) {
        return new ExasolUser(this.writer, name);
    }

    @Override
    public User createUser(final String name, final String password) {
        return new ExasolUser(this.writer, name, password);
    }

    @Override
    public User createLoginUser(final String name) {
        return createUser(name).grant(ExasolGlobalPrivilege.CREATE_SESSION);
    }

    @Override
    public User createLoginUser(final String name, final String password) {
        return createUser(name, password).grant(ExasolGlobalPrivilege.CREATE_SESSION);
    }

    /**
     * Create a builder for a Virtual Schema.
     *
     * @param name name of the Virtual Schema
     * @return builder
     */
    public VirtualSchema.Builder createVirtualSchemaBuilder(final String name) {
        return VirtualSchema.builder(this.writer, name);
    }

    @Override
    // [impl->dsn~creating-objects-through-sql-files~1]
    public void executeSqlFile(final Path... sqlFiles) {
        this.writer.executeSqlFile(sqlFiles);
    }
}