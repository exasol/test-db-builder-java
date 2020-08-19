package com.exasol.dbbuilder.dialects.mysql;

import static com.exasol.dbbuilder.dialects.mysql.MySqlGlobalPrivilege.CREATE_ROLE;
import static com.exasol.dbbuilder.dialects.mysql.MySqlGlobalPrivilege.CREATE_USER;
import static com.exasol.dbbuilder.dialects.mysql.MySqlObjectPrivilege.DELETE;
import static com.exasol.dbbuilder.dialects.mysql.MySqlObjectPrivilege.SELECT;
import static com.exasol.matcher.ResultSetStructureMatcher.table;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.sql.*;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.exasol.dbbuilder.dialects.*;

@Tag("integration")
@Testcontainers
// [itest->dsn~mysql-object-factory~1]
class MySQLDatabaseObjectCreationAndDeletionIT extends AbstractDatabaseObjectCreationAndDeletionIT {
    private static final String MYSQL_DOCKER_IMAGE_REFERENCE = "mysql:8.0.20";
    @Container
    private static final MySQLContainer<?> container = new MySQLContainer<>(MYSQL_DOCKER_IMAGE_REFERENCE)
            .withUsername("root").withPassword("");

    @Override
    protected Connection getAdminConnection() throws SQLException {
        return container.createConnection("");
    }

    @Override
    protected DatabaseObjectFactory getDatabaseObjectFactory() throws SQLException {
        return new MySqlObjectFactory(container.createConnection(""));
    }

    @Override
    protected Matcher<DatabaseObject> existsInDatabase() {
        return new ExistsInDatabaseMatcher();
    }

    private void assertUserHasGlobalPrivilege(final String username, final String columnName) throws AssertionError {
        final String sql = "SELECT `" + columnName + "` FROM mysql.user WHERE User = '" + username + "'";
        assertStatementRendersToYes(columnName, sql);
    }

    @Test
    void testGrantGlobalPrivilegeToUser() {
        final User user = this.factory.createUser("SYSPRIVUSER").grant(CREATE_USER, CREATE_ROLE);
        assertAll(() -> assertUserHasGlobalPrivilege(user.getName(), "Create_user_priv"),
                () -> assertUserHasGlobalPrivilege(user.getName(), "Create_role_priv"));
    }

    private void assertStatementRendersToYes(final String columnName, final String sql) {
        try (final PreparedStatement objectExistenceStatement = MySQLDatabaseObjectCreationAndDeletionIT.this.adminConnection
                .prepareStatement(sql); final ResultSet resultSet = objectExistenceStatement.executeQuery()) {
            resultSet.next();
            assertThat(resultSet.getString(1), equalTo("Y"));
        } catch (final SQLException exception) {
            throw new AssertionError("Unable to determine existence of object: " + columnName, exception);
        }
    }

    private void assertUserHasSchemaPrivilege(final String username, final String objectName, final String columnName)
            throws AssertionError {
        final String sql = "SELECT `" + columnName + "` FROM mysql.db WHERE User = '" + username + "' AND Db = '"
                + objectName + "'";
        assertStatementRendersToYes(columnName, sql);
    }

    @Test
    void testGrantSchemaPrivilegeToUser() throws InterruptedException {
        final Schema schema = this.factory.createSchema("OBJPRIVSCHEMA");
        final User user = this.factory.createUser("OBJPRIVUSER").grant(schema, SELECT, DELETE);
        assertAll(() -> assertUserHasSchemaPrivilege(user.getName(), schema.getName(), "Select_priv"),
                () -> assertUserHasSchemaPrivilege(user.getName(), schema.getName(), "Delete_priv"));
    }

    private class ExistsInDatabaseMatcher extends AbstractDatabaseObjectCreationAndDeletionIT.ExistsInDatabaseMatcher {
        @Override
        protected boolean matchesSafely(final DatabaseObject object) {
            try (final PreparedStatement objectExistenceStatement = MySQLDatabaseObjectCreationAndDeletionIT.this.adminConnection
                    .prepareStatement(getCheckCommand(object));
                    final ResultSet resultSet = objectExistenceStatement.executeQuery()) {
                return resultSet.next() && resultSet.getString(1).equals(object.getName());
            } catch (final SQLException exception) {
                throw new AssertionError("Unable to determine existence of object: " + object.getName(), exception);
            }
        }

        private String getCheckCommand(final DatabaseObject object) {
            if (object instanceof User) {
                final User user = (User) object;
                return "SELECT user FROM mysql.user WHERE user = \"" + user.getName() + "\"";
            } else if (object instanceof Table) {
                final Table table = (Table) object;
                return "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = \"" + table.getName()
                        + "\"";
            } else if (object instanceof Schema) {
                final Schema schema = (Schema) object;
                return "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = \"" + schema.getName()
                        + "\"";
            } else {
                throw new AssertionError("Assertion for " + object.getType() + " is not yet implemented.");
            }
        }
    }

    @Test
    void testGrantTablePrivilegeToUser() throws InterruptedException {
        final Schema schema = this.factory.createSchema("TABPRIVSCHEMA");
        final Table table = schema.createTable("TABPRIVTABLE", "COL1", "DATE", "COL2", "INT");
        final User user = this.factory.createUser("TABPRIVUSER").grant(table, SELECT, DELETE);
        assertAll(() -> assertUserHasTablePrivilege(user.getName(), table.getName(), "Select"),
                () -> assertUserHasTablePrivilege(user.getName(), table.getName(), "Delete"));
    }

    private void assertUserHasTablePrivilege(final String username, final String objectName,
            final String expectedPrivilege) throws AssertionError {
        try (final PreparedStatement statement = this.adminConnection
                .prepareStatement("SELECT `Table_priv` FROM mysql.tables_priv WHERE User = '" + username
                        + "' AND Table_name = '" + objectName + "'")) {
            final ResultSet result = statement.executeQuery();
            assertAll(() -> assertThat(result.next(), equalTo(true)),
                    () -> assertThat(result.getString(1), containsString(expectedPrivilege)));
        } catch (final SQLException exception) {
            throw new AssertionError(
                    "Unable to determine if user " + username + " has global privilege " + expectedPrivilege + ".",
                    exception);
        }
    }

    @Test
    void testInsertIntoTable() {
        final Schema schema = this.factory.createSchema("INSERTSCHEMA");
        final Table table = schema.createTable("INSERTTABLE", "ID", "INT", "NAME", "VARCHAR(10)");
        table.insert(1, "FOO").insert(2, "BAR");
        try {
            final ResultSet result = this.adminConnection.createStatement()
                    .executeQuery("SELECT ID, NAME FROM " + table.getFullyQualifiedName() + "ORDER BY ID ASC");
            assertThat(result, table().row(1, "FOO").row(2, "BAR").matches());
        } catch (final SQLException exception) {
            throw new AssertionError("Unable to validate contents of table " + table.getFullyQualifiedName(),
                    exception);
        }
    }
}