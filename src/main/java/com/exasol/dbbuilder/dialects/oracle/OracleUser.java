package com.exasol.dbbuilder.dialects.oracle;

import com.exasol.dbbuilder.dialects.*;
import com.exasol.errorreporting.ExaError;

/**
 * Oracle {@link User}.
 */
public class OracleUser extends AbstractUser {
    private final OracleImmediateDatabaseObjectWriter writer;

    /**
     * Create an Oracle user with password.
     *
     * @param writer   object writer
     * @param name     username
     * @param password password
     */
    OracleUser(final OracleImmediateDatabaseObjectWriter writer, final OracleIdentifier name, final String password) {
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
        throw new UnsupportedOperationException(ExaError.messageBuilder("E-TDBJ-31")
                .message("Creating users with privileges is not implemented in this version of the test-db-builder.")
                .toString());
    }
}
