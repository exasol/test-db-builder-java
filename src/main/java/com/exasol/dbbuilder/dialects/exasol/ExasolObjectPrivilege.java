package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.dbbuilder.dialects.ObjectPrivilege;

/**
 * Exasol object privilege as used in a {@code GRANT}.
 */
public enum ExasolObjectPrivilege implements ObjectPrivilege {
    SELECT, INSERT, DELETE, UPDATE, ALTER, REFERENCES, EXECUTE
}