package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.*;

/**
 * MySQL database schema.
 */
public class MySqlSchema extends AbstractSchema {
    private final MySqlImmediateDatabaseObjectWriter writer;

    /**
     * Create a new database schema.
     *
     * @param writer       database object writer
     * @param quoteApplier instance of {@link QuoteApplier}
     * @param name         name of the database schema
     */
    public MySqlSchema(final MySqlImmediateDatabaseObjectWriter writer, final QuoteApplier quoteApplier,
            final String name) {
        super(quoteApplier, name);
        this.writer = writer;
        this.writer.write(this);
    }

    @Override
    public DatabaseObjectWriter getWriter() {
        return this.writer;
    }
}