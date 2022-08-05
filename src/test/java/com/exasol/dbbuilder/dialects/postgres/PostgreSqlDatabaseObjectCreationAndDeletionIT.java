package com.exasol.dbbuilder.dialects.postgres;

import java.sql.Connection;
import java.sql.SQLException;

import org.hamcrest.Matcher;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import com.exasol.dbbuilder.dialects.*;
import com.exasol.errorreporting.ExaError;

class PostgreSqlDatabaseObjectCreationAndDeletionIT extends AbstractDatabaseObjectCreationAndDeletionIT {
    private static final String POSTGRES_DOCKER_IMAGE_REFERENCE = "postgres:14.4-bullseye";
    @Container
    private static final PostgreSQLContainer<? extends PostgreSQLContainer<?>> container = new PostgreSQLContainer<>(
            POSTGRES_DOCKER_IMAGE_REFERENCE);

    @Override
    protected Connection getAdminConnection() throws SQLException {
        return container.createConnection("");
    }

    @Override
    protected DatabaseObjectFactory getDatabaseObjectFactory(final Connection adminConnection) throws SQLException {
        return new PostgreSqlObjectFactory(adminConnection);
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

        @Override
        protected String getCheckCommand(final DatabaseObject object) {
            if (object instanceof User) {
                return "SELECT 1 FROM pg_catalog.pg_roles WHERE rolname = ?";
            } else if (object instanceof Table) {
                return "SELECT 1 FROM information_schema.tables WHERE table_name = ?";
            } else if (object instanceof Schema) {
                return "SELECT 1 FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";
            } else {
                throw new AssertionError(ExaError.messageBuilder("E-TDBJ-27")
                        .message("Assertion for {{object}} is not yet implemented.", object.getType()).toString());
            }
        }
    }
}
