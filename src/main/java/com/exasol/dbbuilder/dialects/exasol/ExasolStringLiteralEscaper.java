package com.exasol.dbbuilder.dialects.exasol;

/**
 * This class escapes a string so that it can be used in an exasol string literal.
 */
public class ExasolStringLiteralEscaper {

    /**
     * Escape a goven message for the use in an Exasol string literal.
     * 
     * @param message message to escape
     * @return escaped message
     */
    public String escape(final String message) {
        return message.replace("'", "''");
    }
}
