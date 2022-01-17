package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.dbbuilder.dialects.GlobalPrivilege;

/**
 * Exasol global privilege as used in a {@code GRANT}.
 *
 * @see <a href="https://docs.exasol.com/database_concepts/privileges/details_rights_management.htm#List">Exasol
 *      documentation</a>*
 */
public enum ExasolGlobalPrivilege implements GlobalPrivilege {
    /**
     * Grant any object privilege Exasol global privilege.
     */
    GRANT_ANY_OBJECT_PRIVILEGE,
    /**
     * Grant any privilege Exasol global privilege.
     */
    GRANT_ANY_PRIVILEGE,
    /**
     * Grant any priority group Exasol global privilege.
     */
    GRANT_ANY_PRIORITY_GROUP,
    /**
     * Manage priority groups Exasol global privilege.
     */
    MANAGE_PRIORITY_GROUPS,
    /**
     * Create session Exasol global privilege.
     */
    CREATE_SESSION,
    /**
     * Kill any session Exasol global privilege.
     */
    KILL_ANY_SESSION,
    /**
     * Alter system Exasol global privilege.
     */
    ALTER_SYSTEM,
    /**
     * Import Exasol global privilege.
     */
    IMPORT,
    /**
     * Export Exasol global privilege.
     */
    EXPORT,
    /**
     * Create user Exasol global privilege.
     */
    CREATE_USER,
    /**
     * Alter user Exasol global privilege.
     */
    ALTER_USER,
    /**
     * Drop user Exasol global privilege.
     */
    DROP_USER,
    /**
     * Impersonate any user Exasol global privilege.
     */
    IMPERSONATE_ANY_USER,
    /**
     * Create role Exasol global privilege.
     */
    CREATE_ROLE,
    /**
     * Drop any role Exasol global privilege.
     */
    DROP_ANY_ROLE,
    /**
     * Grant any role Exasol global privilege.
     */
    GRANT_ANY_ROLE,
    /**
     * Create connection Exasol global privilege.
     */
    CREATE_CONNECTION,
    /**
     * Alter any connection Exasol global privilege.
     */
    ALTER_ANY_CONNECTION,
    /**
     * Drop any connection Exasol global privilege.
     */
    DROP_ANY_CONNECTION,
    /**
     * Grant any connection Exasol global privilege.
     */
    GRANT_ANY_CONNECTION,
    /**
     * Use any connection Exasol global privilege.
     */
    USE_ANY_CONNECTION,
    /**
     * Access any connection Exasol global privilege.
     */
    ACCESS_ANY_CONNECTION,
    /**
     * Create schema Exasol global privilege.
     */
    CREATE_SCHEMA,
    /**
     * Alter any schema Exasol global privilege.
     */
    ALTER_ANY_SCHEMA,
    /**
     * Drop any schema Exasol global privilege.
     */
    DROP_ANY_SCHEMA,
    /**
     * Create virtual schema Exasol global privilege.
     */
    CREATE_VIRTUAL_SCHEMA,
    /**
     * Alter any virtual schema Exasol global privilege.
     */
    ALTER_ANY_VIRTUAL_SCHEMA,
    /**
     * Alter any virtual schema refresh Exasol global privilege.
     */
    ALTER_ANY_VIRTUAL_SCHEMA_REFRESH,
    /**
     * Drop any virtual schema Exasol global privilege.
     */
    DROP_ANY_VIRTUAL_SCHEMA,
    /**
     * Create table Exasol global privilege.
     */
    CREATE_TABLE,
    /**
     * Create any table Exasol global privilege.
     */
    CREATE_ANY_TABLE,
    /**
     * Alter any table Exasol global privilege.
     */
    ALTER_ANY_TABLE,
    /**
     * Delete any table Exasol global privilege.
     */
    DELETE_ANY_TABLE,
    /**
     * Drop any table Exasol global privilege.
     */
    DROP_ANY_TABLE,
    /**
     * Insert any table Exasol global privilege.
     */
    INSERT_ANY_TABLE,
    /**
     * Select any table Exasol global privilege.
     */
    SELECT_ANY_TABLE,
    /**
     * Select any dictionary Exasol global privilege.
     */
    SELECT_ANY_DICTIONARY,
    /**
     * Update any table Exasol global privilege.
     */
    UPDATE_ANY_TABLE,
    /**
     * Create view Exasol global privilege.
     */
    CREATE_VIEW,
    /**
     * Create any view Exasol global privilege.
     */
    CREATE_ANY_VIEW,
    /**
     * Drop any view Exasol global privilege.
     */
    DROP_ANY_VIEW,
    /**
     * Create function Exasol global privilege.
     */
    CREATE_FUNCTION,
    /**
     * Create any function Exasol global privilege.
     */
    CREATE_ANY_FUNCTION,
    /**
     * Drop any function Exasol global privilege.
     */
    DROP_ANY_FUNCTION,
    /**
     * Execute any function Exasol global privilege.
     */
    EXECUTE_ANY_FUNCTION,
    /**
     * Create script Exasol global privilege.
     */
    CREATE_SCRIPT,
    /**
     * Create any script Exasol global privilege.
     */
    CREATE_ANY_SCRIPT,
    /**
     * Drop any script Exasol global privilege.
     */
    DROP_ANY_SCRIPT,
    /**
     * Execute any script Exasol global privilege.
     */
    EXECUTE_ANY_SCRIPT
}