package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.AbstractDatabaseObject;
import com.exasol.dbbuilder.dialects.DatabaseObject;
import com.exasol.dbbuilder.dialects.DatabaseObjectException;
import com.exasol.errorreporting.ExaError;

/**
 * Definition of a connection to another database or service.
 * <p>
 * Other terms that are used for this are "named connection" or "connection object". A connection definition is a named
 * database object that contains a reference to the remote database or service and optionally credentials.
 * </p>
 */
public class ConnectionDefinition extends AbstractDatabaseObject {
    private final ExasolImmediateDatabaseObjectWriter writer;
    private final String target;
    private String userName;
    private String password;

    /**
     * Create a connection without credentials.
     *
     * @param writer database object writer
     * @param name   name of the connection
     * @param target target the connection points to
     */
    // [impl~creating-connections~1]
    public ConnectionDefinition(final ExasolImmediateDatabaseObjectWriter writer, final Identifier name,
            final String target) {
        super(name, false);
        this.writer = writer;
        this.target = target;
        this.writer.write(this);
    }

    /**
     * Create a connection with credentials.
     *
     * @param writer   database object writer
     * @param name     name of the connection
     * @param target   target the connection points to
     * @param userName user as which to connect
     * @param password password or password-like credential
     */
    public ConnectionDefinition(final ExasolImmediateDatabaseObjectWriter writer, final Identifier name,
            final String target, final String userName, final String password) {
        super(name, false);
        this.target = target;
        this.writer = writer;
        this.userName = userName;
        this.password = password;
        this.writer.write(this);
    }

    @Override
    public String getType() {
        return "connection";
    }

    /**
     * Get the target the connection definition points to.
     *
     * @return target of the connection
     */
    public String getTarget() {
        return this.target;
    }

    /**
     * Get the name of the user who connects to the remote database or service.
     *
     * @return user name
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Get the password of the user who connects to the remote database or service.
     *
     * @return password or password-like credential
     */
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean hasParent() {
        return false;
    }

    @Override
    public DatabaseObject getParent() {
        throw new DatabaseObjectException(this, ExaError.messageBuilder("E-TDBJ-8").message("Illegal attempt to access parent object of a CONNECTION which is a top-level object.").toString());
    }

    @Override
    // [impl->dsn~dropping-connections~1]
    public void drop() {
        this.writer.drop(this);
    }

    /**
     * Check whether the connection definition contains a user name.
     *
     * @return {@code true} if the connection definition contains a user name
     */
    public boolean hasUserName() {
        return (this.userName != null) && !this.userName.isEmpty();
    }

    /**
     * Check whether the connection definition contains a password.
     *
     * @return {@code true} if the connection definition contains a password
     */
    public boolean hasPassword() {
        return (this.password != null) && !this.password.isEmpty();
    }
}