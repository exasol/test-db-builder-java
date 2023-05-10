package com.exasol.dbbuilder.dialects;

/**
 * This exception is thrown when executing an operation on a {@link DatabaseObject} that was deleted.
 */
public class DatabaseObjectDeletedException extends DatabaseObjectException {
    private static final long serialVersionUID = 6211027393836593969L;

    /**
     * Create a new instance of a {@link DatabaseObjectDeletedException}.
     *
     * @param object database object
     */
    public DatabaseObjectDeletedException(final DatabaseObject object) {
        super(object, object.getType() + " " + object.getFullyQualifiedName() + " was already deleted");
    }
}
