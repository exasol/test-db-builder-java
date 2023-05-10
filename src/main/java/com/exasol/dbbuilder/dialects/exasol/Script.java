package com.exasol.dbbuilder.dialects.exasol;

import java.util.ArrayList;
import java.util.List;

import com.exasol.db.ExasolIdentifier;
import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.Schema;

/**
 * Exasol database (Lua) Script.
 */
public class Script extends AbstractScript {
    private final List<ScriptParameter> parameters;
    private final boolean returnsTable;

    private Script(final Builder builder) {
        super(builder);
        this.parameters = builder.parameters;
        this.returnsTable = builder.returnsTable;
    }

    /**
     * Create a builder for a {@link Script}.
     *
     * @param writer       data object writer
     * @param parentSchema parent schema
     * @param name         name of the script
     * @return builder
     */
    public static Builder builder(final ExasolImmediateDatabaseObjectWriter writer, final Schema parentSchema,
            final String name) {
        return new Builder(writer, parentSchema, ExasolIdentifier.of(name));
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
        verifyNotDeleted();
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
        verifyNotDeleted();
        return this.writer.executeQuery(this, parameterValues);
    }

    @Override
    // [impl->dsn~dropping-scripts~1]
    protected void dropInternally() {
        this.writer.drop(this);
    }

    /**
     * Builder for a {@link Script}.
     */
    public static class Builder extends AbstractScript.Builder<Builder> {
        private final List<ScriptParameter> parameters = new ArrayList<>();
        private boolean returnsTable = false;

        private Builder(final ExasolImmediateDatabaseObjectWriter writer, final Schema parentSchema,
                final Identifier name) {
            super(writer, parentSchema, name);
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
         * Set the return type of the script to a table.
         *
         * @return {@code this} for fluent programming
         */
        public Builder returnsTable() {
            this.returnsTable = true;
            return this;
        }

        @Override
        protected Builder getSelf() {
            return this;
        }

        /**
         * Build the script.
         *
         * @return built script
         */
        public Script build() {
            validate();
            final Script script = new Script(this);
            this.getWriter().write(script);
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