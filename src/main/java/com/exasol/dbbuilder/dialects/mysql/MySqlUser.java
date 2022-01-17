package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.*;

/**
 * MySQL database user.
 */
public class MySqlUser extends AbstractUser {
    private final MySqlImmediateDatabaseObjectWriter writer;

    /**
     * Create a new database user with a default password.
     *
     * @param writer database object writer
     * @param name   username
     */
    public MySqlUser(final MySqlImmediateDatabaseObjectWriter writer, final Identifier name) {
        super(name);
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
    public MySqlUser(final MySqlImmediateDatabaseObjectWriter writer, final Identifier name, final String password) {
        super(name, password);
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