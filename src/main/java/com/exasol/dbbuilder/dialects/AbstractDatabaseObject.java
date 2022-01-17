package com.exasol.dbbuilder.dialects;

import com.exasol.db.Identifier;

/**
 * Base class for database objects.
 * <p>
 * Database objects in integration tests are in most cases owned by the TDDB. This means the TDDB created them and is
 * also responsible for cleaning them up.
 * </p>
 * <p>
 * On the other hand the TDDB can also attach to objects that already exist in the database. In that case the TDDB has a
 * control object, but does not own the database object.
 * </p>
 */
public abstract class AbstractDatabaseObject implements DatabaseObject {
    /** Is this object owned? */
    protected final boolean owned;
    /** Identifier */
    protected Identifier name;

    /**
     * Create a database object.
     *
     * @param name  name of the database object
     * @param owned {@code true} if the object is owned by the TDDB, {@code false} if the TDDB attached to a database
     *              object that already existed
     */
    public AbstractDatabaseObject(final Identifier name, final boolean owned) {
        this.name = name;
        this.owned = owned;
    }

    @Override
    public String getName() {
        return this.name.toString();
    }

    @Override
    public String getFullyQualifiedName() {
        if (hasParent()) {
            return getParent().getFullyQualifiedName() + "." + this.name.quote();
        } else {
            return this.name.quote();
        }
    }

    @Override
    public boolean isOwned() {
        return this.owned;
    }
}