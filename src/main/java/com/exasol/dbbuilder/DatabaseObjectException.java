package com.exasol.dbbuilder;

/**
 * Exception for database object generation.
 */
public class DatabaseObjectException extends RuntimeException {
    private static final long serialVersionUID = 1526690962275232472L;
    /** @serial fully qualified name of the database object that is the context of this exception */
    private final String fullyQualifiedObjectName;
    /** @serial type of the database object that is the context of this exception */
    private final String objectType;

    /**
     * Create a new instance of a {@link DatabaseObjectException}
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
     * Create a new instance of a {@link DatabaseObjectException}
     *
     * @param object database object
     * @param cause  reason we were unable to create the object
     */
    public DatabaseObjectException(final DatabaseObject object, final Exception cause) {
        super("Unable to create " + object.getType() + " " + object.getFullyQualifiedName(), cause);
        this.fullyQualifiedObjectName = object.getFullyQualifiedName();
        this.objectType = object.getType();
    }

    /**
     * Create a new instance of a {@link DatabaseObjectException}
     *
     * @param object  database object
     * @param message error message
     * @param cause   reason we were unable to create the object
     */
    public DatabaseObjectException(final DatabaseObject object, final String message, final Exception cause) {
        super(message + " In the context of " + object.getType() + " " + object.getFullyQualifiedName(), cause);
        this.fullyQualifiedObjectName = object.getFullyQualifiedName();
        this.objectType = object.getType();
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