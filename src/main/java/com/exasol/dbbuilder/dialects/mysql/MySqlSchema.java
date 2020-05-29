package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.AbstractSchema;
import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;

/**
 * MySQL database schema.
 */
public class MySqlSchema extends AbstractSchema {
    private final MySqlImmediateDatabaseObjectWriter writer;

    /**
     * Create a new database schema.
     *
     * @param writer database object writer
     * @param name   name of the database schema
     */
    public MySqlSchema(final MySqlImmediateDatabaseObjectWriter writer, final String name) {
        super(name);
        this.writer = writer;
        this.writer.write(this);
    }

    @Override
    public DatabaseObjectWriter getWriter() {
        return this.writer;
    }
}