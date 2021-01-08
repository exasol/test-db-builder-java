package com.exasol.dbbuilder.dialects.postgres;

import java.sql.*;

import org.hamcrest.Matcher;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import com.exasol.dbbuilder.dialects.*;

class PostgreSqlDatabaseObjectCreationAndDeletionIT extends AbstractDatabaseObjectCreationAndDeletionIT {
    private static final String POSTGRES_DOCKER_IMAGE_REFERENCE = "postgres:13.1";
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
        private final Connection connection;

        private ExistsInDatabaseMatcher(final Connection connection) {
            this.connection = connection;
        }

        @Override
        protected boolean matchesSafely(final DatabaseObject object) {
            final String checkCommand = getCheckCommand(object);
            try (final PreparedStatement objectExistenceStatement = this.connection.prepareStatement(checkCommand);
                    final ResultSet resultSet = objectExistenceStatement.executeQuery()) {
                return resultSet.next() && resultSet.getString(1).equals(object.getName());
            } catch (final SQLException exception) {
                throw new AssertionError("Unable to determine existence of object: " + object.getName(), exception);
            }
        }

        private String getCheckCommand(final DatabaseObject object) {
            if (object instanceof User) {
                final User user = (User) object;
                return "SELECT rolname FROM pg_catalog.pg_roles WHERE rolname = '" + user.getName() + "'";
            } else if (object instanceof Table) {
                final Table table = (Table) object;
                return "SELECT table_name FROM information_schema.tables WHERE table_name = '" + table.getName() + "'";
            } else if (object instanceof Schema) {
                final Schema schema = (Schema) object;
                return "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + schema.getName()
                        + "'";
            } else {
                throw new AssertionError("Assertion for " + object.getType() + " is not yet implemented.");
            }
        }
    }
}
