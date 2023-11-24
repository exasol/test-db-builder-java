package com.exasol.dbbuilder.dialects.exasol;

import java.sql.Connection;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.*;

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
        this(new ExasolImmediateDatabaseObjectWriter(connection, configuration));
    }

    ExasolObjectFactory(final ExasolImmediateDatabaseObjectWriter writer) {
        super(writer);
        this.writer = writer;
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
        return this.createConnectionDefinition(name, to, null, null);
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
        final ConnectionDefinition connectionDefinition = new ConnectionDefinition(this.writer,
                ExasolIdentifier.of(name), to, userName, password);
        this.writer.write(connectionDefinition);
        return connectionDefinition;
    }

    @Override
    public ExasolSchema createSchema(final String name) {
        final ExasolSchema schema = new ExasolSchema(this.writer, ExasolIdentifier.of(name));
        this.writer.write(schema);
        return schema;
    }

    @Override
    protected User createNewUser(final String name) {
        return new ExasolUser(this.writer, ExasolIdentifier.of(name));
    }

    @Override
    protected User createNewUser(final String name, final String password) {
        return new ExasolUser(this.writer, ExasolIdentifier.of(name), password);
    }

    @Override
    @SuppressWarnings("java:S2095") // Not using try-with-resources, the caller is responsible for closing
    public User createLoginUser(final String name) {
        return createUser(name).grant(ExasolGlobalPrivilege.CREATE_SESSION);
    }

    @Override
    @SuppressWarnings("java:S2095") // Not using try-with-resources, the caller is responsible for closing
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
