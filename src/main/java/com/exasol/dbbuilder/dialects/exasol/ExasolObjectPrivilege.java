package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.dbbuilder.dialects.ObjectPrivilege;

/**
 * Exasol object privilege as used in a {@code GRANT}.
 * 
 * @see <a href="https://docs.exasol.com/database_concepts/privileges/details_rights_management.htm#List">Exasol
 *      documentation</a>*
 */
public enum ExasolObjectPrivilege implements ObjectPrivilege {
    SELECT, INSERT, DELETE, UPDATE, ALTER, REFERENCES, EXECUTE
}