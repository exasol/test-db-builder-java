package com.exasol.dbbuilder.dialects;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Interface for writers that persist database objects.
 */
public interface DatabaseObjectWriter {
    /**
     * Create a schema in the database.
     *
     * @param schema schema to be written
     */
    void write(final Schema schema);

    /**
     * Create a table in the database.
     *
     * @param table table to be written
     */
    void write(final Table table);

    /**
     * Bulk write data to a database table.
     *
     * @param table table to write to
     * @param rows  stream of rows to be written
     */
    void write(final Table table, final Stream<List<Object>> rows);

    /**
     * Create a user in the database.
     *
     * @param user to be created
     */
    void write(final User user);

    /**
     * Grant system privileges to a user.
     *
     * @param user       user who gets the privileges
     * @param privileges privileges to be assigned
     */
    void write(final User user, final GlobalPrivilege... privileges);

    /**
     * Grant privileges to a database object to a user.
     *
     * @param user             user who gets the privileges
     * @param object           object the privileges apply to
     * @param objectPrivileges privileges to be assigned
     */
    void write(final User user, final DatabaseObject object, final ObjectPrivilege... objectPrivileges);

    /**
     * Execute the contents of a SQL script file.
     *
     * @param sqlFiles path(s) to the script file(s)
     */
    void executeSqlFile(Path... sqlFiles);

    /**
     * Drop a table.
     *
     * @param table table to drop
     */
    void drop(Table table);

    /**
     * Drop a user.
     * 
     * @param user to drop
     */
    void drop(User user);

    /**
     * Drop a schema.
     * 
     * @param schema to drop
     */
    void drop(Schema schema);
}