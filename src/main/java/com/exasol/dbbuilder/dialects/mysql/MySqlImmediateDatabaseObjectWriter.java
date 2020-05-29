package com.exasol.dbbuilder.dialects.mysql;

import java.sql.Connection;

import com.exasol.dbbuilder.dialects.*;

/**
 * Database object writer that writes objects to the database immediately.
 */
public class MySqlImmediateDatabaseObjectWriter extends AbstractImmediateDatabaseObjectWriter {

    /**
     * Create a new instance of an {@link MySqlImmediateDatabaseObjectWriter}.
     *
     * @param connection JDBC connection
     */
    public MySqlImmediateDatabaseObjectWriter(final Connection connection) {
        super(connection);
    }

    @Override
    public void write(final User user) {
        writeToObject(user,
                "CREATE USER '" + user.getFullyQualifiedName() + "' IDENTIFIED BY '" + user.getPassword() + "'");
    }

    @Override
    public void write(final User user, final GlobalPrivilege... privileges) {
        writeToObject(user,
                "GRANT " + createCommaSeparatedSystemPrivilegeList(privileges) + " ON *.* TO " + user.getFullyQualifiedName());
    }
}