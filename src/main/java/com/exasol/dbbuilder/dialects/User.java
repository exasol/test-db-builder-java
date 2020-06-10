package com.exasol.dbbuilder.dialects;

import java.util.Map;
import java.util.Set;

/**
 * Database user.
 */
public interface User extends DatabaseObject {
    /**
     * Get the user's password.
     *
     * @return password
     */
    public String getPassword();

    /**
     * Get the objectPrivileges of the user.
     *
     * @return object privileges
     */
    public Map<DatabaseObject, ObjectPrivilege[]> getObjectPrivileges();

    /**
     * Get the systemPrivileges of the user.
     *
     * @return system privileges
     */
    public Set<GlobalPrivilege> getGlobalPrivileges();

    /**
     * Grant the user access to all aspects of a database schema.
     *
     * @param object database object
     * @return {@link User} instance for fluent programming
     */
    public User grantAllAccess(DatabaseObject object);

    /**
     * Grant the user access to a database schema with the given privileges.
     *
     * @param object     database object
     * @param privileges privileges to grant the user
     * @return {@link User} instance for fluent programming
     */
    public User grant(DatabaseObject object, ObjectPrivilege... privileges);

    /**
     * Grant system privileges to a user.
     *
     * @param privileges system privileges
     * @return {@link User} instance for fluent programming
     */
    // [impl->dsn~granting-system-privileges-to-database-users~1]
    public User grant(GlobalPrivilege... privileges);

    @Override
    public default String getType() {
        return "user";
    }

    @Override
    public default boolean hasParent() {
        return false;
    }

    @Override
    public default DatabaseObject getParent() {
        throw new DatabaseObjectException(this,
                "Illegal attempt to access parent object of a USER which is a top-level object.");
    }
}