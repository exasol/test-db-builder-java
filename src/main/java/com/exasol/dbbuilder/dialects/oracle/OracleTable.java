package com.exasol.dbbuilder.dialects.oracle;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;
import com.exasol.dbbuilder.dialects.Schema;
import com.exasol.dbbuilder.dialects.Table;

public class OracleTable extends Table {

    private OracleTable(final TableBuilder builder) {
        super(builder);
    }

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
    public static class OracleTableBuilder extends TableBuilder {
        public OracleTableBuilder(final DatabaseObjectWriter writer, final Schema parentSchema, final Identifier tableName) {
        super(writer,parentSchema,tableName);
        }
        @Override
        public OracleTable build() {
            return new OracleTable(this);
        }
        }


    }

