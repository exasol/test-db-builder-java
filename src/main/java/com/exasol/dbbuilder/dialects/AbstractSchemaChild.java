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

    /**
     * Create a new instance of {@link AbstractSchemaChild} from it's {@link Builder}.
     * 
     * @param builder builder to read the parameters from
     */
    protected AbstractSchemaChild(final Builder<?> builder) {
        super(builder.name, builder.owned);
        this.parentSchema = builder.parentSchema;
    }

    @Override
    public boolean hasParent() {
        return true;
    }

    @Override
    public DatabaseObject getParent() {
        return this.parentSchema;
    }

    /**
     * Builder for {@link AbstractSchemaChild}.
     * 
     * @param <T> this type
     */
    public abstract static class Builder<T extends Builder<T>> {

        private final Schema parentSchema;
        private final Identifier name;
        protected boolean owned = true;

        protected Builder(final Schema parentSchema, final Identifier name) {
            this.parentSchema = parentSchema;
            this.name = name;
        }

        /**
         * Validate the fields. Call this in {@code build()}.
         */
        protected void validate() {
            requireNotNull(this.parentSchema, "parentSchema");
            requireNotNull(this.name, "name");
        }

        /**
         * Require a field to be set.
         * 
         * @param object field to check
         * @param name   name of the field for error message
         */
        protected void requireNotNull(final Object object, final String name) {
            if (object == null) {
                throw new IllegalStateException(
                        name + " is a required field. Please call " + name + "() before build().");
            }
        }
    }
}