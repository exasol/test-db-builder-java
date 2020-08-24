package com.exasol.dbbuilder.dialects.exasol;

/**
 * Configuration singleton for the Exasol dialect.
 */
class ExasolConfiguration {
    private static final ExasolConfiguration INSTANCE = new ExasolConfiguration();

    private ExasolConfiguration() {
        // empty on purpose
    }

    /**
     * Get a singleton instance of {@link ExasolConfiguration}.
     *
     * @return singleton instance of {@link ExasolConfiguration}
     */
    public static ExasolConfiguration getInstance() {
        return INSTANCE;
    }

    /**
     * Get if debugging adapter scripts was globally enabled. *
     * <p>
     * You can enable debugging by setting the property test.debugAdapterScripts to true. You can set it by appending
     * -Dtest.debug="true" to your the JVM.
     * </p>
     *
     * @return true if debugging adapter scripts was globally enabled
     */
    public boolean isAdapterScriptDebuggingEnabled() {
        final String debugProperty = System.getProperty("tests.debugAdapterScripts");
        return debugProperty != null && debugProperty.equals("true");
    }
}
