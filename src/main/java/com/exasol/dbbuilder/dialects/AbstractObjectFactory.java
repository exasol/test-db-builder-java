package com.exasol.dbbuilder.dialects;

import java.nio.file.Path;

/**
 * An abstract base for all ObjectFactories.
 */
public abstract class AbstractObjectFactory implements DatabaseObjectFactory {

    private final DatabaseObjectWriter writer;

    /**
     * Create a new instance
     * 
     * @param writer a database object writer
     */
    protected AbstractObjectFactory(final DatabaseObjectWriter writer) {
        this.writer = writer;
    }

    @Override
    public void executeSqlFile(final Path... sqlFiles) {
        getWriter().executeSqlFile(sqlFiles);
    }

    /**
     * Get a {@link DatabaseObjectWriter}.
     * 
     * @return new {@link DatabaseObjectWriter} instance
     */
    protected abstract DatabaseObjectWriter getWriter();

    /**
     * Create a new user object without writing it to the database.
     * 
     * @param name user name
     * @return a new user object
     */
    protected abstract User createNewUser(String name);

    /**
     * Create a new user object without writing it to the database.
     * 
     * @param name     user name
     * @param password password
     * @return a new user object
     */
    protected abstract User createNewUser(String name, String password);

    @Override
    public User createUser(final String name) {
        return writeUser(createNewUser(name));
    }

    @Override
    public User createUser(final String name, final String password) {
        return writeUser(createNewUser(name, password));
    }

    @Override
    public User createLoginUser(final String name) {
        return createUser(name);
    }

    @Override
    public User createLoginUser(final String name, final String password) {
        return createUser(name, password);
    }

    private <T extends User> T writeUser(final T user) {
        this.writer.write(user);
        return user;
    }
}
