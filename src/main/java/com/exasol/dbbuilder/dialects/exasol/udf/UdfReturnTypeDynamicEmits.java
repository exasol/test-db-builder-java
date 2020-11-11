package com.exasol.dbbuilder.dialects.exasol.udf;

/**
 * This class represents the {@code EMITS(...)} {@link UdfReturnType}.
 */
public class UdfReturnTypeDynamicEmits implements UdfReturnType {

    @Override
    public void accept(final UdfReturnTypeVisitor visitor) {
        visitor.visit(this);
    }
}
