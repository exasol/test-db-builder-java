package com.exasol.dbbuilder.dialects.exasol.udf;

/**
 * Visitor for {@link UdfReturnType}.
 */
public interface UdfReturnTypeVisitor {
    public void visit(UdfReturnTypeReturns returns);

    public void visit(UdfScript.UdfReturnTypeDynamicEmits emits);

    public void visit(UdfReturnTypePredefinedEmits emits);
}
