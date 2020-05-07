package com.exasol.dbbuilder;

import java.util.ArrayList;
import java.util.List;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

/**
 * Database (Lua) Script.
 */
public class Script extends AbstractDatabaseObject {
    private final Schema parentSchema;
    private final String content;
    private final List<ScriptParameter> parameters;
    private final ScriptReturnType returnType;

    private Script(final Builder builder) {
        super(builder.writer, builder.name);
        this.parentSchema = builder.parentSchema;
        this.content = builder.content;
        this.parameters = builder.parameters;
        this.returnType = builder.returnType;
    }

    @Override
    public String getType() {
        return "script";
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
     * Get the type of return of the script.
     *
     * @return return type
     */
    public ScriptReturnType getReturnType() {
        return this.returnType;
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
        private ScriptReturnType returnType;

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
         * Set the return type of the script.
         *
         * @param returnType type of returned value
         * @return {@code this} for fluent programming
         */
        public Builder returnType(final ScriptReturnType returnType) {
            this.returnType = returnType;
            return this;
        }

        /**
         * Create a new instance of a {@link Script}.
         *
         * @return new instance
         */
        public Script build() {
            return new Script(this);
        }
    }
}