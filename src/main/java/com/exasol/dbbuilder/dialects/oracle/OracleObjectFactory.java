package com.exasol.dbbuilder.dialects.oracle;

import com.exasol.dbbuilder.dialects.*;
import com.exasol.dbbuilder.dialects.oracle.OracleIdentifier;
import com.exasol.dbbuilder.dialects.oracle.OracleImmediateDatabaseObjectWriter;
import com.exasol.dbbuilder.dialects.oracle.OracleSchema;
import com.exasol.dbbuilder.dialects.oracle.OracleUser;

import java.sql.Connection;

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
        this.writer = new OracleImmediateDatabaseObjectWriter(connectionToOracle);
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }

    @Override
    public User createUser(final String name) {
        return new OracleUser(this.writer, OracleIdentifier.of(name));
    }

    @Override
    public User createUser(final String name, final String password) {
        return new OracleUser(this.writer, OracleIdentifier.of(name), password);
    }

    @Override
    public User createLoginUser(final String name) {
        return createUser(name);
    }

    @Override
    public User createLoginUser(final String name, final String password) {
        return createUser(name, password);
    }

    @Override
    public Schema createSchema(final String name) {
        return new OracleSchema(this.writer, OracleIdentifier.of(name));
    }
}
