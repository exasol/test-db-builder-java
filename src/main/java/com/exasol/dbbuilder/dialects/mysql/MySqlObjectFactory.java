package com.exasol.dbbuilder.dialects.mysql;

import java.nio.file.Path;
import java.sql.Connection;

import com.exasol.dbbuilder.dialects.*;
import com.exasol.dbbuilder.dialects.exasol.ExasolSchema;

/**
 * Factory for a top-level database object.
 */
// [impl->dsn~mysql-object-factory~1]
public final class MySqlObjectFactory implements DatabaseObjectFactory {
    private final MySqlImmediateDatabaseObjectWriter writer;
    private final QuoteApplier quoteApplier;

    /**
     * Create a new {@link MySqlObjectFactory} instance.
     *
     * @param connection JDBC connection
     */
    public MySqlObjectFactory(final Connection connection) {
        this.writer = new MySqlImmediateDatabaseObjectWriter(connection);
        this.quoteApplier = new MySqlQuoteApplier();
    }

    @Override
    public User createUser(final String name) {
        return new MySqlUser(this.writer, this.quoteApplier, name);
    }

    @Override
    public User createUser(final String name, final String password) {
        return new MySqlUser(this.writer, this.quoteApplier, name, password);
    }

    @Override
    public User createLoginUser(final String name) {
        return createUser(name);
    }

    @Override
    public User createLoginUser(final String name, final String password) {
        return createUser(name, password);
    }

    /**
     * Create a new database schema.
     *
     * @param name name of the schema
     * @return new {@link ExasolSchema} instance
     */
    public MySqlSchema createSchema(final String name) {
        return new MySqlSchema(this.writer, this.quoteApplier, name);
    }

    @Override
    public void executeSqlFile(final Path... sqlFiles) {
        this.writer.executeSqlFile(sqlFiles);
    }
}