package com.exasol.dbbuilder;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

/**
 * Base class for all database objects in the scope of a database schema.
 */
public abstract class AbstractSchemaChild extends AbstractDatabaseObject {
    protected final Schema parentSchema;

    /**
     * Create a new child object of a database schema.
     * 
     * @param writer       database object writer to use
     * @param parentSchema parent schema
     * @param name         name of the datbase object
     */
    public AbstractSchemaChild(final DatabaseObjectWriter writer, final Schema parentSchema, final String name) {
        super(writer, name);
        this.parentSchema = parentSchema;
    }

    @Override
    public boolean hasParent() {
        return true;
    }

    @Override
    public DatabaseObject getParent() {
        return this.parentSchema;
    }
}