package com.exasol.dbbuilder.dialects.postgres;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.*;

/**
 * PostgreSQL {@link Schema}.
 */
public class PostgreSqlSchema extends AbstractSchema {
    private final PostgreSqlImmediateDatabaseObjectWriter writer;

    /**
     * Create a new instance of {@link PostgreSqlSchema}.
     * 
     * @param writer object writer
     * @param name   name of the schema
     */
    public PostgreSqlSchema(final PostgreSqlImmediateDatabaseObjectWriter writer, final PostgreSqlIdentifier name) {
        super(name);
        this.writer = writer;
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        verifyNotDeleted();
        return this.writer;
    }

    @Override
    protected Identifier getIdentifier(final String name) {
        return PostgreSqlIdentifier.of(name);
    }
}
