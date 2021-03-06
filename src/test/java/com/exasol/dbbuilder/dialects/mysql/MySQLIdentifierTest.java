package com.exasol.dbbuilder.dialects.mysql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import nl.jqno.equalsverifier.EqualsVerifier;

class MySQLIdentifierTest {
    @ParameterizedTest
    @ValueSource(strings = { "abc", "Abc10", "9abc", "a_$", "漢字テスト", "Тест", "\u0080" })
    void testValidIdentifier(final String value) {
        assertThat(MySQLIdentifier.validate(value), equalTo(true));
    }

    @ParameterizedTest
    @ValueSource(strings = { "\uD800\uDF23", "\u0000", "" })
    void testInvalidIdentifier(final String value) {
        assertThat(MySQLIdentifier.validate(value), equalTo(false));
    }

    @Test
    void testInvalidLength() {
        final String value = "a".repeat(65);
        assertThat(MySQLIdentifier.validate(value), equalTo(false));
    }

    @Test
    void testValidLength() {
        final String value = "a".repeat(64);
        assertThat(MySQLIdentifier.validate(value), equalTo(true));
    }

    @Test
    void equalsContract() {
        EqualsVerifier.simple().forClass(MySQLIdentifier.class).verify();
    }

    @Test
    void testIdentifierIsInjectionSafe() {
        assertThat(MySQLIdentifier.of("test`, `secret_column`, `other").quote(),
                equalTo("`test``, ``secret_column``, ``other`"));
    }
}