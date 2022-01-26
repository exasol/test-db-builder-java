package com.exasol.dbbuilder.dialects.oracle;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.*;
import com.exasol.dbbuilder.dialects.oracle.OracleIdentifier;
import com.exasol.errorreporting.ExaError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.stream.Stream;

/**
 * Oracle {@link DatabaseObjectWriter}.
 */
public class OracleImmediateDatabaseObjectWriter extends AbstractImmediateDatabaseObjectWriter {

    /**
     * Create a new instance of {@link OracleImmediateDatabaseObjectWriter}.
     *
     * @param connectionToOracle connection to the Oracle database.
     */
    public OracleImmediateDatabaseObjectWriter(final Connection connectionToOracle) {
        super(connectionToOracle);
    }

    @Override
    protected String getQuotedColumnName(final String columnName) {
        return OracleIdentifier.of(columnName).quote();
    }

    @Override
    public void write(final User user) {
        writeToObject(user, "CREATE USER " + user.getFullyQualifiedName() + " IDENTIFIED BY " + user.getPassword() + "");
        writeToObject(user, "grant unlimited tablespace to "+ user.getFullyQualifiedName());
    }
    //a schema is linked to a user in oracle, the schema has the same name as the user
    @Override
    public void write(final Schema schema) {
        //throw new UnsupportedOperationException(ExaError.messageBuilder("E-TDBJ-33").message("Oracle doesn't allow users to create schemas. The schema name is tied to the user.").toString());
            writeToObject(schema, "CREATE USER " + schema.getName() + " IDENTIFIED BY " + schema.getName());
            writeToObject(schema, "grant unlimited tablespace to "+ schema.getName());
    }

    @Override
    public void write(final User user, final GlobalPrivilege... privileges) {
        throw new UnsupportedOperationException(ExaError.messageBuilder("E-TDBJ-29").message("Creating users with privileges is not implemented in this version of the test-db-builder.").toString());
    }
    ////a schema is linked to a user in oracle, the schema has the same name as the user
    //TODO : there's no CASCADE in Oracle, investigate this further: https://stackoverflow.com/questions/29926262/delete-all-contents-in-a-schema-in-oracle
    @Override
    public void drop(final Schema schema) {
        //writeToObject(schema, "DROP SCHEMA " + schema.getFullyQualifiedName() + " CASCADE");
        //throw new UnsupportedOperationException(ExaError.messageBuilder("E-TDBJ-30").message("Creating users with privileges is not implemented in this version of the test-db-builder.").toString());
        writeToObject(schema,"DROP USER " + schema.getName() +" CASCADE");
    }
    //overriding the object
//    //we override the insert statement
//    @Override
//    public void write(final Table table, final Stream<List<Object>> rows) {
//        final String valuePlaceholders = "?" + ", ?".repeat(table.getColumnCount() - 1);
//        final String sql = "INSERT INTO " + table.getName() + "" + " VALUES(" + valuePlaceholders + ")";
//        try (final PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
//            final boolean autoCommitOriginalState = this.connection.getAutoCommit();
//            this.connection.setAutoCommit(false);
//            rows.forEach(row -> writeRow(table, sql, preparedStatement, row));
//            if (autoCommitOriginalState) {
//                this.connection.commit();
//                this.connection.setAutoCommit(true);
//            }
//        } catch (final SQLException exception) {
//            throw new DatabaseObjectException(table,
//                    ExaError.messageBuilder("E-TDBJ-2")
//                            .message("Failed to create prepared statement {{statement}} for insert.", sql).toString(),
//                    exception);
//        }
//    }
}
