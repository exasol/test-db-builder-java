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
     * Get the type of the database object.
     *
     * @return type of the database object
     */
    public String getType();

    /**
     * Check if this object has a parent object that sets the scope within the database
     *
     * @return {@code true} if the object has a parent
     */
    public boolean hasParent();

    /**
     * Get the parent object.
     *
     * @return parent object
     */
    public DatabaseObject getParent();

    /**
     * Check whether the script is a control object for a database object that already existed in the database.
     *
     * @return {@code true} if this is a control object attached to an existing database object
     */
    boolean isOwned();
}