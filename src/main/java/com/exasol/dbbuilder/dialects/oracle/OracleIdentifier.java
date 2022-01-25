package com.exasol.dbbuilder.dialects.oracle;

import com.exasol.db.Identifier;

import java.util.Objects;

/**
 * Oracle {@link Identifier}.
 */
public class OracleIdentifier implements Identifier {
    private final String id;

    private OracleIdentifier(final String id) {
        this.id = id;
    }

    /**
     * Get a Oracle {@link Identifier} for a given string.
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
    //TODO: check this
    @Override
    public String quote() {
        return "\"" + this.id.replace("\"", "\"\"") + "\"";
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final OracleIdentifier that = (OracleIdentifier) object;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
