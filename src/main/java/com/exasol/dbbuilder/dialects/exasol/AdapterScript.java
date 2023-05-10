package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.Schema;

/**
 * Virtual Schema Adapter Script.
 */
public class AdapterScript extends AbstractScript {
    private final Language language;

    private AdapterScript(final Builder builder) {
        super(builder);
        this.language = builder.language;
    }

    /**
     * Get a builder for {@link AdapterScript}.
     *
     * @param writer       data object writer
     * @param parentSchema parent schema
     * @param name         name of the script
     * @return builder for {@link AdapterScript}
     */
    // [impl->dsn~creating-adapter-scripts~1]
    public static Builder builder(final ExasolImmediateDatabaseObjectWriter writer, final Schema parentSchema,
            final Identifier name) {
        return new Builder(writer, parentSchema, name);
    }

    @Override
    public String getType() {
        return "adapter script";
    }

    /**
     * Get the implementation language.
     *
     * @return language in which the adapter script is implemented
     */
    public Language getLanguage() {
        return this.language;
    }

    @Override
    // [impl->dsn~dropping-adapter-scripts~1]
    protected void dropInternally() {
        this.writer.drop(this);
    }

    /** Enum with script languages */
    public enum Language {
        /** Java */
        JAVA,
        /** Python */
        PYTHON,
        /** Lua */
        LUA
    }

    /**
     * Builder for {@link AdapterScript}.
     */
    public static final class Builder extends BucketFsContentAdapterScriptBuilder<Builder> {
        private Language language;

        private Builder(final ExasolImmediateDatabaseObjectWriter writer, final Schema parentSchema,
                final Identifier name) {
            super(writer, parentSchema, name);
        }

        /**
         * Set the language of the adapter script.
         * 
         * @param language language of the adapter script
         * @return self
         */
        public Builder language(final Language language) {
            this.language = language;
            return this;
        }

        /**
         * Build the {@link AdapterScript}.
         * 
         * @return built {@link AdapterScript}.
         */
        public AdapterScript build() {
            validate();
            final AdapterScript adapterScript = new AdapterScript(this);
            getWriter().write(adapterScript);
            return adapterScript;
        }

        @Override
        protected void validate() {
            super.validate();
            requireNotNull(this.language, "language");
        }

        @Override
        protected Builder getSelf() {
            return this;
        }
    }
}
