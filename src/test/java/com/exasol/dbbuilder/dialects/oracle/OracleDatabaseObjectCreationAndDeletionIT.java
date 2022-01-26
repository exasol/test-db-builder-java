package com.exasol.dbbuilder.dialects.oracle;

import com.exasol.dbbuilder.dialects.*;
import com.exasol.dbbuilder.dialects.oracle.OracleObjectFactory;
import com.exasol.errorreporting.ExaError;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

class OracleDatabaseObjectCreationAndDeletionIT extends AbstractDatabaseObjectCreationAndDeletionIT {
    private static final String ORACLE_DOCKER_IMAGE_REFERENCE = "gvenzl/oracle-xe:21.3.0";



    @Container
    private static final OracleContainerDBA container = new OracleContainerDBA(
            ORACLE_DOCKER_IMAGE_REFERENCE);

    @Override
    protected Connection getAdminConnection() throws SQLException {
        return container.createConnectionDBA("");
    }
    private final String Username = "SYSTEM";
    @BeforeEach
    protected void beforeEach() throws SQLException {
        super.beforeEach();
        PrepOracleDatabase();
    }

    private void PrepOracleDatabase() throws SQLException {
        //adminConnection.prepareStatement("grant unlimited tablespace to kainaw;").execute();
        //adminConnection.prepareStatement("ALTER USER "+Username+" quota unlimited on users").execute();
    }

    @Override
    protected DatabaseObjectFactory getDatabaseObjectFactory(final Connection adminConnection) throws SQLException {
        return new OracleObjectFactory(adminConnection);
    }

    @Override
    protected Matcher<DatabaseObject> existsInDatabase() {
        return new ExistsInDatabaseMatcher(this.adminConnection);
    }

    private static class ExistsInDatabaseMatcher
            extends AbstractDatabaseObjectCreationAndDeletionIT.ExistsInDatabaseMatcher {
        private ExistsInDatabaseMatcher(final Connection connection) {
            super(connection);
        }

        protected String getCheckCommand(final DatabaseObject object) {
            if (object instanceof User) {
                return "SELECT 1 FROM SYS.DBA_ROLE_PRIVS WHERE GRANTEE = ?";
            } else if (object instanceof Table) {
                return "SELECT 1 FROM SYS.TABLES WHERE table_name = ?";
            } else if (object instanceof Schema) {
                return "SELECT 1 FROM SYS.DBA_OBJECTS WHERE owner = ?";
            } else {
                throw new AssertionError(ExaError.messageBuilder("E-TDBJ-32").message("Assertion for {{object}} is not yet implemented.", object.getType()).toString());
            }
        }
    }

    @Override
    @Test
    // [itest->dsn~creating-schemas~1]
    protected void testCreateSchema() {
        var username =  container.getUsername();
        assertThat(this.factory.createSchema("ps"), existsInDatabase());
    }
    @Override
    @Test
    // [itest->dsn~creating-tables~1]
    protected void testCreateTable() {
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_TABLE");
        assertThat(schema.createTable("THE_TABLE", "COL1", "DATE", "COL2", "INT"), existsInDatabase());
    }
    @Override
    @Test
    protected void testTruncateTable() throws SQLException {
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA");
        final Table table = schema.createTable("MY_TABLE", "COL1", "INTEGER").insert(1);
        table.truncate();
        assertThat(getTableSize(table), equalTo(0));
    }

}
