package com.exasol.dbbuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

/**
 * Database (Lua) Script.
 */
public class Script extends AbstractSchemaChild {
    private final String content;
    private final List<ScriptParameter> parameters;
    private final boolean returnsTable;

    private Script(final Builder builder) {
        super(builder.writer, builder.parentSchema, builder.name, builder.owned);
        this.content = builder.content;
        this.parameters = builder.parameters;
        this.returnsTable = builder.returnsTable;
    }

    @Override
    public String getType() {
        return "script";
    }

    /**
     * Get the script's parameters.
     *
     * @return parameters
     */
    public List<ScriptParameter> getParameters() {
        return this.parameters;
    }

    /**
     * Get the script content (i.e. the implementation).
     *
     * @return script content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Check if the script returns a table.
     *
     * @return {@code true} if the script returns a table, {@code false} if it returns a row count.
     */
    public boolean returnsTable() {
        return this.returnsTable;
    }

    /**
     * Execute the script ignoring potential return values.
     *
     * @param parameterValues script parameters
     * @return row count
     */
    // [impl->dsn~running-scripts-that-have-no-return~1]
    public int execute(final Object... parameterValues) {
        return this.writer.execute(this, parameterValues);
    }

    /**
     * Execute a script returning a table.
     *
     * @param parameterValues script parameter values
     *
     * @return script result as table
     */
    public List<List<Object>> executeQuery(final Object... parameterValues) {
        return this.writer.executeQuery(this, parameterValues);
    }

    /**
     * Create a builder for a {@link Script}.
     *
     * @param writer       data object writer
     * @param parentSchema parent schema
     * @param name         name of the script
     * @return builder
     */
    public static Builder builder(final DatabaseObjectWriter writer, final Schema parentSchema, final String name) {
        return new Builder(writer, parentSchema, name);
    }

    /**
     * Builder for a {@link Script}.
     */
    public static class Builder {
        private final DatabaseObjectWriter writer;
        private final Schema parentSchema;
        private final String name;
        private final List<ScriptParameter> parameters = new ArrayList<>();
        private String content;
        private boolean returnsTable = false;
        private boolean owned = true;

        private Builder(final DatabaseObjectWriter writer, final Schema parentSchema, final String name) {
            this.writer = writer;
            this.parentSchema = parentSchema;
            this.name = name;
        }

        /**
         * Add parameters to the script parameter list.
         *
         * @param parameterNames list of parameter names
         * @return {@code this} for fluent programming
         */
        public Builder parameter(final String... parameterNames) {
            for (final String parameterName : parameterNames) {
                this.parameters.add(new ScriptParameter(parameterName, false));
            }
            return this;
        }

        /**
         * Add array parameters to the script parameter list.
         *
         * @param parameterNames list of parameter names
         * @return {@code this} for fluent programming
         */
        public Builder arrayParameter(final String... parameterNames) {
            for (final String parameterName : parameterNames) {
                this.parameters.add(new ScriptParameter(parameterName, true));
            }
            return this;
        }

        /**
         * Set the content of the script (i.e. the implementation).
         *
         * @param content script content
         * @return {@code this} for fluent programming
         */
        public Builder content(final String content) {
            this.content = content;
            return this;
        }

        /**
         * Load the script content from a file.
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
         * Set the return type of the script to a table.
         *
         * @return {@code this} for fluent programming
         */
        public Builder returnsTable() {
            this.returnsTable = true;
            return this;
        }

        /**
         * Create a new instance of a {@link Script}.
         *
         * @return new instance
         */
        public Script build() {
            final Script script = new Script(this);
            this.writer.write(script);
            return script;
        }

        /**
         * Create a control object instance from an existing script.
         *
         * @return new instance
         */
        // [impl->dsn~controlling-existing-scripts~1]
        public Script attach() {
            this.owned = false;
            return new Script(this);
        }
    }
}