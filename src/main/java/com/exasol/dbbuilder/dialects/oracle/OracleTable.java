package com.exasol.dbbuilder.dialects.oracle;

import com.exasol.dbbuilder.dialects.*;

/**
 * OracleTable class, used for oracle containers.
 */
public class OracleTable extends Table {

    private OracleTable(final Table.Builder builder) {
        super(builder);
    }

    /**
     * Returns an Oracle table builder, used for constructing an Oracle table.
     *
     * @param writer    A writer
     * @param schema    The parent schema
     * @param tableName The table name
     * @return A builder for building an oracle table
     */
    public static Builder builder(final DatabaseObjectWriter writer, final Schema schema,
            final OracleIdentifier tableName) {
        return new OracleTable.Builder(writer, schema, tableName);
    }

    @Override
    public String getFullyQualifiedName() {
        if (hasParent()) {
            return getParent().getName() + "." + this.name.toString();
        } else {
            return this.name.toString();
        }
    }

    /**
     * A builder for Oracle Table objects.
     */
    public static class Builder extends Table.Builder {
        /**
         * Constructor for an oracle table builder.
         *
         * @param writer       A writer
         * @param parentSchema The parent schema of the table
         * @param tableName    The name of the table to be built
         */
        public Builder(final DatabaseObjectWriter writer, final Schema parentSchema, final OracleIdentifier tableName) {
            super(writer, parentSchema, tableName);
        }

        /**
         * Builds and returns the table object.
         *
         * @return OracleTable object
         */
        @Override
        public OracleTable build() {
            return new OracleTable(this);
        }
    }
}
