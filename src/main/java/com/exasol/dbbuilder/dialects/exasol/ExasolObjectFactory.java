package com.exasol.dbbuilder.dialects.exasol;

import java.sql.Connection;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.AbstractObjectFactory;
import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;
import com.exasol.dbbuilder.dialects.User;

/**
 * Factory for Exasol top-level database objects.
 */
// [impl->dsn~exasol-object-factory~1]
public final class ExasolObjectFactory extends AbstractObjectFactory {
    private final ExasolImmediateDatabaseObjectWriter writer;

    /**
     * Create a new {@link ExasolObjectFactory} instance without further configuration.
     *
     * @param connection JDBC connection
     */
    public ExasolObjectFactory(final Connection connection) {
        this(connection, ExasolObjectConfiguration.builder().build());
    }

    /**
     * Create a new {@link ExasolObjectFactory} instance.
     *
     * @param connection    JDBC connection
     * @param configuration configuration for building Exasol objects
     */
    public ExasolObjectFactory(final Connection connection, final ExasolObjectConfiguration configuration) {
        this.writer = new ExasolImmediateDatabaseObjectWriter(connection, configuration);
    }

    /**
     * Create a connection without credentials.
     *
     * @param name name of the connection
     * @param to   target the connection points to
     * @return new {@link ConnectionDefinition} instance
     */
    // [impl->dsn~creating-connections~1]
    public ConnectionDefinition createConnectionDefinition(final String name, final String to) {
        return new ConnectionDefinition(this.writer, ExasolIdentifier.of(name), to);
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
        return new ConnectionDefinition(this.writer, ExasolIdentifier.of(name), to, userName, password);
    }

    @Override
    public ExasolSchema createSchema(final String name) {
        return new ExasolSchema(this.writer, ExasolIdentifier.of(name));
    }

    @Override
    public User createUser(final String name) {
        return new ExasolUser(this.writer, ExasolIdentifier.of(name));
    }

    @Override
    public User createUser(final String name, final String password) {
        return new ExasolUser(this.writer, ExasolIdentifier.of(name), password);
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
        return VirtualSchema.builder(this.writer, ExasolIdentifier.of(name));
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }
}