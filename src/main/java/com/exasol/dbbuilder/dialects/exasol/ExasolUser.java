package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.*;

/**
 * Exasol database user.
 */
public class ExasolUser extends AbstractUser {
    private final ExasolImmediateDatabaseObjectWriter writer;

    /**
     * Create a new database user with a default password.
     * 
     * @param writer database object writer
     * @param name   user name
     */
    public ExasolUser(final ExasolImmediateDatabaseObjectWriter writer, final Identifier name) {
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
    public ExasolUser(final ExasolImmediateDatabaseObjectWriter writer, final Identifier name, final String password) {
        super(name, password);
        this.writer = writer;
        writer.write(this);
    }

    @Override
    public User grantAllAccess(final DatabaseObject object) {
        verifyNotDeleted();
        super.objectPrivileges.put(object, ExasolObjectPrivilege.values());
        this.writer.write(this, object, ExasolObjectPrivilege.values());
        return this;
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        verifyNotDeleted();
        return this.writer;
    }
}