package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.ObjectPrivilege;

/**
 * MySQL object privilege as used in a {@code GRANT}.
 * 
 * @see <a href="https://dev.mysql.com/doc/refman/8.0/en/grant.html">MySQL documentation</a>
 */
public enum MySqlObjectPrivilege implements ObjectPrivilege {
    ALTER, ALTER_ROUTINE, CREATE, CREATE_ROUTINE, CREATE_TEMPORARY_TABLES, CREATE_VIEW, DELETE, DROP, EVENT, EXECUTE,
    GRANT_OPTION, INDEX, INSERT, LOCK_TABLES, REFERENCES, SELECT, SHOW_VIEW, TRIGGER, UPDATE
}