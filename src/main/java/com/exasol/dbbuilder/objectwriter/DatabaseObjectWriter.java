package com.exasol.dbbuilder.objectwriter;

import com.exasol.dbbuilder.AdapterScript;
import com.exasol.dbbuilder.ConnectionDefinition;
import com.exasol.dbbuilder.DatabaseObject;
import com.exasol.dbbuilder.ObjectPrivilege;
import com.exasol.dbbuilder.Schema;
import com.exasol.dbbuilder.SystemPrivilege;
import com.exasol.dbbuilder.Table;
import com.exasol.dbbuilder.User;
import com.exasol.dbbuilder.VirtualSchema;

/**
 * Interface for writers that persist database objects.
 */
public interface DatabaseObjectWriter {
    /**
     * Create an adapter script for a Virtual Schema.
     *
     * @param adapterScript the adapter script to be created
     */
    public void write(AdapterScript adapterScript);

    /**
     * Create a connection definition.
     *
     * @param connectionDefinition connection definition to be created
     */
    public void write(final ConnectionDefinition connectionDefinition);

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
     * Write an adapter script to the database
     *
     * @param virtualSchema
     */
    public void write(final VirtualSchema virtualSchema);

}