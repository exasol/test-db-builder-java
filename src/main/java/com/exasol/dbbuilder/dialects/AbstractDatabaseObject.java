package com.exasol.dbbuilder.dialects;

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
    protected QuoteApplier quoteApplier;
    protected String name;
    protected final boolean owned;

    /**
     * Create a database object.
     *
     * @param quoteApplier instance of {@link QuoteApplier}
     * @param name         name of the database object
     * @param owned        {@code true} if the object is owned by the TDDB, {@code false} if the TDDB attached to a
     *                     database object that already existed
     */
    public AbstractDatabaseObject(final QuoteApplier quoteApplier, final String name, final boolean owned) {
        this.quoteApplier = quoteApplier;
        this.name = name;
        this.owned = owned;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getFullyQualifiedName() {
        if (hasParent()) {
            return getParent().getFullyQualifiedName() + "." + this.quoteApplier.quote(this.name);
        } else {
            return this.quoteApplier.quote(this.name);
        }
    }

    @Override
    public boolean isOwned() {
        return this.owned;
    }
}