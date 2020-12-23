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
        this.writer = new PostgreSqlImmediateDatabaseObjectWriter(connectionToPostgres);
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }

    @Override
    public User createUser(final String name) {
        return new PostgreSqlUser(this.writer, PostgreSqlIdentifier.of(name));
    }

    @Override
    public User createUser(final String name, final String password) {
        return new PostgreSqlUser(this.writer, PostgreSqlIdentifier.of(name), password);
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
        return new PostgreSqlSchema(this.writer, name);
    }
}
