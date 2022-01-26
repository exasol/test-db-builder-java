package com.exasol.dbbuilder.dialects;

import java.util.List;

import com.exasol.errorreporting.ExaError;

/**
 * Database schema.
 */
public interface Schema extends DatabaseObject {
    /**
     * Get the tables inside this schema.
     *
     * @return list of tables in this schema
     */
    List<Table> getTables();

    /**
     * Create a table with an arbitrary number of columns.
     *
     * @param name        name of the table
     * @param columnNames list of column names
     * @param columnTypes list of column types
     * @return table
     */
    // [impl->dsn~creating-tables~1]
    Table createTable(String name, List<String> columnNames, List<String> columnTypes);

    /**
     * Create a table with one column.
     *
     * @param name        name of the table
     * @param column1Name name of the first column
     * @param column1Type type of the first column
     * @return table
     */
    // [impl->dsn~creating-tables~1]
    Table createTable(String name, String column1Name, String column1Type);

    /**
     * Create a table with two columns.
     *
     * @param name        name of the table
     * @param column1Name name of the first column
     * @param column1Type type of the first column
     * @param column2Name name of the second column
     * @param column2Type type of the second column
     * @return table
     */
    // [impl->dsn~creating-tables~1]
    Table createTable(String name, String column1Name, String column1Type, String column2Name, String column2Type);

    /**
     * Create a table with three columns.
     *
     * @param name        name of the table
     * @param column1Name name of the first column
     * @param column1Type type of the first column
     * @param column2Name name of the second column
     * @param column2Type type of the second column
     * @param column3Name name of the third column
     * @param column3Type type of the third column
     * @return table
     */
    // [impl->dsn~creating-tables~1]
    Table createTable(String name, String column1Name, String column1Type, String column2Name, String column2Type,
            String column3Name, String column3Type);

    /**
     * Create a builder for a table.
     * <p>
     * In cases where you need a more complex table that can be created by the convenience methods {@code createTable},
     * this method provides a builder.
     *
     * @param name table name
     * @return builder for the table
     */
    public Table.TableBuilder createTableBuilder(final String name);

    @Override
    default String getType() {
        return "schema";
    }

    @Override
    default boolean hasParent() {
        return false;
    }

    @Override
    default DatabaseObject getParent() {
        throw new DatabaseObjectException(this,ExaError.messageBuilder("E-TDBJ-16").message("Illegal attempt to access parent object of a SCHEMA which is a top-level object.").toString());
    }
}
