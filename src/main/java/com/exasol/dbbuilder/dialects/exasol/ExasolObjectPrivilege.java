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
     * Select exasol object privilege.
     */
    SELECT,
    /**
     * Insert exasol object privilege.
     */
    INSERT,
    /**
     * Delete exasol object privilege.
     */
    DELETE,
    /**
     * Update exasol object privilege.
     */
    UPDATE,
    /**
     * Alter exasol object privilege.
     */
    ALTER,
    /**
     * References exasol object privilege.
     */
    REFERENCES,
    /**
     * Execute exasol object privilege.
     */
    EXECUTE
}