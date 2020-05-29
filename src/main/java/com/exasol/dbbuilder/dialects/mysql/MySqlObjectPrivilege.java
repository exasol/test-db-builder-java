package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.ObjectPrivilege;

/**
 * MySQL object privilege as used in a {@code GRANT}.
 */
public enum MySqlObjectPrivilege implements ObjectPrivilege {
    ALTER, ALTER_ROUTINE, CREATE, CREATE_ROUTINE, CREATE_TEMPORARY_TABLES, CREATE_VIEW, DELETE, DROP, EVENT, EXECUTE,
    FILE, GRANT_OPTION, INDEX, INSERT, LOCK_TABLES, REFERENCES, SELECT, SHOW_VIEW, TRIGGER, UPDATE
}