package com.exasol.dbbuilder.dialects.exasol;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ExasolConfigurationTest {

    public static final String PROPERTY_NAME = "tests.debugAdapterScripts";

    @Test
    void testAdapterScriptDebuggingDefault() {
        System.clearProperty(PROPERTY_NAME);
        assertThat(ExasolConfiguration.getInstance().isAdapterScriptDebuggingEnabled(), equalTo(false));
    }

    @Test
    void testAdapterScriptDebuggingEnabled() {
        System.setProperty(PROPERTY_NAME, "true");
        assertThat(ExasolConfiguration.getInstance().isAdapterScriptDebuggingEnabled(), equalTo(true));
    }

    @Test
    void testAdapterScriptDebuggingDisabled() {
        System.setProperty(PROPERTY_NAME, "false");
        assertThat(ExasolConfiguration.getInstance().isAdapterScriptDebuggingEnabled(), equalTo(false));
    }
}