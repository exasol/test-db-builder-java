package com.exasol.dbbuilder.dialects.exasol.udf;

/**
 * This interface represents the return type of a UDF.
 */
public interface UdfReturnType {
    /**
     * Accept a {@link UdfReturnTypeVisitor}.
     * 
     * @param visitor visitor to accept
     */
    public void accept(final UdfReturnTypeVisitor visitor);
}
