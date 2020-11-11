package com.exasol.dbbuilder.dialects.exasol.udf;

import java.util.List;

import com.exasol.dbbuilder.dialects.Column;

/**
 * This class represents the {@code EMITS(column1 type1, ...)} {@link UdfReturnType}.
 */
public class UdfReturnTypePredefinedEmits implements UdfReturnType {
    private final List<Column> columns;

    UdfReturnTypePredefinedEmits(final List<Column> columns) {
        this.columns = columns;
    }

    /**
     * Get a list of columns that the UDF returns.
     *
     * @return list of columns that the UDF returns
     */
    public List<Column> getColumns() {
        return this.columns;
    }

    @Override
    public void accept(final UdfReturnTypeVisitor visitor) {
        visitor.visit(this);
    }
}
