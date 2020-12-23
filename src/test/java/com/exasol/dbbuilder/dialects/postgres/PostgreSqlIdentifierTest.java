package com.exasol.dbbuilder.dialects.postgres;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class PostgreSqlIdentifierTest {
    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(PostgreSqlIdentifier.class).verify();
    }
}
