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
    public User createUser(final String name) {
        return createUser(name, generateDefaultPassword(name));
    }

    private String generateDefaultPassword(final String username) {
        return username + "PWD";
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
     * Write the given {@link User} to the database. The method returns the user to allow creating, writing and
     * returning in a single line.
     * 
     * @param user the user to write
     * @param <T>  the concrete user type
     * @return the user
     */
    protected <T extends User> T writeUser(final T user) {
        this.writer.write(user);
        return user;
    }

    /**
     * Write the given {@link Schema} to the database. The method returns the schema to allow creating, writing and
     * returning in a single line.
     * 
     * @param schema the schema to write
     * @param <T>    the concrete schema type
     * @return the schema
     */
    protected <T extends Schema> T writeSchema(final T schema) {
        this.writer.write(schema);
        return schema;
    }
}
