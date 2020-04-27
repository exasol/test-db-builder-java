package com.exasol.dbbuilder;

/**
 * Privilege as used in a {@code GRANT}.
 */
public enum SystemPrivilege {
    CREATE_SESSION, KILL_ANY_SESSION;

    @Override
    public String toString() {
        return this.name().replace("_", " ");
    }
}