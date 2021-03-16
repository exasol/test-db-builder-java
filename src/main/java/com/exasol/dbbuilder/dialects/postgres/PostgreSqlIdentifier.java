package com.exasol.dbbuilder.dialects.postgres;

import java.util.Objects;

import com.exasol.db.Identifier;

/**
 * PostgreSQL {@link Identifier}.
 */
public class PostgreSqlIdentifier implements Identifier {
    private final String id;

    private PostgreSqlIdentifier(final String id) {
        this.id = id;
    }

    /**
     * Get a PostgreSQL {@link Identifier} for a given string.
     * 
     * @param id id for the identifier
     * @return built identifier
     */
    public static PostgreSqlIdentifier of(final String id) {
        return new PostgreSqlIdentifier(id);
    }

    @Override
    public String toString() {
        return this.id;
    }

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
        final PostgreSqlIdentifier that = (PostgreSqlIdentifier) object;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
