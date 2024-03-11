package com.exasol.dbbuilder.dialects.exasol;

/**
 * This class escapes a string so that it can be used in an Exasol string literal.
 */
public class ExasolStringLiteralEscaper {
    /**
     * Create a new instance of an {@link ExasolStringLiteralEscaper}.
     */
    public ExasolStringLiteralEscaper() {
        // Intentionally empty default constructor to avoid Java 17+ JavaDoc warning.
    }

    /**
     * Escape a given message for the use in an Exasol string literal.
     * 
     * @param message message to escape
     * @return escaped message
     */
    public String escape(final String message) {
        return message.replace("'", "''");
    }
}
