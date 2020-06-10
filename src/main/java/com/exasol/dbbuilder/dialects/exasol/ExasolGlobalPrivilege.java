package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.dbbuilder.dialects.GlobalPrivilege;

/**
 * Exasol global privilege as used in a {@code GRANT}.
 * 
 * @see <a href="https://docs.exasol.com/database_concepts/privileges/details_rights_management.htm#List">Exasol
 *      documentation</a>*
 */
public enum ExasolGlobalPrivilege implements GlobalPrivilege {
    GRANT_ANY_OBJECT_PRIVILEGE, GRANT_ANY_PRIVILEGE, GRANT_ANY_PRIORITY_GROUP, MANAGE_PRIORITY_GROUPS, CREATE_SESSION,
    KILL_ANY_SESSION, ALTER_SYSTEM, IMPORT, EXPORT, CREATE_USER, ALTER_USER, DROP_USER, IMPERSONATE_ANY_USER,
    CREATE_ROLE, DROP_ANY_ROLE, GRANT_ANY_ROLE, CREATE_CONNECTION, ALTER_ANY_CONNECTION, DROP_ANY_CONNECTION,
    GRANT_ANY_CONNECTION, USE_ANY_CONNECTION, ACCESS_ANY_CONNECTION, CREATE_SCHEMA, ALTER_ANY_SCHEMA, DROP_ANY_SCHEMA,
    CREATE_VIRTUAL_SCHEMA, ALTER_ANY_VIRTUAL_SCHEMA, ALTER_ANY_VIRTUAL_SCHEMA_REFRESH, DROP_ANY_VIRTUAL_SCHEMA,
    CREATE_TABLE, CREATE_ANY_TABLE, ALTER_ANY_TABLE, DELETE_ANY_TABLE, DROP_ANY_TABLE, INSERT_ANY_TABLE,
    SELECT_ANY_TABLE, SELECT_ANY_DICTIONARY, UPDATE_ANY_TABLE, CREATE_VIEW, CREATE_ANY_VIEW, DROP_ANY_VIEW,
    CREATE_FUNCTION, CREATE_ANY_FUNCTION, DROP_ANY_FUNCTION, EXECUTE_ANY_FUNCTION, CREATE_SCRIPT, CREATE_ANY_SCRIPT,
    DROP_ANY_SCRIPT, EXECUTE_ANY_SCRIPT
}