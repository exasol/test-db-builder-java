package com.exasol.dbbuilder.dialects.exasol.udf;

/**
 * This class represents the {@code EMITS(...)} {@link UdfReturnType}.
 */
public class UdfReturnTypeDynamicEmits implements UdfReturnType {
    /**
     * Create a new instance of an {@link UdfReturnTypeDynamicEmits}.
     */
    public UdfReturnTypeDynamicEmits() {
        // Intentionally empty default constructor to avoid Java 17+ JavaDoc warning.
    }

    @Override
    public void accept(final UdfReturnTypeVisitor visitor) {
        visitor.visit(this);
    }
}
