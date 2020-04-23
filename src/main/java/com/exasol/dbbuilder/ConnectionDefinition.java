package com.exasol.dbbuilder;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

/**
 * Definition of a connection to another database or service.
 * <p>
 * Other terms that are used for this are "named connection" or "connection object". A connection definition is a named
 * database object that contains a reference to the remote database or service and optionally credentials.
 * </p>
 */
public class ConnectionDefinition extends AbstractDatabaseObject {
    private final String to;
    private String userName;
    private String password;

    /**
     * Create a connection without credentials.
     *
     * @param writer database object writer
     * @param name   name of the connection
     * @param to     target the connection points to
     */
    public ConnectionDefinition(final DatabaseObjectWriter writer, final String name, final String to) {
        super(writer, name);
        this.to = to;
        writer.write(this);
    }

    /**
     * Create a connection with credentials.
     *
     * @param writer   database object writer
     * @param name     name of the connection
     * @param to       target the connection points to
     * @param userName user as which to connect
     * @param password password or password-like credential
     */
    public ConnectionDefinition(final DatabaseObjectWriter writer, final String name, final String to,
            final String userName, final String password) {
        super(writer, name);
        this.to = to;
        this.userName = userName;
        this.password = password;
        writer.write(this);
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
    public String getTo() {
        return this.to;
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
        throw new DatabaseObjectException(this,
                "Illegal attempt to access parent object of a CONNECTION which is a top-level object.");
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