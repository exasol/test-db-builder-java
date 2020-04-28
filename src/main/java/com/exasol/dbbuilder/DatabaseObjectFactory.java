package com.exasol.dbbuilder;

/**
 * Interface for database object factories.
 * <p>
 * The concrete implementation depends on the feature set and dialect of the database that you want to create objects
 * for.
 * </p>
 */
public interface DatabaseObjectFactory {
    /**
     * Create a connection without credentials.
     *
     * @param name name of the connection
     * @param to   target the connection points to
     * @return new {@link User} instance
     */
    public ConnectionDefinition createConnectionDefinition(final String name, final String to);

    /**
     * Create a connection without credentials.
     *
     * @param name     name of the connection
     * @param target   target the connection points to
     * @param userName user as which to connect
     * @param password password or password-like credential
     * @return new {@link User} instance
     */
    public ConnectionDefinition createConnectionDefinition(final String name, final String target,
            final String userName, String password);

    /**
     * Create a new database schema.
     *
     * @param name name of the schema
     * @return new {@link Schema} instance.
     */
    public Schema createSchema(final String name);

    /**
     * /** Create a new database user with a default password.
     * <p>
     * This method creates a user with a password derived from the user name. Note that this is only acceptable in the
     * scope of testing for which the TDDB is made. Never use something like this in production code!
     * </p>
     *
     * @param name user name
     * @return new {@link User} instance
     */
    public User createUser(final String name);

    /**
     * Create a new database user.
     *
     * @param name     user name
     * @param password login password
     * @return new {@link User} instance
     */
    public User createUser(final String name, final String password);

    /**
     * Create user that can log into the database with a default password.
     * <p>
     * This method creates a user with a password derived from the user name. Note that this is only acceptable in the
     * scope of testing for which the TDDB is made. Never use something like this in production code!
     * </p>
     *
     * @param name
     * @return new {@link User} instance
     */
    public User createLoginUser(final String name);

    /**
     * Create user that can log into the database.
     *
     * @param name     user name
     * @param password login password
     * @return new {@link User} instance
     */
    public User createLoginUser(final String name, final String password);

    /**
     * Create a builder for a Virtual Schema.
     *
     * @param name name of the Virtual Schema
     * @return builder
     */
    public VirtualSchema.Builder createVirtualSchemaBuilder(final String name);
}