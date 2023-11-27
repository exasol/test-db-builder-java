package com.exasol.dbbuilder.dialects.postgres;

import com.exasol.dbbuilder.dialects.*;
import com.exasol.errorreporting.ExaError;

/**
 * PostgreSQL {@link User}.
 */
public class PostgreSqlUser extends AbstractUser {
    private final PostgreSqlImmediateDatabaseObjectWriter writer;

    /**
     * Create a PostgreSQL user with password.
     * 
     * @param writer   object writer
     * @param name     username
     * @param password password
     */
    PostgreSqlUser(final PostgreSqlImmediateDatabaseObjectWriter writer, final PostgreSqlIdentifier name,
            final String password) {
        super(name, password);
        this.writer = writer;
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        verifyNotDeleted();
        return this.writer;
    }

    @Override
    public User grantAllAccess(final DatabaseObject object) {
        throw new UnsupportedOperationException(ExaError.messageBuilder("E-TDBJ-12")
                .message("Creating users with privileges is not implemented in this version of the test-db-builder.")
                .toString());
    }
}
