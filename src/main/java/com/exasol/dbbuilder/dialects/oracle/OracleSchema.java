package com.exasol.dbbuilder.dialects.oracle;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.AbstractSchema;
import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;
import com.exasol.dbbuilder.dialects.Schema;
import com.exasol.dbbuilder.dialects.oracle.OracleIdentifier;
import com.exasol.dbbuilder.dialects.oracle.OracleImmediateDatabaseObjectWriter;

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
        return this.writer;
    }

    @Override
    protected Identifier getIdentifier(final String name) {
        return OracleIdentifier.of(name);
    }
}
