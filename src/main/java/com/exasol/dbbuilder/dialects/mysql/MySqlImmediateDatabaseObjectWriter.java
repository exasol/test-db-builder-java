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
    public void truncate(final Table table) {
        writeToObject(table, "TRUNCATE " + table.getFullyQualifiedName());
    }

    @Override
    public void write(final User user) {
        writeToObject(user,
                "CREATE USER " + user.getFullyQualifiedName() + " IDENTIFIED BY '" + user.getPassword() + "'");
    }

    @Override
    public void write(final User user, final GlobalPrivilege... privileges) {
        writeToObject(user, "GRANT " + createCommaSeparatedSystemPrivilegeList(privileges) + " ON *.* TO "
                + user.getFullyQualifiedName());
    }

    @Override
    protected String getQuotedColumnName(final String columnName) {
        return MySQLIdentifier.of(columnName).quote();
    }

    @Override
    public void write(final User user, final DatabaseObject object, final ObjectPrivilege... privileges) {
        if (object instanceof Schema) {
            writeToObject(user, "GRANT " + createCommaSeparatedObjectPrivilegeList(privileges) //
                    + " ON " + object.getFullyQualifiedName() //
                    + ".* TO " + user.getFullyQualifiedName());
        } else {
            super.write(user, object, privileges);
        }
    }

    @Override
    // [impl->dsn~dropping-schemas~2]
    public void drop(final Schema schema) {
        writeToObject(schema, "DROP SCHEMA " + schema.getFullyQualifiedName());
    }
}