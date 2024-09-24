package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.*;

/**
 * A MySql table that allows specifying a charset.
 */
public class MySqlTable extends Table {

    private final String charset;

    /**
     * Create a new MySql table based on a given builder.
     *
     * @param builder the builder from which to copy the values
     */
    protected MySqlTable(final Builder builder) {
        super(builder);
        this.charset = builder.charset;
    }

    /**
     * Get the table's charset.
     * 
     * @return charset or {@code null} for the default charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * Create a builder for a {@link MySqlTable}.
     *
     * @param writer       database object writer
     * @param parentSchema parent schema
     * @param tableName    name of the database table
     * @return new {@link Builder} instance
     */
    public static Builder builder(final DatabaseObjectWriter writer, final Schema parentSchema,
            final Identifier tableName) {
        return new Builder(writer, parentSchema, tableName);
    }

    /**
     * Builder for {@link MySqlTable}s.
     */
    public static class Builder extends Table.Builder {

        private String charset;

        private Builder(final DatabaseObjectWriter writer, final Schema parentSchema, final Identifier tableName) {
            super(writer, parentSchema, tableName);
        }

        @Override
        public Builder column(final String columnName, final String columnType) {
            super.column(columnName, columnType);
            return this;
        }

        /**
         * Set a custom charset for the new table. Defaults to UTF-8.
         * 
         * @param charset custom charset, e.g. {@code ascii}
         * @return {@code this} for fluent programming
         */
        public Builder charset(final String charset) {
            this.charset = charset;
            return this;
        }

        /**
         * Build a new {@link MySqlTable} instance.
         *
         * @return new {@link MySqlTable} instance
         */
        @Override
        public MySqlTable build() {
            final MySqlTable table = new MySqlTable(this);
            this.writer.write(table);
            return table;
        }
    }
}
