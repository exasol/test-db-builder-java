package com.exasol.dbbuilder.dialects;

/**
 * Exception for database object generation.
 */
public class DatabaseObjectException extends RuntimeException {
    private static final long serialVersionUID = 1526690962275232472L;
    /**
     * Fully qualified name of the database object that is the context of this exception
     * @serial include
     */
    private final String fullyQualifiedObjectName;
    /**
     * Type of the database object that is the context of this exception
     * @serial include
     */
    private final String objectType;

    /**
     * Create a new instance of a {@link DatabaseObjectException}.
     *
     * @param object  database object
     * @param message error message
     */
    public DatabaseObjectException(final DatabaseObject object, final String message) {
        super(message);
        this.fullyQualifiedObjectName = object.getFullyQualifiedName();
        this.objectType = object.getType();
    }

    /**
     * Create a new instance of a {@link DatabaseObjectException}.
     *
     * @param object database object
     * @param cause  reason we were unable to create the object
     */
    public DatabaseObjectException(final DatabaseObject object, final Throwable cause) {
        super("Unable to create " + object.getType() + " " + object.getFullyQualifiedName(), cause);
        this.fullyQualifiedObjectName = object.getFullyQualifiedName();
        this.objectType = object.getType();
    }

    /**
     * Create a new instance of a {@link DatabaseObjectException}.
     *
     * @param object  database object
     * @param message error message
     * @param cause   reason we were unable to create the object
     */
    public DatabaseObjectException(final DatabaseObject object, final String message, final Throwable cause) {
        super(message + " In the context of " + object.getType() + " " + object.getFullyQualifiedName(), cause);
        this.fullyQualifiedObjectName = object.getFullyQualifiedName();
        this.objectType = object.getType();
    }

    /**
     * Create a new instance of a {@link DatabaseObjectException}.
     *
     * @param message error message
     * @param cause   reason the error is thrown
     */
    public DatabaseObjectException(final String message, final Throwable cause) {
        super(message, cause);
        this.fullyQualifiedObjectName = null;
        this.objectType = null;
    }

    /**
     * Get the database object type
     *
     * @return object type
     */
    public String getObjectType() {
        return this.objectType;
    }

    /**
     * Get the database object name
     *
     * @return object name
     */
    public String getFullyQualifiedObjectName() {
        return this.fullyQualifiedObjectName;
    }
}