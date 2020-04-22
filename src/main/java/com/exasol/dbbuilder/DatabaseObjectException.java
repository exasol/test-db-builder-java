package com.exasol.dbbuilder;

/**
 * Exception for database object generation.
 */
public class DatabaseObjectException extends RuntimeException {
    private static final long serialVersionUID = -8044152273775485585L;
    private final DatabaseObject object;

    /**
     * Create a new instance of a {@link DatabaseObjectException}
     *
     * @param object  database object
     * @param message error message
     */
    public DatabaseObjectException(final DatabaseObject object, final String message) {
        super("Unable to create " + object.getType() + " \"" + object.getName() + "\"");
        this.object = object;
    }

    /**
     * Create a new instance of a {@link DatabaseObjectException}
     *
     * @param object database object
     * @param cause  reason we were unable to create the object
     */
    public DatabaseObjectException(final DatabaseObject object, final Exception cause) {
        super("Unable to create " + object.getType() + " \"" + object.getFullyQualifiedName() + "\"", cause);
        this.object = object;
    }

    /**
     * Create a new instance of a {@link DatabaseObjectException}
     *
     * @param message error message
     * @param object  database object
     * @param cause   reason we were unable to create the object
     */
    public DatabaseObjectException(final String message, final DatabaseObject object, final Exception cause) {
        super(message + "In the context of " + object.getType() + " \"" + object.getFullyQualifiedName() + "\"", cause);
        this.object = object;
    }

    /**
     * Get the database object type
     *
     * @return object type
     */
    public String getObjectType() {
        return this.object.getType();
    }

    /**
     * Get the database object name
     *
     * @return object name
     */
    public String getObjectName() {
        return this.object.getFullyQualifiedName();
    }
}