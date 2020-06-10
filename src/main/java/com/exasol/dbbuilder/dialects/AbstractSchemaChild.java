package com.exasol.dbbuilder.dialects;

import com.exasol.db.Identifier;

/**
 * Base class for all database objects in the scope of a database schema.
 */
public abstract class AbstractSchemaChild extends AbstractDatabaseObject {
    protected final Schema parentSchema;

    /**
     * Create a new child object of a database schema.
     *
     * @param parentSchema parent schema
     * @param name         name of the database object
     * @param owned        {@code true} if the object is owned by the TDDB, {@code false} if the TDDB attached to a
     *                     database object that already existed
     */
    public AbstractSchemaChild(final Schema parentSchema, final Identifier name, final boolean owned) {
        super(name, owned);
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