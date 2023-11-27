package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.*;

/**
 * MySQL database user.
 */
public class MySqlUser extends AbstractUser {
    private final MySqlImmediateDatabaseObjectWriter writer;

    /**
     * Create a new database user.
     *
     * @param writer   database object writer
     * @param name     user name
     * @param password login password
     */
    MySqlUser(final MySqlImmediateDatabaseObjectWriter writer, final MySQLIdentifier name, final String password) {
        super(name, password);
        this.writer = writer;
    }

    @Override
    public User grantAllAccess(final DatabaseObject object) {
        verifyNotDeleted();
        super.objectPrivileges.put(object, MySqlObjectPrivilege.values());
        this.writer.write(this, object, MySqlObjectPrivilege.values());
        return this;
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        verifyNotDeleted();
        return this.writer;
    }
}
