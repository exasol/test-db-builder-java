package com.exasol.dbbuilder.dialects;

import java.util.Map;
import java.util.Set;

import com.exasol.errorreporting.ExaError;

/**
 * Database user.
 */
public interface User extends DatabaseObject {
    /**
     * Get the user's password.
     *
     * @return password
     */
    String getPassword();

    /**
     * Get the objectPrivileges of the user.
     *
     * @return object privileges
     */
    Map<DatabaseObject, ObjectPrivilege[]> getObjectPrivileges();

    /**
     * Get the systemPrivileges of the user.
     *
     * @return system privileges
     */
    Set<GlobalPrivilege> getGlobalPrivileges();

    /**
     * Grant the user access to all aspects of a database schema.
     *
     * @param object database object
     * @return {@link User} instance for fluent programming
     */
    User grantAllAccess(DatabaseObject object);

    /**
     * Grant the user access to a database schema with the given privileges.
     *
     * @param object     database object
     * @param privileges privileges to grant the user
     * @return {@link User} instance for fluent programming
     */
    User grant(DatabaseObject object, ObjectPrivilege... privileges);

    /**
     * Grant system privileges to a user.
     *
     * @param privileges system privileges
     * @return {@link User} instance for fluent programming
     */
    // [impl->dsn~granting-system-privileges-to-database-users~1]
    User grant(GlobalPrivilege... privileges);

    @Override
    default String getType() {
        return "user";
    }

    @Override
    default boolean hasParent() {
        return false;
    }

    @Override
    default DatabaseObject getParent() {
        throw new DatabaseObjectException(this,ExaError.messageBuilder("E-TDBJ-17").message("Illegal attempt to access parent object of a USER which is a top-level object.").toString());
    }
}