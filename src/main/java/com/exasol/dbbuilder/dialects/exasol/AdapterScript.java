package com.exasol.dbbuilder.dialects.exasol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.AbstractSchemaChild;
import com.exasol.dbbuilder.dialects.Schema;

/**
 * Virtual Schema Adapter Script.
 */
public class AdapterScript extends AbstractSchemaChild {
    private final ExasolImmediateDatabaseObjectWriter writer;
    private final Language language;
    private final String content;
    private final String debuggerConnection;

    private AdapterScript(final ExasolImmediateDatabaseObjectWriter writer, final Schema parentSchema,
            final String name, final Language language, final String content, final String debuggerConnection) {
        super(parentSchema, ExasolIdentifier.of(name), false);
        this.writer = writer;
        this.language = language;
        this.content = content;
        this.debuggerConnection = debuggerConnection;
        this.writer.write(this);
    }

    /**
     * Get a builder for {@link AdapterScript}.
     *
     * @return builder for {@link AdapterScript}
     */
    public static Builder builder() {
        return new Builder();
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

    /**
     * Get the actual script content.
     *
     * @return script content
     */
    public String getContent() {
        return this.content;
    }

    @Override
    // [impl->dsn~dropping-adapter-scripts~1]
    public void drop() {
        this.writer.drop(this);
    }

    /**
     * Get if this adapter script has a debugger connection.
     *
     * @return true if a debugger connection was set
     */
    public boolean hasDebuggerConnection() {
        return this.debuggerConnection != null;
    }

    /**
     * Get the debugger connection for this adapter script.
     *
     * @return debugger connection
     */
    public String getDebuggerConnection() {
        return this.debuggerConnection;
    }

    public enum Language {
        JAVA, PYTHON, LUA, R
    }

    /**
     * Builder for {@link AdapterScript}.
     */
    public static final class Builder {
        protected Schema parentSchema;
        protected String name;
        protected boolean owned;
        private ExasolImmediateDatabaseObjectWriter writer;
        private Language language;
        private String content;
        private String debuggerConnection;

        private Builder() {
        }

        /**
         * Set the parent Schema.
         * 
         * @param parentSchema parent schema
         * @return self
         */
        public Builder parentSchema(final Schema parentSchema) {
            this.parentSchema = parentSchema;
            return this;
        }

        /**
         * Set the {@link ExasolImmediateDatabaseObjectWriter}.
         * 
         * @param writer {@link ExasolImmediateDatabaseObjectWriter}
         * @return self
         */
        public Builder writer(final ExasolImmediateDatabaseObjectWriter writer) {
            this.writer = writer;
            return this;
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
         * Set the content of the adapter script.
         * 
         * @param content script content
         * @return self
         */
        public Builder content(final String content) {
            this.content = content;
            return this;
        }

        /**
         * Load the adapter script content from a file.
         *
         * @param path path to file containing the script content
         * @return {@code this} for fluent programming
         * @throws IOException in case the file could not be read
         */
        public Builder content(final Path path) throws IOException {
            this.content = Files.readString(path);
            return this;
        }

        /**
         * Set an optional connection to a debugger. See {@link ExasolConfiguration#isAdapterScriptDebuggingEnabled()}
         * 
         * @param debuggerConnection optional connection to a debugger
         * @return self
         */
        public Builder debuggerConnection(final String debuggerConnection) {
            this.debuggerConnection = debuggerConnection;
            return this;
        }

        /**
         * Set the name of the adapter script..
         * 
         * @param name name of the adapter script
         * @return self
         */
        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * Build the {@link AdapterScript}.
         * 
         * @return built {@link AdapterScript}.
         */
        public AdapterScript build() {
            requireNotNull(this.writer, "writer");
            requireNotNull(this.parentSchema, "parentSchema");
            requireNotNull(this.name, "name");
            requireNotNull(this.language, "language");
            requireNotNull(this.content, "content");
            return new AdapterScript(this.writer, this.parentSchema, this.name, this.language, this.content,
                    this.debuggerConnection);
        }

        private void requireNotNull(final Object object, final String name) {
            if (object == null) {
                throw new IllegalStateException(
                        name + " is a required field. Please call " + name + "() before build().");
            }
        }
    }
}
