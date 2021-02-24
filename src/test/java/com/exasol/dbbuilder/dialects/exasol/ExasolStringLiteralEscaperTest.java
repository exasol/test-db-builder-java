package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ExasolStringLiteralEscaperTest {

    @ParameterizedTest
    @CsvSource({ //
            "test', test''", //
            "test\", test\"", //
            "test'', test''''", //
    })
    void test(final String input, final String expectedOutput) {
        assertThat(new ExasolStringLiteralEscaper().escape(input), equalTo(expectedOutput));
    }
}