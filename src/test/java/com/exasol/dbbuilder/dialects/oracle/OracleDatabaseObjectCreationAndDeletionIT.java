package com.exasol.dbbuilder.dialects.oracle;

import com.exasol.dbbuilder.dialects.*;
import com.exasol.dbbuilder.dialects.oracle.OracleObjectFactory;
import com.exasol.errorreporting.ExaError;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;

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
        //return createDBAConnection("");
    }
//    public static int connectTimeoutSeconds = 120;
//    public Connection createDBAConnection(String queryString) throws SQLException, JdbcDatabaseContainer.NoDriverFoundException {
//        Properties info = new Properties();
//        //info.put("user", container.getUsername());
//        info.put("user", "SYSTEM");
//        info.put("password", container.getPassword());
//        String url = this.constructUrlForConnection(container,queryString);
//        Driver jdbcDriverInstance = container.getJdbcDriverInstance();
//        SQLException lastException = null;
//
//        try {
//            long start = System.currentTimeMillis();
//
//            while(System.currentTimeMillis() < start + (long)(1000 * connectTimeoutSeconds) && this.isRunning()) {
//                try {
//                    //logger().debug("Trying to create DBA JDBC connection.");
//                    return jdbcDriverInstance.connect(url, info);
//                } catch (SQLException var9) {
//                    lastException = var9;
//                    Thread.sleep(100L);
//                }
//            }
//        } catch (InterruptedException var10) {
//            Thread.currentThread().interrupt();
//        }
//
//        throw new SQLException("Could not create new connection", lastException);
//    }
//    protected String constructUrlForConnection(OracleContainer container,String queryString) {
//        String baseUrl = container.getJdbcUrl();
//        if ("".equals(queryString)) {
//            return baseUrl;
//        } else if (!queryString.startsWith("?")) {
//            throw new IllegalArgumentException("The '?' character must be included");
//        } else {
//            return baseUrl.contains("?") ? baseUrl + "&" + queryString.substring(1) : baseUrl + queryString;
//        }
//    }
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

}
