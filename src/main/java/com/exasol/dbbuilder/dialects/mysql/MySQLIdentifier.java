package com.exasol.dbbuilder.dialects.mysql;

import com.exasol.db.Identifier;

/**
 * MySQL-specific identifier.
 */
public class MySQLIdentifier implements Identifier {
    private final String id;

    private MySQLIdentifier(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public String quote() {
        return "`" + this.id + "`";
    }

    /**
     * Create a new {@link MySQLIdentifier}.
     * 
     * @param id the identifier as {@link String}
     * @return new {@link MySQLIdentifier} instance
     */
    public static Identifier of(final String id) {
        return new MySQLIdentifier(id);
    }
}