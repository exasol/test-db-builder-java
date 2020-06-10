package com.exasol.dbbuilder.dialects.mysql;

import java.util.Set;

import com.exasol.dbbuilder.dialects.*;

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
    public User grant(final DatabaseObject object, final ObjectPrivilege... privileges) {
        this.objectPrivileges.put(object, privileges);
        this.writer.write(this, object, privileges);
        return this;
    }

    @Override
    public User grant(final GlobalPrivilege... privileges) {
        this.globalPrivileges.addAll(Set.of(privileges));
        this.writer.write(this, privileges);
        return this;
    }
}