package com.exasol.dbbuilder.dialects;

import com.exasol.db.Identifier;

/**
 * Base class for database objects.
 * <p>
 * Database objects in integration tests are in most cases owned by the TDBJ. This means the TDBJ created them and is
 * also responsible for cleaning them up.
 * </p>
 * <p>
 * On the other hand the TDBJ can also attach to objects that already exist in the database. In that case the TDBJ has a
 * control object, but does not own the database object.
 * </p>
 */
public abstract class AbstractDatabaseObject implements DatabaseObject {
    /** Is this object owned? */
    protected final boolean owned;
    /** Identifier */
    protected Identifier name;
    /** Was this object deleted? This is set to {@code true} after {@link #drop()} was called. */
    private boolean deleted = false;

    /**
     * Create a database object.
     *
     * @param name  name of the database object
     * @param owned {@code true} if the object is owned by the TDBJ, {@code false} if the TDBJ attached to a database
     *              object that already existed
     */
    protected AbstractDatabaseObject(final Identifier name, final boolean owned) {
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
    public void drop() {
        verifyNotDeleted();
        this.dropInternally();
        this.markDeleted();
    }

    /**
     * Mark this object as deleted.
     */
    protected void markDeleted() {
        this.deleted = true;
    }

    /**
     * This is called by {@link #drop()} to actually execute the DROP statement.
     */
    protected abstract void dropInternally();

    /**
     * Checks if this object was {@link #drop() deleted} and throws an exception in case it was deleted. This method
     * must be called before each operation that assumes the object still exists in the database.
     */
    protected void verifyNotDeleted() {
        if (deleted) {
            throw new DatabaseObjectDeletedException(this);
        }
    }

    @Override
    public boolean isOwned() {
        return this.owned;
    }
}
