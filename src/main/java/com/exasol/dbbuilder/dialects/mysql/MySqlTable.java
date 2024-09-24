package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.*;

/**
 * A MySql table that allows specifying a character set.
 */
public class MySqlTable extends Table {
    private final String charset;

    /**
     * Create a new MySql table based on a given builder.
     *
     * @param builder builder from which to copy the values
     */
    protected MySqlTable(final Builder builder) {
        super(builder);
        this.charset = builder.charset;
    }

    /**
     * Get the table's character set.
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
        // Overriding this so that returned builder has the right type and users don't need to cast.
        public Builder column(final String columnName, final String columnType) {
            return (Builder) super.column(columnName, columnType);
        }

        /**
         * Set a custom character set for the new table. Defaults to UTF-8.
         * <p>
         * This character set is then used for the whole table down to the columns. Additionally the standard collation
         * rules for this dataset are applied.
         * </p>
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
