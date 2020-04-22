package com.exasol.dbbuilder;

/**
 * Privilege as used in a {@code GRANT}.
 */
public enum ObjectPrivilege {
    SELECT, INSERT, DELETE, UPDATE, ALTER, REFERENCES, EXECUTE
}