package com.exasol.dbbuilder.dialects;

import java.nio.file.Path;

/**
 * Interface for writers that persist database objects.
 */
public interface DatabaseObjectWriter {
    /**
     * Create a schema in the database.
     *
     * @param schema schema to be written
     */
    public void write(final Schema schema);

    /**
     * Create a table in the database.
     *
     * @param table table to be written
     */
    public void write(final Table table);

    /**
     * Write data to a database table.
     *
     * @param table  table to write to
     * @param values values to be written
     */
    public void write(final Table table, final Object... values);

    /**
     * Create a user in the database.
     *
     * @param user to be created
     */
    public void write(final User user);

    /**
     * Grant system privileges to a user.
     *
     * @param user       user who gets the privileges
     * @param privileges privileges to be assigned
     */
    public void write(final User user, final GlobalPrivilege... privileges);

    /**
     * Grant privileges to a database object to a user.
     *
     * @param user             user who gets the privileges
     * @param object           object the privileges apply to
     * @param objectPrivileges privileges to be assigned
     */
    public void write(final User user, final DatabaseObject object, final ObjectPrivilege... objectPrivileges);

    /**
     * Execute the contents of a SQL script file.
     *
     * @param sqlFiles path(s) to the script file(s)
     */
    public void executeSqlFile(Path... sqlFiles);

    /**
     * Drop a table.
     *
     * @param table table to drop.
     */
    public void drop(Table table);
}