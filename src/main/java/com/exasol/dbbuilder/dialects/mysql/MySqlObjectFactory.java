package com.exasol.dbbuilder.dialects.mysql;

import java.sql.Connection;

import com.exasol.dbbuilder.dialects.*;

/**
 * Factory for MySQL top-level database objects.
 */
// [impl->dsn~mysql-object-factory~1]
public final class MySqlObjectFactory extends AbstractObjectFactory {
    private final MySqlImmediateDatabaseObjectWriter writer;

    /**
     * Create a new {@link MySqlObjectFactory} instance.
     *
     * @param connection JDBC connection
     */
    public MySqlObjectFactory(final Connection connection) {
        this.writer = new MySqlImmediateDatabaseObjectWriter(connection);
    }

    @Override
    public User createUser(final String name) {
        return new MySqlUser(this.writer, name);
    }

    @Override
    public User createUser(final String name, final String password) {
        return new MySqlUser(this.writer, name, password);
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
    public MySqlSchema createSchema(final String name) {
        return new MySqlSchema(this.writer, name);
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }
}