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
     *
     * @param name
     */
    public Schema(final DatabaseObjectWriter writer, final String name) {
        super(writer, name);
        writer.write(this);
    }

    @Override
    public String getType() {
        return "table";
    }

    @Override
    public String getFullyQualifiedName() {
        return this.name;
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
     * Create a table with two columns.
     *
     * @param tableName   name of the table
     * @param colum1Name  name of the first column
     * @param column1Type type of the first column
     * @param column2Name name of the second column
     * @param column2Type type of the second column
     * @return table
     */
    public Table createTable(final String tableName, final String colum1Name, final String column1Type,
            final String column2Name, final String column2Type) {
        final Table table = Table.builder(this.writer, this, tableName) //
                .column(colum1Name, column1Type) //
                .column(column2Name, column2Type) //
                .build();
        this.tables.add(table);
        return table;
    }

    /**
     * Create a builder for a Virtual Schema.
     *
     * @param name name of the Virtual Schema
     * @return builder
     */
    public VirtualSchema.Builder createVirtualSchemaBuilder(final String name) {
        return VirtualSchema.builder(this.writer, this, name);
    }
}