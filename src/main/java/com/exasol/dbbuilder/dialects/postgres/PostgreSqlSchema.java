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
    public PostgreSqlSchema(final PostgreSqlImmediateDatabaseObjectWriter writer, final String name) {
        super(PostgreSqlIdentifier.of(name));
        this.writer = writer;
        this.writer.write(this);
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }

    @Override
    protected Identifier getIdentifier(final String name) {
        return PostgreSqlIdentifier.of(name);
    }
}
