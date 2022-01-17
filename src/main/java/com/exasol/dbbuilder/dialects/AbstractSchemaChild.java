package com.exasol.dbbuilder.dialects;

import com.exasol.db.Identifier;
import com.exasol.errorreporting.ExaError;

/**
 * Base class for all database objects in the scope of a database schema.
 */
public abstract class AbstractSchemaChild extends AbstractDatabaseObject {
    /** Parent schema */
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
    protected AbstractSchemaChild(final Builder builder) {
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
     */
    public abstract static class Builder {

        private final Schema parentSchema;
        private final Identifier name;
        /** Is this schema child owned? */
        protected boolean owned = true;

        /**
         * Create a new abstract builder.
         * 
         * @param parentSchema parent schema
         * @param name         object name
         */
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
                throw new IllegalStateException(ExaError.messageBuilder("E-TDBJ-15").message(
                        "{{name}} is a required field. Please provide a value by calling {{name|uq}}() before build().",
                        name).toString());
            }
        }
    }
}