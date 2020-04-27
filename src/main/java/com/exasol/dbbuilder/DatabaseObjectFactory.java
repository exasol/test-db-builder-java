package com.exasol.dbbuilder;

public interface DatabaseObjectFactory {
    /**
     * Create a connection without credentials.
     *
     * @param name name of the connection
     * @param to   target the connection points to
     * @return new {@link User} instance
     */
    public ConnectionDefinition createConnectionDefinition(String name, String to);

    /**
     * Create a connection without credentials.
     *
     * @param name     name of the connection
     * @param to       target the connection points to
     * @param userName user as which to connect
     * @param password password or password-like credential
     * @return new {@link User} instance
     */
    public ConnectionDefinition createConnectionDefinition(String name, String to, String userName, String password);

    /**
     * Create a new database schema.
     *
     * @param name name of the schema
     * @return new {@link Schema} instance.
     */
    public Schema createSchema(String name);

    /**
     * Create a new database user.
     *
     * @param name user name
     * @return new {@link User} instance
     */
    public User createUser(String name);

    /**
     * Create a builder for a Virtual Schema.
     *
     * @param name name of the Virtual Schema
     * @return builder
     */
    public VirtualSchema.Builder createVirtualSchemaBuilder(String name);
}