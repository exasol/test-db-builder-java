package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.ObjectPrivilege;

/**
 * MySQL object privilege as used in a {@code GRANT}.
 *
 * @see <a href="https://dev.mysql.com/doc/refman/8.0/en/grant.html">MySQL documentation</a>
 */
public enum MySqlObjectPrivilege implements ObjectPrivilege {
    /**
     * Alter MY_SQL object privilege.
     */
    ALTER,
    /**
     * Alter routine MY_SQL object privilege.
     */
    ALTER_ROUTINE,
    /**
     * Create MY_SQL object privilege.
     */
    CREATE,
    /**
     * Create routine MY_SQL object privilege.
     */
    CREATE_ROUTINE,
    /**
     * Create temporary tables MY_SQL object privilege.
     */
    CREATE_TEMPORARY_TABLES,
    /**
     * Create view MY_SQL object privilege.
     */
    CREATE_VIEW,
    /**
     * Delete MY_SQL object privilege.
     */
    DELETE,
    /**
     * Drop MY_SQL object privilege.
     */
    DROP,
    /**
     * Event MY_SQL object privilege.
     */
    EVENT,
    /**
     * Execute MY_SQL object privilege.
     */
    EXECUTE,
    /**
     * Grant option MY_SQL object privilege.
     */
    GRANT_OPTION,
    /**
     * Index MY_SQL object privilege.
     */
    INDEX,
    /**
     * Insert MY_SQL object privilege.
     */
    INSERT,
    /**
     * Lock tables MY_SQL object privilege.
     */
    LOCK_TABLES,
    /**
     * References MY_SQL object privilege.
     */
    REFERENCES,
    /**
     * Select MY_SQL object privilege.
     */
    SELECT,
    /**
     * Show view MY_SQL object privilege.
     */
    SHOW_VIEW,
    /**
     * Trigger MY_SQL object privilege.
     */
    TRIGGER,
    /**
     * Update MY_SQL object privilege.
     */
    UPDATE
}