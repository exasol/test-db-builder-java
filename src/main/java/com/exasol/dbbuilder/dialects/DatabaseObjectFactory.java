package com.exasol.dbbuilder.dialects;

import java.nio.file.Path;

/**
 * Interface for database object factories.
 * <p>
 * The concrete implementation depends on the feature set and dialect of the database that you want to create objects
 * for.
 * </p>
 */
// [impl->dsn~database-object-factory~1]
public interface DatabaseObjectFactory {
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
     * @param name user name
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
     * Execute the contents of an SQL script file.
     *
     * @param sqlFiles path to the script file
     */
    public void executeSqlFile(final Path... sqlFiles);

    /**
     * Create a new database schema.
     *
     * @param name name of the schema
     * @return new {@link Schema}
     */
    public Schema createSchema(String name);
}