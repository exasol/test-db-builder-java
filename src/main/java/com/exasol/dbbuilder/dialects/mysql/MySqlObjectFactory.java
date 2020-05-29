package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.DatabaseObjectFactory;
import com.exasol.dbbuilder.dialects.User;
import com.exasol.dbbuilder.dialects.exasol.ExasolSchema;

import java.nio.file.Path;
import java.sql.Connection;

/**
 * Factory for a top-level database object.
 */
public final class MySqlObjectFactory implements DatabaseObjectFactory {
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
    // [impl->dsn~creating-database-users~1]
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
        return createUser(name);
    }

    /**
     * Create a new database schema.
     *
     * @param name name of the schema
     * @return new {@link ExasolSchema} instance
     */
    public MySqlSchema createSchema(final String name) {
        return new MySqlSchema(this.writer, name);
    }

    @Override
    // [impl->dsn~creating-objects-through-sql-files~1]
    public void executeSqlFile(final Path... sqlFiles) {
        this.writer.executeSqlFile(sqlFiles);
    }
}