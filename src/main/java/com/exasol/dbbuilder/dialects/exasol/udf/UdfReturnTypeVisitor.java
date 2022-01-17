package com.exasol.dbbuilder.dialects.exasol.udf;

/**
 * Visitor for {@link UdfReturnType}.
 */
public interface UdfReturnTypeVisitor {
    /**
     * Visit.
     *
     * @param returns object to visit
     */
    public void visit(UdfReturnTypeReturns returns);

    /**
     * Visit.
     *
     * @param emits object to visit
     */
    public void visit(UdfReturnTypeDynamicEmits emits);

    /**
     * Visit.
     *
     * @param emits object to visit
     */
    public void visit(UdfReturnTypePredefinedEmits emits);
}
