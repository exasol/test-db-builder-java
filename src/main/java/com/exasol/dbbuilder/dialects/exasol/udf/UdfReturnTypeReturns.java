package com.exasol.dbbuilder.dialects.exasol.udf;

/**
 * This class represents the {@code RETURNS type} {@link UdfReturnType}.
 */
public class UdfReturnTypeReturns implements UdfReturnType {
    private final String type;

    UdfReturnTypeReturns(final String type) {
        this.type = type;
    }

    /**
     * Get the return type of the UDF.
     *
     * @return return type of the UDF
     */
    public String getType() {
        return this.type;
    }

    @Override
    public void accept(final UdfReturnTypeVisitor visitor) {
        visitor.visit(this);
    }
}
