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
        super(writer);
        this.writer = writer;
    }

    @Override
    protected User createNewUser(final String name) {
        return new MySqlUser(this.writer, MySQLIdentifier.of(name));
    }

    @Override
    protected User createNewUser(final String name, final String password) {
        return new MySqlUser(this.writer, MySQLIdentifier.of(name), password);
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
