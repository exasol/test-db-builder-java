package com.exasol.dbbuilder.dialects;

/**
 * Common interface for privilege as used in a {@code GRANT}.
 */
interface Privilege {
    /**
     * Get a space-separated name of a privilege.
     * 
     * @return privilege as a string
     */
    public default String getSqlName() {
        return this.name().replace("_", " ");
    }

    /**
     * Get a privilege name as it is.
     * 
     * @return privilege as a string
     */
    public String name();
}