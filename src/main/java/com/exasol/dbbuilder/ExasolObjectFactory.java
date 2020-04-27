package com.exasol.dbbuilder;

import java.sql.Connection;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;
import com.exasol.dbbuilder.objectwriter.ImmediateDatabaseObjectWriter;

/**
 * Factory for top-level a database schema.
 */
public final class ExasolObjectFactory implements DatabaseObjectFactory {
    private final DatabaseObjectWriter writer;

    /**
     * Create a new {@link ExasolObjectFactory} instance.
     *
     * @param connection JDBC connection
     */
    public ExasolObjectFactory(final Connection connection) {
        this.writer = new ImmediateDatabaseObjectWriter(connection);
    }

    @Override
    public ConnectionDefinition createConnectionDefinition(final String name, final String to) {
        return new ConnectionDefinition(this.writer, name, to);
    }

    @Override
    public ConnectionDefinition createConnectionDefinition(final String name, final String to, final String userName,
            final String password) {
        return new ConnectionDefinition(this.writer, name, to, userName, password);
    }

    @Override
    public Schema createSchema(final String name) {
        return new Schema(this.writer, name);
    }

    @Override
    public User createUser(final String name) {
        return new User(this.writer, name);
    }

    @Override
    public VirtualSchema.Builder createVirtualSchemaBuilder(final String name) {
        return VirtualSchema.builder(this.writer, name);
    }
}