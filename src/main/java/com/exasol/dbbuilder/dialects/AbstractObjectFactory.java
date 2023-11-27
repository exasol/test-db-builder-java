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
        this.writer.executeSqlFile(sqlFiles);
    }

    @Override
    public User createLoginUser(final String name) {
        return createUser(name);
    }

    @Override
    public User createLoginUser(final String name, final String password) {
        return createUser(name, password);
    }

    /**
     * Write the given user to the database. The method returns the user to allow creating, writing and returning in a
     * single line.
     * 
     * @param user the user to write
     * @param <T>  the concrete user type
     * @return the user
     */
    protected <T extends User> T writeUser(final T user) {
        this.writer.write(user);
        return user;
    }
}
