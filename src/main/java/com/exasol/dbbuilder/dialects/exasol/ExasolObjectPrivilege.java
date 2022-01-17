package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.dbbuilder.dialects.ObjectPrivilege;

/**
 * Exasol object privilege as used in a {@code GRANT}.
 *
 * @see <a href="https://docs.exasol.com/database_concepts/privileges/details_rights_management.htm#List">Exasol
 *      documentation</a>*
 */
public enum ExasolObjectPrivilege implements ObjectPrivilege {
    /**
     * Select Exasol object privilege.
     */
    SELECT,
    /**
     * Insert Exasol object privilege.
     */
    INSERT,
    /**
     * Delete Exasol object privilege.
     */
    DELETE,
    /**
     * Update Exasol object privilege.
     */
    UPDATE,
    /**
     * Alter Exasol object privilege.
     */
    ALTER,
    /**
     * References Exasol object privilege.
     */
    REFERENCES,
    /**
     * Execute Exasol object privilege.
     */
    EXECUTE
}