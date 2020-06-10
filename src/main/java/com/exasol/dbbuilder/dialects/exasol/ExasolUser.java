package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.*;

/**
 * Exasol database user.
 */
public class ExasolUser extends AbstractUser {
    private final ExasolImmediateDatabaseObjectWriter writer;

    /**
     * Create a new database user with a default password.
     * <p>
     * 
     * @param writer database object writer
     * @param name   user name
     */
    public ExasolUser(final ExasolImmediateDatabaseObjectWriter writer, final String name) {
        super(ExasolIdentifier.of(name));
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
    public ExasolUser(final ExasolImmediateDatabaseObjectWriter writer, final String name, final String password) {
        super(ExasolIdentifier.of(name), password);
        this.writer = writer;
        writer.write(this);
    }

    @Override
    public User grantAllAccess(final DatabaseObject object) {
        super.objectPrivileges.put(object, ExasolObjectPrivilege.values());
        this.writer.write(this, object, ExasolObjectPrivilege.values());
        return this;
    }

    @Override
    protected DatabaseObjectWriter getWriter() {
        return this.writer;
    }
}