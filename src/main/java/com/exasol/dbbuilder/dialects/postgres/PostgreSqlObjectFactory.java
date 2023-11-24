package com.exasol.dbbuilder.dialects.postgres;

import java.sql.Connection;

import com.exasol.dbbuilder.dialects.*;

/**
 * PostgreSQL {@link DatabaseObjectFactory}.
 */
public class PostgreSqlObjectFactory extends AbstractObjectFactory {
    private final PostgreSqlImmediateDatabaseObjectWriter writer;

    /**
     * Create a new instance of {@link PostgreSqlObjectFactory}.
     * 
     * @param connectionToPostgres connection to the PostgreSQL database.
     */
    public PostgreSqlObjectFactory(final Connection connectionToPostgres) {
        this(new PostgreSqlImmediateDatabaseObjectWriter(connectionToPostgres));
    }

    PostgreSqlObjectFactory(final PostgreSqlImmediateDatabaseObjectWriter writer) {
        this.writer = writer;
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }

    @Override
    public User createUser(final String name) {
        return writeUser(new PostgreSqlUser(this.writer, PostgreSqlIdentifier.of(name)));
    }

    @Override
    public User createUser(final String name, final String password) {
        return writeUser(new PostgreSqlUser(this.writer, PostgreSqlIdentifier.of(name), password));
    }

    private User writeUser(final User user) {
        this.writer.write(user);
        return user;
    }

    @Override
    public User createLoginUser(final String name) {
        return createUser(name);
    }

    @Override
    public User createLoginUser(final String name, final String password) {
        return createUser(name, password);
    }

    @Override
    public Schema createSchema(final String name) {
        final PostgreSqlSchema schema = new PostgreSqlSchema(this.writer, PostgreSqlIdentifier.of(name));
        this.writer.write(schema);
        return schema;
    }
}
