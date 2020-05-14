package com.exasol.dbbuilder;

import java.util.HashMap;
import java.util.Map;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

/**
 * Virtual Schema
 */
public class VirtualSchema extends AbstractDatabaseObject {
    private static final String SCHEMA_NAME_KEY = "SCHEMA_NAME";
    private static final String CONNECTION_NAME_KEY = "CONNECTION_NAME";
    private static final String SQL_DIALECT_KEY = "SQL_DIALECT";
    private final AdapterScript adapterScript;
    private final ConnectionDefinition connectionDefinition;
    private final Map<String, String> properties = new HashMap<>();

    private VirtualSchema(final Builder builder) {
        super(builder.writer, builder.name, false);
        this.adapterScript = builder.adapterScript;
        this.connectionDefinition = builder.connectionDefinition;
        addReservedProperties(builder);
        this.properties.putAll(builder.properties);
        this.writer.write(this);
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
        throw new DatabaseObjectException(this,
                "Illegal attempt to access parent object of a VIRTUAL SCHEMA which is a top-level object.");
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
     * Create a new builder for a {@link VirtualSchema}.
     *
     * @param writer database object writer
     * @param name   name of the Virtual Schema to be built
     * @return builder instance
     */
    public static Builder builder(final DatabaseObjectWriter writer, final String name) {
        return new Builder(writer, name);
    }

    /**
     * Builder for a {@link VirtualSchema}.
     */
    public static class Builder {
        private final DatabaseObjectWriter writer;
        private final String name;
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
        public Builder(final DatabaseObjectWriter writer, final String name) {
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