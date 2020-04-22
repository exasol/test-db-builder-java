package com.exasol.dbbuilder;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

/**
 * Base class for database objects.
 */
public abstract class AbstractDatabaseObject implements DatabaseObject {
    protected DatabaseObjectWriter writer;
    protected String name;

    /**
     * Create a database object.
     *
     * @param writer writer that makes the object persistent.
     * @param name   name of the database object
     */
    public AbstractDatabaseObject(final DatabaseObjectWriter writer, final String name) {
        this.writer = writer;
        this.name = name;
    }

    /**
     * Get the name of the database object.
     *
     * @return name of the schema
     */
    @Override
    public String getName() {
        return this.name;
    }
}