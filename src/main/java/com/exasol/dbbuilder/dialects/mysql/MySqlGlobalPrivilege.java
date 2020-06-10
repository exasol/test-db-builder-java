package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.GlobalPrivilege;

/**
 * MySQL global privilege as used in a {@code GRANT}.
 * 
 * @see <a href="https://dev.mysql.com/doc/refman/8.0/en/grant.html">MySQL documentation</a>
 */
public enum MySqlGlobalPrivilege implements GlobalPrivilege {
    ALL, ALTER, ALTER_ROUTINE, CREATE, CREATE_ROLE, CREATE_ROUTINE, CREATE_TABLESPACE, CREATE_TEMPORARY_TABLES,
    CREATE_USER, CREATE_VIEW, DELETE, DROP, DROP_ROLE, EVENT, EXECUTE, FILE, GRANT_OPTION, INDEX, INSERT, LOCK_TABLES,
    PROCESS, PROXY, REFERENCES, RELOAD, REPLICATION_CLIENT, REPLICATION_SLAVE, SELECT, SHOW_DATABASES, SHOW_VIEW,
    SHUTDOWN, SUPER, TRIGGER, UPDATE, USAGE
}