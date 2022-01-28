package com.exasol.dbbuilder.dialects;

import java.util.*;
import java.util.stream.Stream;

import com.exasol.db.Identifier;
import com.exasol.errorreporting.ExaError;

/**
 * Database table.
 */
public class Table extends AbstractSchemaChild {
    private final DatabaseObjectWriter writer;
    private final List<Column> columns;

    /**
     * Table constructor
     *
     * @param builder Pass in a table builder with the right column and table name information.
     */
    protected Table(final TableBuilder builder) {
        super(builder.parentSchema, builder.tableName, false);
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
    public static TableBuilder builder(final DatabaseObjectWriter writer, final Schema schema, final Identifier tableName) {
        return new TableBuilder(writer, schema, tableName);
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
        this.bulkInsert(Stream.of(Arrays.asList(values)));
        return this;
    }

    /**
     * Remove all rows from this table.
     */
    public void truncate() {
        this.writer.truncate(this);
    }

    /**
     * Insert multiple rows at once. Compared to inserting each row using {@link #insert(Object...)} this is a lot
     * faster.
     * 
     * @param rows stream of rows to insert
     * @return {@code this} for fluent programming
     */
    @SuppressWarnings("java:S3864") // usage pf peek is safe here
    public Table bulkInsert(final Stream<List<Object>> rows) {
        this.writer.write(this, rows.peek(row -> {
            if (row.size() != getColumnCount()) {
                throw new IllegalArgumentException(ExaError.messageBuilder("E-TDBJ-3").message(
                    "Column count mismatch. Tried to insert row with {{actual}} values into table {{table name}} which has {{expected}} columns. If this is a bulk insert, multiple other rows might have already been written. Consider a rollback on the connection, to discard the changes.", row.size(), getFullyQualifiedName(), getColumnCount())
                    .toString());
            }
        }));
        return this;
    }

    /**
     * Builder for database tables.
     */
    public static class TableBuilder {
        private final DatabaseObjectWriter writer;
        private final Identifier tableName;
        private final List<Column> columns = new ArrayList<>();
        private final Schema parentSchema;

        /**
         * Create new instance of a builder for a database table.
         *
         * @param writer       data object writer
         * @param parentSchema parent schema
         * @param tableName    name of the database table
         */
        public TableBuilder(final DatabaseObjectWriter writer, final Schema parentSchema, final Identifier tableName) {
            this.writer = writer;
            this.parentSchema = parentSchema;
            this.tableName = tableName;
        }

        /**
         * Add a column to the table.
         *
         * @param columnName name of the column
         * @param columnType column data type
         * @return {@code this} for fluent programming
         */
        public TableBuilder column(final String columnName, final String columnType) {
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