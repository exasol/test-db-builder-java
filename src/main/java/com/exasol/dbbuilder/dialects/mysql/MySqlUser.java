package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.AbstractUser;
import com.exasol.dbbuilder.dialects.DatabaseObject;
import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;
import com.exasol.dbbuilder.dialects.User;

/**
 * MySQL database user.
 */
public class MySqlUser extends AbstractUser {
    private final MySqlImmediateDatabaseObjectWriter writer;

    /**
     * Create a new database user with a default password.
     * <p>
     *
     * @param writer database object writer
     * @param name   user name
     */
    public MySqlUser(final MySqlImmediateDatabaseObjectWriter writer, final String name) {
        super(MySQLIdentifier.of(name));
        this.writer = writer;
        this.writer.write(this);
    }

    /**
     * Create a new database user.
     *
     * @param writer   database object writer
     * @param name     user name
     * @param password login password
     */
    public MySqlUser(final MySqlImmediateDatabaseObjectWriter writer, final String name, final String password) {
        super(MySQLIdentifier.of(name), password);
        this.writer = writer;
        this.writer.write(this);
    }

    @Override
    public User grantAllAccess(final DatabaseObject object) {
        super.objectPrivileges.put(object, MySqlObjectPrivilege.values());
        this.writer.write(this, object, MySqlObjectPrivilege.values());
        return this;
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }
}