package com.exasol.dbbuilder.dialects.oracle;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;
import com.exasol.dbbuilder.dialects.Schema;
import com.exasol.dbbuilder.dialects.Table;

/**
 * OracleTable class, used for oracle containers.
 */
public class OracleTable extends Table {

    private OracleTable(final TableBuilder builder) {
        super(builder);
    }

    /**
     * Returns a (Oracle) table builder, used for constructing an Oracle table.
     *
     * @param writer A writer
     * @param schema The parent schema
     * @param tableName The table name
     * @return A builder for building an oracle table
     */
    public static OracleTableBuilder oracleTableBuilder(final DatabaseObjectWriter writer, final Schema schema, final Identifier tableName) {
        return new OracleTableBuilder(writer, schema, tableName);
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
    public static class OracleTableBuilder extends TableBuilder {
        /**
         * Constructor for an oracle table builder.
         *
         * @param writer A writer
         * @param parentSchema The parent schema of the table
         * @param tableName The name of the table to be built
         */
        public OracleTableBuilder(final DatabaseObjectWriter writer, final Schema parentSchema, final Identifier tableName) {
        super(writer,parentSchema,tableName);
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

