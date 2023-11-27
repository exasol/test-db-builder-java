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
        super(writer);
        this.writer = writer;
    }

    @Override
    public User createUser(final String name, final String password) {
        return writeUser(new PostgreSqlUser(this.writer, PostgreSqlIdentifier.of(name), password));
    }

    @Override
    public Schema createSchema(final String name) {
        return writeSchema(new PostgreSqlSchema(this.writer, PostgreSqlIdentifier.of(name)));
    }
}
