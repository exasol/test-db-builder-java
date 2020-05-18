package com.exasol.dbbuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

/**
 * Database user
 */
public class User extends AbstractDatabaseObject {
    private final String password;
    private final Map<DatabaseObject, ObjectPrivilege[]> objectPrivileges = new HashMap<>();
    private final Set<SystemPrivilege> systemPrivileges = new HashSet<>();

    /**
     * Create a new database user with a default password.
     * <p>
     * This method creates a user with a password derived from the user name. Note that this is only acceptable in the
     * scope of testing for which the TDDB is made. Never use something like this in production code!
     * </p>
     *
     * @param writer database object writer
     * @param name   user name
     */
    public User(final DatabaseObjectWriter writer, final String name) {
        super(writer, name, false);
        this.password = name + "PWD";
        writer.write(this);
    }

    /**
     * Create a new database user.
     *
     * @param writer   database object writer
     * @param name     user name
     * @param password login password
     */
    public User(final DatabaseObjectWriter writer, final String name, final String password) {
        super(writer, name, false);
        this.password = password;
        writer.write(this);
    }

    @Override
    public String getType() {
        return "user";
    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public DatabaseObject getParent() {
        throw new DatabaseObjectException(this,
                "Illegal attempt to access parent object of a USER which is a top-level object.");
    }

    /**
     * Get the user's password.
     *
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Get the objectPrivileges of the user.
     *
     * @return object privileges
     */
    public Map<DatabaseObject, ObjectPrivilege[]> getObjectPrivileges() {
        return this.objectPrivileges;
    }

    /**
     * Get the systemPrivileges of the user.
     *
     * @return system privileges
     */
    public Set<SystemPrivilege> getSystemPrivileges() {
        return this.systemPrivileges;
    }

    /**
     * Grant the user access to all aspects of a database schema.
     *
     * @param object database object
     * @return {@link User} instance for fluent programming
     */
    public User grantAllAccess(final DatabaseObject object) {
        this.objectPrivileges.put(object, ObjectPrivilege.values());
        this.writer.write(this, object, ObjectPrivilege.values());
        return this;
    }

    /**
     * Grant the user access to a database schema with the given privileges.
     *
     * @param object     database object
     * @param privileges privileges to grant the user
     * @return {@link User} instance for fluent programming
     */
    public User grant(final DatabaseObject object, final ObjectPrivilege... privileges) {
        this.objectPrivileges.put(object, privileges);
        this.writer.write(this, object, privileges);
        return this;
    }

    /**
     * Grant system privileges to a user.
     *
     * @param privileges system privileges
     * @return {@link User} instance for fluent programming
     */
    // [impl->dsn~granting-system-privileges-to-database-users~1]
    public User grant(final SystemPrivilege... privileges) {
        this.systemPrivileges.addAll(Set.of(privileges));
        this.writer.write(this, privileges);
        return this;
    }
}