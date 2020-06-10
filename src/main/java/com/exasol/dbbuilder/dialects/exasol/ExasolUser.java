package com.exasol.dbbuilder.dialects.exasol;

import java.util.Set;

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
     * @param writer       database object writer
     * @param quoteApplier instance of {@link QuoteApplier}
     * @param name         user name
     */
    public ExasolUser(final ExasolImmediateDatabaseObjectWriter writer, final QuoteApplier quoteApplier,
            final String name) {
        super(quoteApplier, name);
        this.writer = writer;
        this.writer.write(this);
    }

    /**
     * Create a new database user.
     *
     * @param writer       database object writer
     * @param quoteApplier instance of {@link QuoteApplier}*
     * @param name         user name
     * @param password     login password
     */
    public ExasolUser(final ExasolImmediateDatabaseObjectWriter writer, final QuoteApplier quoteApplier,
            final String name, final String password) {
        super(quoteApplier, name, password);
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