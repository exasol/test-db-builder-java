package com.exasol.dbbuilder.objectwriter;

import java.sql.SQLException;

import com.exasol.dbbuilder.DatabaseObject;
import com.exasol.dbbuilder.DatabaseObjectException;

/**
 * Exception for database object writers.
 */
public class DatabaseObjectWriterException extends RuntimeException {
    private static final long serialVersionUID = -9202457347924851051L;
    private final String objectName;
    private final String objectType;
    private final String sql;

    /**
     * Create a new instance of a {@link DatabaseObjectException}.
     *
     * @param message error message
     * @param cause   exception causing this one
     */
    public DatabaseObjectWriterException(final String message, final SQLException cause) {
        super(message, cause);
        this.objectName = null;
        this.objectType = null;
        this.sql = null;
    }

    /**
     * Create a new instance of a {@link DatabaseObjectException}.
     *
     * @param object object that the writer tried to modify
     * @param sql    SQL statement the writer used
     * @param cause  exception causing this one
     */
    public DatabaseObjectWriterException(final DatabaseObject object, final String sql, final SQLException cause) {
        super("Unable to modify " + object.getType() + " " + object.getFullyQualifiedName() + " using SQL statement: "
                + sql, cause);
        this.objectName = object.getFullyQualifiedName();
        this.objectType = object.getType();
        this.sql = sql;
    }

    /**
     * Get the name of the database object the writer tried to modify.
     *
     * @return object name
     */
    public String getObjectName() {
        return this.objectName;
    }

    /**
     * Get the type of the object the writer tried to modify.
     *
     * @return object type
     */
    public String getObjectType() {
        return this.objectType;
    }

    /**
     * Get the SQL statement that was used.
     *
     * @return SQL statement
     */
    public String getSql() {
        return this.sql;
    }
}