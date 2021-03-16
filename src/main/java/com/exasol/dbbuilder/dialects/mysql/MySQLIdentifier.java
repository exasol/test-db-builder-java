package com.exasol.dbbuilder.dialects.mysql;

import java.util.Objects;

import com.exasol.db.Identifier;

/**
 * MySQL-specific identifier.
 */
public class MySQLIdentifier implements Identifier {
    private final String id;

    private MySQLIdentifier(final String id) {
        this.id = id;
    }

    /**
     * Create a new {@link MySQLIdentifier}.
     *
     * @param id the identifier as {@link String}
     * @return new {@link MySQLIdentifier} instance
     */
    public static Identifier of(final String id) {
        if (validate(id)) {
            return new MySQLIdentifier(id);
        } else {
            throw new AssertionError("E-ID-1: Unable to create identifier \"" + id //
                    + "\" because it contains illegal characters." //
                    + " For information about valid identifiers, please refer to" //
                    + " https://dev.mysql.com/doc/refman/8.0/en/identifiers.html");
        }
    }

    /**
     * Check if a string is a valid identifier.
     *
     * @param id identifier to be validated
     * @return {@code true} if the string is a valid identifier
     */
    public static boolean validate(final String id) {
        if ((id == null) || (id.isEmpty()) || (id.length() > 64)) {
            return false;
        }
        for (int i = 0; i < id.length(); ++i) {
            final int codePoint = id.codePointAt(i);
            if ((!Character.isBmpCodePoint(codePoint)) || (codePoint == '\u0000')) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public String quote() {
        return "`" + this.id.replace("`", "``") + "`";
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        final MySQLIdentifier that = (MySQLIdentifier) object;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}