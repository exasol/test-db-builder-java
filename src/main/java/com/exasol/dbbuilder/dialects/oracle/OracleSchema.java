package com.exasol.dbbuilder.dialects.oracle;

import java.util.List;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.*;
import com.exasol.errorreporting.ExaError;

/**
 * Oracle {@link Schema}.
 */
public class OracleSchema extends AbstractSchema {
    private final OracleImmediateDatabaseObjectWriter writer;

    /**
     * Create a new instance of {@link OracleSchema}.
     *
     * @param writer object writer
     * @param name   name of the schema
     */
    public OracleSchema(final OracleImmediateDatabaseObjectWriter writer, final Identifier name) {
        super(name);
        this.writer = writer;
        this.writer.write(this);
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        verifyNotDeleted();
        return this.writer;
    }

    @Override
    protected Identifier getIdentifier(final String name) {
        return OracleIdentifier.of(name);
    }

    @Override
    public OracleTable.Builder createTableBuilder(final String name) {
        verifyNotDeleted();
        return OracleTable.builder(getWriter(), this, getIdentifier(name));
    }

    @Override
    public Table createTable(final String name, final List<String> columnNames, final List<String> columnTypes) {
        verifyNotDeleted();
        if (columnNames.size() == columnTypes.size()) {
            final OracleTable.Builder builder = OracleTable.builder(getWriter(), this, getIdentifier(name));
            passColumnsToTableBuilder(columnNames, columnTypes, builder);
            final OracleTable table = builder.build();
            this.tables.add(table);
            return table;
        } else {
            throw new IllegalArgumentException(ExaError.messageBuilder("E-TDBJ-34").message(
                    "Got {{column names size}} column names but {{column types}} column types. Please provide the same number of parameters for both when creating a table.",
                    columnNames.size(), columnTypes.size()).toString());
        }
    }
}
