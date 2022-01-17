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
     * Grant any object privilege exasol global privilege.
     */
    GRANT_ANY_OBJECT_PRIVILEGE,
    /**
     * Grant any privilege exasol global privilege.
     */
    GRANT_ANY_PRIVILEGE,
    /**
     * Grant any priority group exasol global privilege.
     */
    GRANT_ANY_PRIORITY_GROUP,
    /**
     * Manage priority groups exasol global privilege.
     */
    MANAGE_PRIORITY_GROUPS,
    /**
     * Create session exasol global privilege.
     */
    CREATE_SESSION,
    /**
     * Kill any session exasol global privilege.
     */
    KILL_ANY_SESSION,
    /**
     * Alter system exasol global privilege.
     */
    ALTER_SYSTEM,
    /**
     * Import exasol global privilege.
     */
    IMPORT,
    /**
     * Export exasol global privilege.
     */
    EXPORT,
    /**
     * Create user exasol global privilege.
     */
    CREATE_USER,
    /**
     * Alter user exasol global privilege.
     */
    ALTER_USER,
    /**
     * Drop user exasol global privilege.
     */
    DROP_USER,
    /**
     * Impersonate any user exasol global privilege.
     */
    IMPERSONATE_ANY_USER,
    /**
     * Create role exasol global privilege.
     */
    CREATE_ROLE,
    /**
     * Drop any role exasol global privilege.
     */
    DROP_ANY_ROLE,
    /**
     * Grant any role exasol global privilege.
     */
    GRANT_ANY_ROLE,
    /**
     * Create connection exasol global privilege.
     */
    CREATE_CONNECTION,
    /**
     * Alter any connection exasol global privilege.
     */
    ALTER_ANY_CONNECTION,
    /**
     * Drop any connection exasol global privilege.
     */
    DROP_ANY_CONNECTION,
    /**
     * Grant any connection exasol global privilege.
     */
    GRANT_ANY_CONNECTION,
    /**
     * Use any connection exasol global privilege.
     */
    USE_ANY_CONNECTION,
    /**
     * Access any connection exasol global privilege.
     */
    ACCESS_ANY_CONNECTION,
    /**
     * Create schema exasol global privilege.
     */
    CREATE_SCHEMA,
    /**
     * Alter any schema exasol global privilege.
     */
    ALTER_ANY_SCHEMA,
    /**
     * Drop any schema exasol global privilege.
     */
    DROP_ANY_SCHEMA,
    /**
     * Create virtual schema exasol global privilege.
     */
    CREATE_VIRTUAL_SCHEMA,
    /**
     * Alter any virtual schema exasol global privilege.
     */
    ALTER_ANY_VIRTUAL_SCHEMA,
    /**
     * Alter any virtual schema refresh exasol global privilege.
     */
    ALTER_ANY_VIRTUAL_SCHEMA_REFRESH,
    /**
     * Drop any virtual schema exasol global privilege.
     */
    DROP_ANY_VIRTUAL_SCHEMA,
    /**
     * Create table exasol global privilege.
     */
    CREATE_TABLE,
    /**
     * Create any table exasol global privilege.
     */
    CREATE_ANY_TABLE,
    /**
     * Alter any table exasol global privilege.
     */
    ALTER_ANY_TABLE,
    /**
     * Delete any table exasol global privilege.
     */
    DELETE_ANY_TABLE,
    /**
     * Drop any table exasol global privilege.
     */
    DROP_ANY_TABLE,
    /**
     * Insert any table exasol global privilege.
     */
    INSERT_ANY_TABLE,
    /**
     * Select any table exasol global privilege.
     */
    SELECT_ANY_TABLE,
    /**
     * Select any dictionary exasol global privilege.
     */
    SELECT_ANY_DICTIONARY,
    /**
     * Update any table exasol global privilege.
     */
    UPDATE_ANY_TABLE,
    /**
     * Create view exasol global privilege.
     */
    CREATE_VIEW,
    /**
     * Create any view exasol global privilege.
     */
    CREATE_ANY_VIEW,
    /**
     * Drop any view exasol global privilege.
     */
    DROP_ANY_VIEW,
    /**
     * Create function exasol global privilege.
     */
    CREATE_FUNCTION,
    /**
     * Create any function exasol global privilege.
     */
    CREATE_ANY_FUNCTION,
    /**
     * Drop any function exasol global privilege.
     */
    DROP_ANY_FUNCTION,
    /**
     * Execute any function exasol global privilege.
     */
    EXECUTE_ANY_FUNCTION,
    /**
     * Create script exasol global privilege.
     */
    CREATE_SCRIPT,
    /**
     * Create any script exasol global privilege.
     */
    CREATE_ANY_SCRIPT,
    /**
     * Drop any script exasol global privilege.
     */
    DROP_ANY_SCRIPT,
    /**
     * Execute any script exasol global privilege.
     */
    EXECUTE_ANY_SCRIPT
}