package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.AbstractSchemaChild;
import com.exasol.dbbuilder.dialects.Schema;

import java.util.Optional;

/**
 * Virtual Schema Adapter Script.
 */
public class AdapterScript extends AbstractSchemaChild {
    private final ExasolImmediateDatabaseObjectWriter writer;
    private final Language language;
    private final String content;
    private final Optional<String> debuggerConnection;

    /**
     * Create a new instance of an {@link AdapterScript} with possible debugger connection.
     * <p>
     * The debugger is only attached if the property test.debugAdapterScripts="true" is set. You can set it by appending
     * -Dtest.debug="true" to your the JVM.
     * </p>
     *
     * @param writer             database object writer
     * @param parentSchema       parent schema
     * @param name               name of the adapter script
     * @param language           language the the script is implemented in
     * @param content            the actual script
     * @param debuggerConnection connection string for the debugger
     */
    public AdapterScript(final ExasolImmediateDatabaseObjectWriter writer, final Schema parentSchema, final String name,
            final Language language, final String content, final Optional<String> debuggerConnection) {
        super(parentSchema, ExasolIdentifier.of(name), false);
        this.writer = writer;
        this.language = language;
        this.content = content;
        this.debuggerConnection = debuggerConnection;
        this.writer.write(this);
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

    /**
     * Get if debugging adapter scripts was globally enabled enabled.
     * 
     * @return true if debugging adapter scripts was globally enabled
     */
    public static boolean isAdapterScriptDebuggingEnabled() {
        final String debugProperty = System.getProperty("tests.debugAdapterScripts");
        return debugProperty != null && debugProperty.equals("true");
    }

    /**
     * Get the debugger connection for this adapter script.
     * 
     * @return debugger connection
     */
    public Optional<String> getDebuggerConnection() {
        return this.debuggerConnection;
    }

    public enum Language {
        JAVA, PYTHON, LUA, R
    }

    /**
     * Get a builder for {@link AdapterScript}.
     * 
     * @return builder for {@link AdapterScript}
     */
    public static Builder builder() {
        return new Builder();
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
        private Optional<String> debuggerConnection = Optional.empty();

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
         * Set an optional connection to a debugger.
         * 
         * @param debuggerConnection optional connection to a debugger
         * @return self
         */
        public Builder debuggerConnection(final String debuggerConnection) {
            this.debuggerConnection = Optional.of(debuggerConnection);
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
