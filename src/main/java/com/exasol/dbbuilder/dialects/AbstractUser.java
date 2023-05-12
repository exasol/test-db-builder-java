package com.exasol.dbbuilder.dialects;

import java.util.*;

import com.exasol.db.Identifier;

/**
 * This class contains common logic for a database user.
 */
public abstract class AbstractUser extends AbstractDatabaseObject implements User {
    /** Password */
    protected final String password;
    /** Object privileges */
    protected final Map<DatabaseObject, ObjectPrivilege[]> objectPrivileges = new HashMap<>();
    /** Global privileges */
    protected final Set<GlobalPrivilege> globalPrivileges = new HashSet<>();

    /**
     * Create a new database user with a default password.
     * <p>
     * This method creates a user with a password derived from the user name. Note that this is only acceptable in the
     * scope of testing for which the TDDB is made. Never use something like this in production code!
     * </p>
     *
     * @param name user name
     */
    protected AbstractUser(final Identifier name) {
        super(name, false);
        this.password = name + "PWD";
    }

    /**
     * Create a new database user.
     *
     * @param name     user name
     * @param password login password
     */
    protected AbstractUser(final Identifier name, final String password) {
        super(name, false);
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Map<DatabaseObject, ObjectPrivilege[]> getObjectPrivileges() {
        return Collections.unmodifiableMap(this.objectPrivileges);
    }

    @Override
    public Set<GlobalPrivilege> getGlobalPrivileges() {
        return Collections.unmodifiableSet(this.globalPrivileges);
    }

    /**
     * Get a {@link DatabaseObjectWriter}.
     *
     * @return {@link DatabaseObjectWriter}
     */
    protected abstract DatabaseObjectWriter getWriter();

    @Override
    public User grant(final DatabaseObject object, final ObjectPrivilege... privileges) {
        verifyNotDeleted();
        this.objectPrivileges.put(object, privileges);
        getWriter().write(this, object, privileges);
        return this;
    }

    @Override
    public User grant(final GlobalPrivilege... privileges) {
        verifyNotDeleted();
        this.globalPrivileges.addAll(Set.of(privileges));
        getWriter().write(this, privileges);
        return this;
    }

    @Override
    // [impl->dsn~dropping-users~1]
    protected void dropInternally() {
        getWriter().drop(this);
    }
}
