package com.exasol.dbbuilder.dialects;

/**
 * A common interface for quote appliers.
 */
public interface QuoteApplier {
    /**
     * Quote a string if necessary.
     * 
     * @param name string to quote
     * @return quoted sring
     */
    public String quote(String name);
}
