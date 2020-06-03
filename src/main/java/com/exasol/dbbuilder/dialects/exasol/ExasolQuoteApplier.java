package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.dbbuilder.dialects.QuoteApplier;

/**
 * Exasol-specific quote applier.
 */
public class ExasolQuoteApplier implements QuoteApplier {
    @Override
    public String quote(final String name) {
        return "\"" + name.replaceAll("\"", "\"\"") + "\"";
    }
}