package com.exasol.dbbuilder.dialects.oracle;

import com.exasol.db.Identifier;

/**
 * Oracle {@link Identifier}.
 */
public class OracleIdentifier implements Identifier {
    private final String id;

    private OracleIdentifier(final String id) {
        this.id = id;
    }

    /**
     * Create an Oracle {@link Identifier} for a given string.
     * 
     * @param id id for the identifier
     * @return built identifier
     */
    public static OracleIdentifier of(final String id) {
        return new OracleIdentifier(id);
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public String quote() {
        return "\"" + this.id.replace("\"", "\"\"") + "\"";
    }
}
