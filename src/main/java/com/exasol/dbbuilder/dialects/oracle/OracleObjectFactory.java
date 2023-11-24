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
        this.writer = writer;
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }

    @Override
    public User createUser(final String name) {
        return writeUser(new OracleUser(this.writer, OracleIdentifier.of(name)));
    }

    @Override
    public User createUser(final String name, final String password) {
        return writeUser(new OracleUser(this.writer, OracleIdentifier.of(name), password));
    }

    private User writeUser(final User user) {
        this.writer.write(user);
        return user;
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
        final OracleSchema schema = new OracleSchema(this.writer, OracleIdentifier.of(name));
        this.writer.write(schema);
        return schema;
    }
}
