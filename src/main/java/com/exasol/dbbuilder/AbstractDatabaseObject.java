package com.exasol.dbbuilder;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

/**
 * Base class for database objects.
 */
public abstract class AbstractDatabaseObject implements DatabaseObject {
    protected DatabaseObjectWriter writer;
    protected String name;
    protected final boolean attached;

    /**
     * Create a database object.
     *
     * @param writer   writer that makes the object persistent.
     * @param name     name of the database object
     * @param attached {@code true} if the object is a control object for a previously existing database object
     */
    public AbstractDatabaseObject(final DatabaseObjectWriter writer, final String name, final boolean attached) {
        this.writer = writer;
        this.name = name;
        this.attached = attached;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getFullyQualifiedName() {
        if (hasParent()) {
            return getParent().getFullyQualifiedName() + "." + quote(this.name);
        } else {
            return quote(this.name);
        }
    }

    private String quote(final String name) {
        return "\"" + name.replaceAll("\"", "\"\"") + "\"";
    }

    @Override
    public boolean isAttached() {
        return this.attached;
    }
}