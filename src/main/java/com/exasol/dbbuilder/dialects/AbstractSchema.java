package com.exasol.dbbuilder.dialects;

import java.util.ArrayList;
import java.util.List;

import com.exasol.db.Identifier;

/**
 * This class contains common logic for a database schema.
 */
public abstract class AbstractSchema extends AbstractDatabaseObject implements Schema {
    protected final List<Table> tables = new ArrayList<>();

    /**
     * Create a new database schema.
     *
     * @param name name of the database schema
     */
    public AbstractSchema(final Identifier name) {
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
        return Table.builder(getWriter(), this, getIdentifier(name));
    }

    @Override
    public Table createTable(final String name, final List<String> columnNames, final List<String> columnTypes) {
        if (columnNames.size() == columnTypes.size()) {
            final Table.Builder builder = Table.builder(getWriter(), this, getIdentifier(name));
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