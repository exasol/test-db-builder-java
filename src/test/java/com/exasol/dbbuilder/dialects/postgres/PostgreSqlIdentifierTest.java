package com.exasol.dbbuilder.dialects.postgres;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class PostgreSqlIdentifierTest {
    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(PostgreSqlIdentifier.class).verify();
    }

    @Test
    void testQuote() {
        assertThat(PostgreSqlIdentifier.of("test").quote(), equalTo("\"test\""));
    }
}
