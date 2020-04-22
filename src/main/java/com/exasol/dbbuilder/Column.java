package com.exasol.dbbuilder;

/**
 * Column of a database table.
 */
public class Column {
    private final String name;
    private final String type;

    /**
     * Create a new column in a database table.
     *
     * @param name name of the column
     * @param type type of the column
     */
    public Column(final String name, final String type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Get the column name.
     *
     * @return name of the column
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the column type.
     * 
     * @return column type
     */
    public String getType() {
        return this.type;
    }
}