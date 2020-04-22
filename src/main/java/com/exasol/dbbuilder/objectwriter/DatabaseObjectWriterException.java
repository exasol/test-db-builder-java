package com.exasol.dbbuilder.objectwriter;

import java.sql.SQLException;

import com.exasol.dbbuilder.DatabaseObjectException;

/**
 * Exception for database object writers.
 */
public class DatabaseObjectWriterException extends RuntimeException {
    private static final long serialVersionUID = -3047784401339832425L;

    /**
     * Create a new instance of a {@link DatabaseObjectException}.
     *
     * @param message error message
     * @param cause   exception causing this one
     */
    public DatabaseObjectWriterException(final String message, final SQLException cause) {
        super(message, cause);
    }
}