package com.exasol.dbbuilder.objectwriter;

import java.util.List;

import com.exasol.dbbuilder.DatabaseObject;
import com.exasol.dbbuilder.ObjectPrivilege;
import com.exasol.dbbuilder.Schema;
import com.exasol.dbbuilder.SystemPrivilege;
import com.exasol.dbbuilder.Table;
import com.exasol.dbbuilder.User;

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
     * Create a user in the database.
     * 
     * @param user to be created
     */
    public void write(final User user);

    /**
     * Create a table in the database.
     * 
     * @param table table to be written
     */
    public void write(final Table table);

    /**
     * Grant system privileges to a user.
     * 
     * @param user       user who gets the privileges
     * @param privileges privileges to be assigned
     */
    public void write(final User user, final SystemPrivilege... privileges);

    /**
     * Grant privileges to a database object to a user.
     * 
     * @param user             user who gets the privileges
     * @param object           object the privileges apply to
     * @param objectPrivileges privileges to be assigned
     */
    public void write(final User user, final DatabaseObject object, final ObjectPrivilege... objectPrivileges);

    /**
     * Write data to a database table.
     * 
     * @param table table to write to
     * @param rows  rows to be written
     */
    public void write(Table table, List<List<Object>> rows);
}