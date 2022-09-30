package com.exasol.dbbuilder.dialects.exasol;

import java.util.HashMap;
import java.util.Map;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.*;
import com.exasol.errorreporting.ExaError;

/**
 * Virtual Schema.
 */
public class VirtualSchema extends AbstractDatabaseObject {
    private static final String SCHEMA_NAME_KEY = "SCHEMA_NAME";
    private static final String CONNECTION_NAME_KEY = "CONNECTION_NAME";
    private static final String SQL_DIALECT_KEY = "SQL_DIALECT";
    private static final String DEBUG_PROPERTY = "com.exasol.virtualschema.debug.";
    static final String DEBUG_HOST = DEBUG_PROPERTY + "host";
    static final String DEBUG_PORT = DEBUG_PROPERTY + "port";
    static final String DEBUG_LOG_LEVEL = DEBUG_PROPERTY + "level";

    private final ExasolImmediateDatabaseObjectWriter writer;
    private final AdapterScript adapterScript;
    private final ConnectionDefinition connectionDefinition;
    private final Map<String, String> properties = new HashMap<>();

    private VirtualSchema(final Builder builder) {
        super(builder.name, false);
        this.writer = builder.writer;
        this.adapterScript = builder.adapterScript;
        this.connectionDefinition = builder.connectionDefinition;
        addReservedProperties(builder);
        addDebugProperties();
        this.properties.putAll(builder.properties);
        this.writer.write(this);
    }

    /**
     * Create a new builder for a {@link VirtualSchema}.
     *
     * @param writer database object writer
     * @param name   name of the Virtual Schema to be built
     * @return builder instance
     */
    public static Builder builder(final ExasolImmediateDatabaseObjectWriter writer, final Identifier name) {
        return new Builder(writer, name);
    }

    private void addReservedProperties(final Builder builder) {
        if (builder.dialectName != null) {
            this.properties.put(VirtualSchema.SQL_DIALECT_KEY, builder.dialectName);
        }
        if (builder.connectionDefinition != null) {
            this.properties.put(VirtualSchema.CONNECTION_NAME_KEY, builder.connectionDefinition.getName());
        }
        if (builder.sourceSchemaName != null) {
            this.properties.put(VirtualSchema.SCHEMA_NAME_KEY, builder.sourceSchemaName);
        }
    }

    private void addDebugProperties() {
        final String host = System.getProperty(DEBUG_HOST);
        final String port = System.getProperty(DEBUG_PORT);

        String address = "";
        if (host != null) {
            address = host;
        }
        if (port != null) {
            address += ":" + port;
        }
        if (!address.isEmpty()) {
            this.properties.put("DEBUG_ADDRESS", address);
        }
        String logLevel = System.getProperty(DEBUG_LOG_LEVEL);
        if ((logLevel == null) && !address.isEmpty()) {
            logLevel = "ALL";
        }
        if (logLevel != null) {
            this.properties.put("LOG_LEVEL", logLevel);
        }
    }

    @Override
    public String getType() {
        return "virtual schema";
    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public DatabaseObject getParent() {
        throw new DatabaseObjectException(this, ExaError.messageBuilder("E-TDBJ-10") //
                .message("Illegal attempt to access parent object of a VIRTUAL SCHEMA which is a top-level object.") //
                .toString());
    }

    @Override
    // [impl->dsn~dropping-virtual-schemas~2]
    public void drop() {
        this.writer.drop(this);
    }

    /**
     * Get the name of the source schema.
     *
     * @return source schema name
     */
    public String getSourceSchemaName() {
        return this.properties.get(VirtualSchema.SCHEMA_NAME_KEY);
    }

    /**
     * Get the adapter script that powers the Virtual Schema.
     *
     * @return adapter script
     */
    public AdapterScript getAdapterScript() {
        return this.adapterScript;
    }

    /**
     * Get the name of the Virtual Schema dialect.
     *
     * @return dialect name
     */
    public String getDialectName() {
        return this.properties.get(VirtualSchema.SQL_DIALECT_KEY);
    }

    /**
     * Get the connection definition.
     *
     * @return connection definition
     */
    public ConnectionDefinition getConnectionDefinition() {
        return this.connectionDefinition;
    }

    /**
     * Get the raw adapter properties.
     *
     * @return raw adapter properties
     */
    public Map<String, String> getProperties() {
        return this.properties;
    }

    /**
     * Builder for a {@link VirtualSchema}.
     */
    // [impl->dsn~creating-virtual-schemas~1]
    public static class Builder {
        private final ExasolImmediateDatabaseObjectWriter writer;
        private final Identifier name;
        private String sourceSchemaName;
        private AdapterScript adapterScript;
        private String dialectName;
        private ConnectionDefinition connectionDefinition;
        private Map<String, String> properties = new HashMap<>();

        /**
         * Create a new instance of a builder for a {@link VirtualSchema}.
         *
         * @param writer database object writer
         * @param name   name of the Virtual Schema
         */
        public Builder(final ExasolImmediateDatabaseObjectWriter writer, final Identifier name) {
            this.writer = writer;
            this.name = name;
        }

        /**
         * Set the name of the source schema the Virtual Schema refers to.
         *
         * @param sourceSchemaName name of the source schema
         * @return {@code this} for fluent programming
         */
        public Builder sourceSchemaName(final String sourceSchemaName) {
            this.sourceSchemaName = sourceSchemaName;
            return this;
        }

        /**
         * Set the source schema the Virtual Schema refers to.
         *
         * @param sourceSchema source schema
         * @return {@code this} for fluent programming
         */
        public Builder sourceSchema(final Schema sourceSchema) {
            this.sourceSchemaName = sourceSchema.getName();
            return this;
        }

        /**
         * Set the name of the script that serves as adapter for the Virtual Schema.
         *
         * @param adapterScript Virtual Schema Adapter script
         * @return {@code this} for fluent programming
         */
        public Builder adapterScript(final AdapterScript adapterScript) {
            this.adapterScript = adapterScript;
            return this;
        }

        /**
         * Set the name of the Virtual Schema dialect that should be used.
         *
         * @param dialectName name of the SQL dialect
         * @return {@code this} for fluent programming
         */
        public Builder dialectName(final String dialectName) {
            this.dialectName = dialectName;
            return this;
        }

        /**
         * Set the connection object pointing to the remote data source.
         *
         * @param connectionDefinition connection object
         * @return {@code this} for fluent programming.
         */
        public Builder connectionDefinition(final ConnectionDefinition connectionDefinition) {
            this.connectionDefinition = connectionDefinition;
            return this;
        }

        /**
         * Build a new instance of a {@link VirtualSchema}.
         *
         * @return new {@link VirtualSchema} instance
         */
        public VirtualSchema build() {
            return new VirtualSchema(this);
        }

        /**
         * Set additional properties for the adapter.
         *
         * @param properties additional properties
         * @return {@code this} for fluent programming
         */
        public Builder properties(final Map<String, String> properties) {
            this.properties = properties;
            return this;
        }
    }
}