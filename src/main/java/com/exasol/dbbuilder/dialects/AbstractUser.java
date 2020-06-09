package com.exasol.dbbuilder.dialects;

import java.util.*;

import com.exasol.db.Identifier;

/**
 * This class contains common logic for a database user.
 */
public abstract class AbstractUser extends AbstractDatabaseObject implements User {
    protected final String password;
    protected final Map<DatabaseObject, ObjectPrivilege[]> objectPrivileges = new HashMap<>();
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
    public AbstractUser(final Identifier name) {
        super(name, false);
        this.password = name + "PWD";
    }

    /**
     * Create a new database user.
     *
     * @param name     user name
     * @param password login password
     */
    public AbstractUser(final Identifier name, final String password) {
        super(name, false);
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Map<DatabaseObject, ObjectPrivilege[]> getObjectPrivileges() {
        return this.objectPrivileges;
    }

    @Override
    public Set<GlobalPrivilege> getGlobalPrivileges() {
        return this.globalPrivileges;
    }
}