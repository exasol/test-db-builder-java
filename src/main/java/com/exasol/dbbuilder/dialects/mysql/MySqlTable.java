package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.*;

public class MySqlTable extends Table {

    private final String charset;

    protected MySqlTable(final MySqlTableBuilder builder) {
        super(builder);
        this.charset = builder.charset;
    }

    public String getCharset() {
        return charset;
    }

    public static MySqlTableBuilder builder(final DatabaseObjectWriter writer, final Schema parentSchema,
            final Identifier tableName) {
        return new MySqlTableBuilder(writer, parentSchema, tableName);
    }

    public static class MySqlTableBuilder extends Table.Builder {

        private String charset;

        private MySqlTableBuilder(final DatabaseObjectWriter writer, final Schema parentSchema,
                final Identifier tableName) {
            super(writer, parentSchema, tableName);
        }

        @Override
        public MySqlTableBuilder column(final String columnName, final String columnType) {
            super.column(columnName, columnType);
            return this;
        }

        public MySqlTableBuilder charset(final String charset) {
            this.charset = charset;
            return this;
        }

        @Override
        public MySqlTable build() {
            final MySqlTable table = new MySqlTable(this);
            this.writer.write(table);
            return table;
        }
    }
}
