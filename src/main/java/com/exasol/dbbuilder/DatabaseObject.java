package com.exasol.dbbuilder;

/**
 * Common interface for database objects.
 */
public interface DatabaseObject {
    /**
     * Get the name of the database object.
     *
     * @return object name
     */
    public String getName();

    /**
     * Get the name including the parent object names.
     *
     * @return fully qualified name
     */
    public String getFullyQualifiedName();

    /**
     * Get the type of the database object;
     *
     * @return type of the database object
     */
    public String getType();
}