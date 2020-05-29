package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.GlobalPrivilege;

/**
 * MySQL global privilege as used in a {@code GRANT}.
 */
public enum MySqlGlobalPrivilege implements GlobalPrivilege {
    ALL, CREATE_ROLE, CREATE_TABLESPACE, CREATE_USER, DROP_ROLE, PROCESS, PROXY, RELOAD, REPLICATION_CLIENT,
    REPLICATION_SLAVE, SHOW_DATABASES, SHUTDOWN, SUPER, USAGE
}