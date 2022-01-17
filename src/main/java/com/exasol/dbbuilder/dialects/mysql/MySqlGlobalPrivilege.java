package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.GlobalPrivilege;

/**
 * MySQL global privilege as used in a {@code GRANT}.
 * 
 * @see <a href="https://dev.mysql.com/doc/refman/8.0/en/grant.html">MySQL documentation</a>
 */
public enum MySqlGlobalPrivilege implements GlobalPrivilege {
    /**
     * All MY_SQL global privilege.
     */
    ALL,
    /**
     * Alter MY_SQL global privilege.
     */
    ALTER,
    /**
     * Alter routine MY_SQL global privilege.
     */
    ALTER_ROUTINE,
    /**
     * Create MY_SQL global privilege.
     */
    CREATE,
    /**
     * Create role MY_SQL global privilege.
     */
    CREATE_ROLE,
    /**
     * Create routine MY_SQL global privilege.
     */
    CREATE_ROUTINE,
    /**
     * Create tablespace MY_SQL global privilege.
     */
    CREATE_TABLESPACE,
    /**
     * Create temporary tables MY_SQL global privilege.
     */
    CREATE_TEMPORARY_TABLES,
    /**
     * Create user MY_SQL global privilege.
     */
    CREATE_USER,
    /**
     * Create view MY_SQL global privilege.
     */
    CREATE_VIEW,
    /**
     * Delete MY_SQL global privilege.
     */
    DELETE,
    /**
     * Drop MY_SQL global privilege.
     */
    DROP,
    /**
     * Drop role MY_SQL global privilege.
     */
    DROP_ROLE,
    /**
     * Event MY_SQL global privilege.
     */
    EVENT,
    /**
     * Execute MY_SQL global privilege.
     */
    EXECUTE,
    /**
     * File MY_SQL global privilege.
     */
    FILE,
    /**
     * Grant option MY_SQL global privilege.
     */
    GRANT_OPTION,
    /**
     * Index MY_SQL global privilege.
     */
    INDEX,
    /**
     * Insert MY_SQL global privilege.
     */
    INSERT,
    /**
     * Lock tables MY_SQL global privilege.
     */
    LOCK_TABLES,
    /**
     * Process MY_SQL global privilege.
     */
    PROCESS,
    /**
     * Proxy MY_SQL global privilege.
     */
    PROXY,
    /**
     * References MY_SQL global privilege.
     */
    REFERENCES,
    /**
     * Reload MY_SQL global privilege.
     */
    RELOAD,
    /**
     * Replication client MY_SQL global privilege.
     */
    REPLICATION_CLIENT,
    /**
     * Replication slave MY_SQL global privilege.
     */
    REPLICATION_SLAVE,
    /**
     * Select MY_SQL global privilege.
     */
    SELECT,
    /**
     * Show databases MY_SQL global privilege.
     */
    SHOW_DATABASES,
    /**
     * Show view MY_SQL global privilege.
     */
    SHOW_VIEW,
    /**
     * Shutdown MY_SQL global privilege.
     */
    SHUTDOWN,
    /**
     * Super MY_SQL global privilege.
     */
    SUPER,
    /**
     * Trigger MY_SQL global privilege.
     */
    TRIGGER,
    /**
     * Update MY_SQL global privilege.
     */
    UPDATE,
    /**
     * Usage MY_SQL global privilege.
     */
    USAGE
}