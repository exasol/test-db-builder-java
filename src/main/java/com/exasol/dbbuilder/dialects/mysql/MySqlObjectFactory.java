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
        this(new MySqlImmediateDatabaseObjectWriter(connection));
    }

    MySqlObjectFactory(final MySqlImmediateDatabaseObjectWriter writer) {
        this.writer = writer;
    }

    @Override
    public User createUser(final String name) {
        return writeUser(new MySqlUser(this.writer, MySQLIdentifier.of(name)));
    }

    @Override
    public User createUser(final String name, final String password) {
        return writeUser(new MySqlUser(this.writer, MySQLIdentifier.of(name), password));
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
    public MySqlSchema createSchema(final String name) {
        final MySqlSchema schema = new MySqlSchema(this.writer, MySQLIdentifier.of(name));
        this.writer.write(schema);
        return schema;
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }
}
