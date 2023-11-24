package com.exasol.dbbuilder.dialects.oracle;

import java.sql.Connection;

import com.exasol.dbbuilder.dialects.*;

/**
 * Oracle {@link DatabaseObjectFactory}.
 */
public class OracleObjectFactory extends AbstractObjectFactory {
    private final OracleImmediateDatabaseObjectWriter writer;

    /**
     * Create a new instance of {@link OracleObjectFactory}.
     *
     * @param connectionToOracle connection to the Oracle database.
     */
    public OracleObjectFactory(final Connection connectionToOracle) {
        this(new OracleImmediateDatabaseObjectWriter(connectionToOracle));
    }

    OracleObjectFactory(final OracleImmediateDatabaseObjectWriter writer) {
        super(writer);
        this.writer = writer;
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }

    @Override
    protected User createNewUser(final String name) {
        return new OracleUser(this.writer, OracleIdentifier.of(name));
    }

    @Override
    protected User createNewUser(final String name, final String password) {
        return new OracleUser(this.writer, OracleIdentifier.of(name), password);
    }

    @Override
    public Schema createSchema(final String name) {
        final OracleSchema schema = new OracleSchema(this.writer, OracleIdentifier.of(name));
        this.writer.write(schema);
        return schema;
    }
}
