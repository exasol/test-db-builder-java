package com.exasol.dbbuilder.dialects;

import java.util.ArrayList;
import java.util.List;

import com.exasol.db.Identifier;
import com.exasol.errorreporting.ExaError;

/**
 * This class contains common logic for a database schema.
 */
public abstract class AbstractSchema extends AbstractDatabaseObject implements Schema {
    /** List of tables */
    protected final List<Table> tables = new ArrayList<>();

    /**
     * Create a new database schema.
     *
     * @param name name of the database schema
     */
    protected AbstractSchema(final Identifier name) {
        super(name, false);
    }

    /**
     * Get a {@link DatabaseObjectWriter}.
     *
     * @return {@link DatabaseObjectWriter}
     */
    protected abstract DatabaseObjectWriter getWriter();

    @Override
    public List<Table> getTables() {
        return this.tables;
    }

    @Override
    public Table createTable(final String name, final String column1Name, final String column1Type) {
        return createTable(name, List.of(column1Name), List.of(column1Type));
    }

    @Override
    public Table createTable(final String name, final String column1Name, final String column1Type,
            final String column2Name, final String column2Type) {
        return createTable(name, List.of(column1Name, column2Name), List.of(column1Type, column2Type));
    }

    @Override
    public Table createTable(final String name, final String column1Name, final String column1Type,
            final String column2Name, final String column2Type, final String column3Name, final String column3Type) {
        return createTable(name, List.of(column1Name, column2Name, column3Name),
                List.of(column1Type, column2Type, column3Type));
    }

    @Override
    public Table.Builder createTableBuilder(final String name) {
        return Table.builder(getWriter(), this, getIdentifier(name));
    }

    @Override
    public Table createTable(final String name, final List<String> columnNames, final List<String> columnTypes) {
        if (columnNames.size() == columnTypes.size()) {
            //Create a local table builder + enter info
            final Table.Builder builder = Table.builder(getWriter(), this, getIdentifier(name));
            passColumnsToTableBuilder(columnNames, columnTypes, builder);
            //Build a table with the builder
            final Table table = builder.build();
            //add the table to the schema's tables list
            this.tables.add(table);
            //return the new table object (reference)
            return table;
        } else {
            throw new IllegalArgumentException(ExaError.messageBuilder("E-TDBJ-18").message(
                    "Got {{column names size}} column names but {{column types}} column types. Please provide the same number of parameters for both when creating a table.",
                    columnNames.size(), columnTypes.size()).toString());
        }
    }

    /**
     * Method that passes in a list of column names, and a list of their types, into a table builder.
     *
     * @param columnNames the column names
     * @param columnTypes the column types
     * @param builder the builder that gets the information passed in
     */
    protected void passColumnsToTableBuilder(List<String> columnNames, List<String> columnTypes, Table.Builder builder) {
        int index = 0;
        for (final String columnName : columnNames) {
            builder.column(columnName, columnTypes.get(index));
            ++index;
        }
    }

    /**
     * Get an instance of {@link Identifier}.
     * 
     * @param name identifier id
     * @return instance of {@link Identifier}
     */
    protected abstract Identifier getIdentifier(String name);

    @Override
    // [impl->dsn~dropping-schemas~2]
    public void drop() {
        getWriter().drop(this);
        this.tables.clear();
    }
}