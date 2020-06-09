package com.exasol.dbbuilder.dialects.exasol;

import java.sql.Connection;

import com.exasol.dbbuilder.dialects.*;

/**
 * Factory for Exasol top-level database objects.
 */
public final class ExasolObjectFactory extends AbstractObjectFactory {
    private final ExasolImmediateDatabaseObjectWriter writer;
    private final QuoteApplier quoteApplier;

    /**
     * Create a new {@link ExasolObjectFactory} instance.
     *
     * @param connection JDBC connection
     */
    public ExasolObjectFactory(final Connection connection) {
        this.writer = new ExasolImmediateDatabaseObjectWriter(connection);
        this.quoteApplier = new ExasolQuoteApplier();
    }

    /**
     * Create a connection without credentials.
     *
     * @param name name of the connection
     * @param to   target the connection points to
     * @return new {@link ConnectionDefinition} instance
     */
    public ConnectionDefinition createConnectionDefinition(final String name, final String to) {
        return new ConnectionDefinition(this.writer, this.quoteApplier, name, to);
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
        return new ConnectionDefinition(this.writer, this.quoteApplier, name, to, userName, password);
    }

    @Override
    public ExasolSchema createSchema(final String name) {
        return new ExasolSchema(this.writer, this.quoteApplier, name);
    }

    @Override
    // [impl->dsn~creating-database-users~1]
    public User createUser(final String name) {
        return new ExasolUser(this.writer, this.quoteApplier, name);
    }

    @Override
    public User createUser(final String name, final String password) {
        return new ExasolUser(this.writer, this.quoteApplier, name, password);
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
        return VirtualSchema.builder(this.writer, this.quoteApplier, name);
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }
}