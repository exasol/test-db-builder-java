package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.dbbuilder.dialects.QuoteApplier;

/**
 * MySQL-specific quote applier.
 */
public class MySqlQuoteApplier implements QuoteApplier {
    @Override
    public String quote(final String name) {
        return "`" + name + "`";
    }
}