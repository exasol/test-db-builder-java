package com.exasol.dbbuilder.dialects.postgres;

import java.sql.Connection;

import com.exasol.dbbuilder.dialects.*;
import com.exasol.errorreporting.ExaError;

/**
 * PostgreSQL {@link DatabaseObjectWriter}.
 */
public class PostgreSqlImmediateDatabaseObjectWriter extends AbstractImmediateDatabaseObjectWriter {

    /**
     * Create a new instance of {@link PostgreSqlImmediateDatabaseObjectWriter}.
     * 
     * @param connectionToPostgres connection to the PostgreSQL database.
     */
    public PostgreSqlImmediateDatabaseObjectWriter(final Connection connectionToPostgres) {
        super(connectionToPostgres);
    }

    @Override
    protected String getQuotedColumnName(final String columnName) {
        return PostgreSqlIdentifier.of(columnName).quote();
    }

    @Override
    public void write(final User user) {
        writeToObject(user, "CREATE USER " + user.getFullyQualifiedName() + " PASSWORD '" + user.getPassword() + "'");
    }

    @Override
    public void write(final User user, final GlobalPrivilege... privileges) {
        throw new UnsupportedOperationException(ExaError.messageBuilder("E-TDBJ-11").message("Creating users with privileges is not implemented in this version of the test-db-builder.").toString());
    }

    @Override
    public void drop(final Schema schema) {
        writeToObject(schema, "DROP SCHEMA " + schema.getFullyQualifiedName() + " CASCADE");
    }
}
