package com.exasol.dbbuilder.dialects;

import java.util.*;
import java.util.stream.Stream;

import com.exasol.db.Identifier;

/**
 * Database table.
 */
public class Table extends AbstractSchemaChild {
    private final DatabaseObjectWriter writer;
    private final List<Column> columns;

    private Table(final Builder builder) {
        super(builder.parentSchema, builder.name, false);
        this.columns = builder.columns;
        this.writer = builder.writer;
        this.writer.write(this);
    }

    /**
     * Create a builder for a {@link Table}.
     *
     * @param writer    database object writer
     * @param schema    parent schema
     * @param tableName name of the database table
     * @return new {@link Table} instance
     */
    // [impl->dsn~creating-tables~1]
    public static Builder builder(final DatabaseObjectWriter writer, final Schema schema, final Identifier tableName) {
        return new Builder(writer, schema, tableName);
    }

    @Override
    public String getType() {
        return "table";
    }

    @Override
    // [impl->dsn~dropping-tables~1]
    public void drop() {
        this.writer.drop(this);
    }

    /**
     * Get the columns of the table.
     *
     * @return table columns
     */
    public List<Column> getColumns() {
        return this.columns;
    }

    /**
     * Get the number of columns the table has.
     *
     * @return column count
     */
    public int getColumnCount() {
        return this.columns.size();
    }

    /**
     * Insert a row of values to the table.
     *
     * @param values cell values
     * @return {@code this} for fluent programming
     */
    public Table insert(final Object... values) {
        if (values.length != this.columns.size()) {
            throw new IllegalArgumentException(
                    "Column count mismatch. Tried to insert row with " + values.length + " values into table \""
                            + this.getFullyQualifiedName() + "\" which has " + this.columns.size() + " columns");
        }
        final List<Object> row = Arrays.asList(values);
        this.writer.write(this, Stream.of(row));
        return this;
    }

    /**
     * Insert multiple rows at once. Compared to inserting each row using {@link #insert(Object...)} this is a lot
     * faster.
     * 
     * @param rows stream of rows to insert
     * @return {@code this} for fluent programming
     */
    public Table bulkInsert(final Stream<List<Object>> rows) {
        this.writer.write(this, rows);
        return this;
    }

    /**
     * Builder for database tables.
     */
    public static class Builder {
        private final DatabaseObjectWriter writer;
        private final Identifier name;
        private final List<Column> columns = new ArrayList<>();
        private final Schema parentSchema;

        /**
         * Create new instance of a builder for a database table.
         *
         * @param writer       data object writer
         * @param parentSchema parent schema
         * @param tableName    name of the database table
         */
        public Builder(final DatabaseObjectWriter writer, final Schema parentSchema, final Identifier tableName) {
            this.writer = writer;
            this.parentSchema = parentSchema;
            this.name = tableName;
        }

        /**
         * Add a column to the table.
         *
         * @param columnName name of the column
         * @param columnType column data type
         * @return {@code this} for fluent programming
         */
        public Builder column(final String columnName, final String columnType) {
            this.columns.add(new Column(columnName, columnType));
            return this;
        }

        /**
         * Build a new {@link Table} instance.
         *
         * @return new {@link Table} instance
         */
        public Table build() {
            return new Table(this);
        }
    }
}