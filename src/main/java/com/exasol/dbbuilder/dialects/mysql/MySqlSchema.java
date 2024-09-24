package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.db.Identifier;
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
    MySqlSchema(final MySqlImmediateDatabaseObjectWriter writer, final MySQLIdentifier name) {
        super(name);
        this.writer = writer;
    }

    @Override
    public DatabaseObjectWriter getWriter() {
        verifyNotDeleted();
        return this.writer;
    }

    @Override
    protected Identifier getIdentifier(final String name) {
        return MySQLIdentifier.of(name);
    }

    @Override
    public MySqlTable.Builder createTableBuilder(final String name) {
        return MySqlTable.builder(getWriter(), this, getIdentifier(name));
    }
}
