package com.exasol.dbbuilder.dialects.exasol.udf;

import java.util.*;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.Column;
import com.exasol.dbbuilder.dialects.Schema;
import com.exasol.dbbuilder.dialects.exasol.*;
import com.exasol.errorreporting.ExaError;

/**
 * This class represents UDF scripts.
 */
public class UdfScript extends AbstractScript {
    private final List<Column> parameters;
    private final UdfReturnType returnType;
    private final InputType inputType;
    private final Language language;

    private UdfScript(final Builder builder) {
        super(builder);
        this.parameters = builder.parameters;
        this.returnType = builder.returnType;
        this.inputType = builder.inputType;
        this.language = builder.language;
    }

    /**
     * Get a builder for {@link UdfScript}.
     * 
     * @param writer       data object writer
     * @param parentSchema parent schema
     * @param name         name of the script
     * @return builder for {@link UdfScript}
     */
    public static Builder builder(final ExasolImmediateDatabaseObjectWriter writer, final Schema parentSchema,
            final Identifier name) {
        return new Builder(writer, parentSchema, name);
    }

    @Override
    public String getType() {
        return "udf script";
    }

    /**
     * Get the input parameters for this UDF.
     * 
     * @return list of input parameters
     */
    public List<Column> getParameters() {
        return this.parameters;
    }

    /**
     * Get the {@link UdfReturnType} of this UDF.
     * 
     * @return {@link UdfReturnType} of this UDF
     */
    public UdfReturnType getReturnType() {
        return this.returnType;
    }

    /**
     * Get the {@link InputType} of this UDF.
     * 
     * @return {@link InputType} of this UDF
     */
    public InputType getInputType() {
        return this.inputType;
    }

    /**
     * Get the {@link Language} of this UDF.
     *
     * @return {@link Language} of this UDF
     */
    public Language getLanguage() {
        return this.language;
    }

    @Override
    protected void dropInternally() {
        this.writer.drop(this);
    }

    /**
     * Languages supported by UDFs.
     */
    public enum Language {
        /**
         * Java language.
         */
        JAVA,
        /**
         * Python 2 language.
         * 
         * @deprecated Newer versions of Exasol 7.1 don't support Python 2 any more. Migrate to {@link #PYTHON3}.
         */
        @Deprecated PYTHON,
        /**
         * Python 3 language.
         */
        PYTHON3,
        /**
         * Lua language.
         */
        LUA,
        /**
         * R language.
         */
        R
    }

    /**
     * UDF input types.
     */
    public enum InputType {
        /**
         * If you define the option SET, then the processing refers to a set of input values. Within the code, you can
         * iterate through those values
         */
        SET,
        /**
         * The keyword SCALAR specifies that the script processes single input rows. It's code is therefore called once
         * per input row.
         */
        SCALAR
    }

    /**
     * Builder for {@link UdfScript}.
     */
    public static class Builder extends BucketFsContentAdapterScriptBuilder<Builder> {
        private final List<Column> parameters = new ArrayList<>();
        private Language language;
        private InputType inputType;
        private UdfReturnType returnType;

        private Builder(final ExasolImmediateDatabaseObjectWriter writer, final Schema parentSchema,
                final Identifier name) {
            super(writer, parentSchema, name);
        }

        /**
         * Set the language of the UDF.
         *
         * @param language language of the adapter script
         * @return self
         */
        public Builder language(final Language language) {
            this.language = language;
            return this;
        }

        /**
         * Set input type of the UDF.
         * 
         * @param inputType type
         * @return self for fluent programming
         */
        public Builder inputType(final InputType inputType) {
            this.inputType = inputType;
            return this;
        }

        /**
         * Set return type fo this UDF to EMITS(param_name type, ...).
         * 
         * @param columns {@link Column}s that the UDF emits
         * @return self for fluent programming
         */
        public Builder emits(final Column... columns) {
            this.returnType = new UdfReturnTypePredefinedEmits(Arrays.asList(columns));
            return this;
        }

        /**
         * Add an input parameter to this UDF.
         * 
         * @param name name of the parameter
         * @param type type of the parameter
         * @return self for fluent programming
         */
        public Builder parameter(final String name, final String type) {
            this.parameters.add(new Column(name, type));
            return this;
        }

        /**
         * Set return type fo this UDF to EMITS(...).
         *
         * @return self for fluent programming
         */
        public Builder emits() {
            this.returnType = new UdfReturnTypeDynamicEmits();
            return this;
        }

        /**
         * Set return type fo this UDF to EMITS(...).
         * 
         * @param returnType return type of the UDF
         * @return self for fluent programming
         */
        public Builder returns(final String returnType) {
            this.returnType = new UdfReturnTypeReturns(returnType);
            return this;
        }

        /**
         * Build the UDF script.
         * 
         * @return built UDF script
         */
        public UdfScript build() {
            validate();
            requireNotNull(this.language, "language");
            requireNotNull(this.inputType, "inputType");
            if (this.returnType == null) {
                throw new IllegalStateException(ExaError.messageBuilder("E-TDBJ-7").message(
                        "Missing return type. Please set it by calling emits(), emits(types...) or returns(type) on this builder.")
                        .toString());
            }
            final UdfScript udf = new UdfScript(this);
            this.getWriter().write(udf);
            return udf;
        }

        @Override
        protected Builder getSelf() {
            return this;
        }
    }

}