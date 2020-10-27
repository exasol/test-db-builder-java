package com.exasol.dbbuilder.dialects.exasol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents global configuration for building Exasol objects.
 */
public class ExasolObjectConfiguration {
    private final List<String> jvmOptions;

    private ExasolObjectConfiguration(final Builder builder) {
        this.jvmOptions = builder.jvmOptions;
    }

    /**
     * Get a builder for {@link ExasolObjectConfiguration}.
     *
     * @return builder for {@link ExasolObjectConfiguration}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Get a list of options for the JVM, added to all JAVA UDFs and virtual schema adapters.
     *
     * @return list of JVM options
     */
    public List<String> getJvmOptions() {
        return this.jvmOptions;
    }

    /**
     * Builder for {@link ExasolObjectConfiguration}.
     */
    public static class Builder {
        private final List<String> jvmOptions = new ArrayList<>();

        private Builder() {
            // empty on purpose
        }

        /**
         * Add one or more JVM options.
         * <p>
         * The options are added to all JAVA UDFs and virtual schema adapter declarations.
         * </p>
         * 
         * @param jvmOptions one or more JVM options to add
         * @return self for fluent programming
         */
        // [impl->dsn~creating-java-scripts-with-jvm-options~1]
        public Builder withJvmOptions(final String... jvmOptions) {
            this.jvmOptions.addAll(Arrays.asList(jvmOptions));
            return this;
        }

        /**
         * Build the {@link ExasolObjectConfiguration}.
         * 
         * @return built {@link ExasolObjectConfiguration}.
         */
        public ExasolObjectConfiguration build() {
            return new ExasolObjectConfiguration(this);
        }
    }
}
