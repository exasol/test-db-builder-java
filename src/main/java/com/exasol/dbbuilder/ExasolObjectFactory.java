package com.exasol.dbbuilder;

import java.nio.file.Path;
import java.sql.Connection;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;
import com.exasol.dbbuilder.objectwriter.ImmediateDatabaseObjectWriter;

/**
 * Factory for a top-level database object.
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
    public User createUser(final String name, final String password) {
        return new User(this.writer, name, password);
    }

    @Override
    public User createLoginUser(final String name) {
        return createUser(name).grant(SystemPrivilege.CREATE_SESSION);
    }

    @Override
    public User createLoginUser(final String name, final String password) {
        return createUser(name, password).grant(SystemPrivilege.CREATE_SESSION);
    }

    @Override
    public VirtualSchema.Builder createVirtualSchemaBuilder(final String name) {
        return VirtualSchema.builder(this.writer, name);
    }

    @Override
    // [impl->dsn~creating-objects-through-sql-files~1]
    public void executeSql(final Path scriptFile) {
        this.writer.executeSql(scriptFile);
    }
}
