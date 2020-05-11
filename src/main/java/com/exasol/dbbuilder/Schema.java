package com.exasol.dbbuilder;

import java.util.ArrayList;
import java.util.List;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

/**
 * Database schema.
 */
public class Schema extends AbstractDatabaseObject {
    private final List<Table> tables = new ArrayList<>();

    /**
     * Create a new database schema.
     *
     * @param writer database object writer
     * @param name   name of the database schema
     */
    public Schema(final DatabaseObjectWriter writer, final String name) {
        super(writer, name);
        writer.write(this);
    }

    @Override
    public String getType() {
        return "schema";
    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public DatabaseObject getParent() {
        throw new DatabaseObjectException(this,
                "Illegal attempt to access parent object of a SCHEMA which is a top-level object.");
    }

    /**
     * Get the tables inside this schema.
     *
     * @return list of tables in this schema
     */
    public List<Table> getTables() {
        return this.tables;
    }

    /**
     * Create an adapter script.
     *
     * @param name     name of the adapter script
     * @param language language the adapter script is implemented in
     * @param content  implementation of the script
     * @return adapter script
     */
    public AdapterScript createAdapterScript(final String name, final AdapterScript.Language language,
            final String content) {
        return new AdapterScript(this.writer, this, name, language, content);
    }

    /**
     * Create a script that does not return anything.
     *
     * @param name    name of the script
     * @param content implementation of the script
     * @return script
     */
    // [impl->dsn~creating-scripts~1]
    public Script createScript(final String name, final String content) {
        return Script.builder(this.writer, this, name).content(content).build();
    }

    /**
     * Create a table with an arbitrary number of columns.
     *
     * @param name        name of the table
     * @param columnNames list of column names
     * @param columnTypes list of column types
     * @return table
     */
    public Table createTable(final String name, final List<String> columnNames, final List<String> columnTypes) {
        if (columnNames.size() == columnTypes.size()) {
            final Table.Builder builder = Table.builder(this.writer, this, name);
            int index = 0;
            for (final String columnName : columnNames) {
                builder.column(columnName, columnTypes.get(index));
                ++index;
            }
            final Table table = builder.build();
            this.tables.add(table);
            return table;
        } else {
            throw new IllegalArgumentException("Got " + columnNames.size() + " column names but " + columnTypes
                    + " column types. Please provide the same number of parameters for both when creating a table.");
        }
    }

    /**
     * Create a table with one column.
     *
     * @param name        name of the table
     * @param column1Name name of the first column
     * @param column1Type type of the first column
     * @return table
     */
    public Table createTable(final String name, final String column1Name, final String column1Type) {
        return createTable(name, List.of(column1Name), List.of(column1Type));
    }

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
    public Table createTable(final String name, final String column1Name, final String column1Type,
            final String column2Name, final String column2Type) {
        return createTable(name, List.of(column1Name, column2Name), List.of(column1Type, column2Type));
    }

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
    public Table createTable(final String name, final String column1Name, final String column1Type,
            final String column2Name, final String column2Type, final String column3Name, final String column3Type) {
        return createTable(name, List.of(column1Name, column2Name, column3Name),
                List.of(column1Type, column2Type, column3Type));
    }

    /**
     * Create a builder for a table.
     * <p>
     * In cases where you need a more complex table than can be created by the convenience methods {@code createTable},
     * this method provides a builder.
     *
     * @param name table name
     * @return builder for the table
     */
    public Table.Builder createTableBuilder(final String name) {
        return Table.builder(this.writer, this, name);
    }
}
