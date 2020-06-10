package com.exasol.dbbuilder.dialects.mysql;

import static com.exasol.dbbuilder.dialects.mysql.MySqlGlobalPrivilege.CREATE_ROLE;
import static com.exasol.dbbuilder.dialects.mysql.MySqlGlobalPrivilege.CREATE_USER;
import static com.exasol.dbbuilder.dialects.mysql.MySqlObjectPrivilege.DELETE;
import static com.exasol.dbbuilder.dialects.mysql.MySqlObjectPrivilege.SELECT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.sql.*;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.exasol.dbbuilder.dialects.*;

@Tag("integration")
@Testcontainers
// [itest->dsn~mysql-object-factory~1]
class MySQLDatabaseObjectCreationIT {
    private static final String MYSQL_DOCKER_IMAGE_REFERENCE = "mysql:8.0.20";
    @Container
    private static final MySQLContainer<?> container = new MySQLContainer<>(MYSQL_DOCKER_IMAGE_REFERENCE)
            .withUsername("root").withPassword("");
    private MySqlObjectFactory factory;
    private Connection adminConnection;

    @BeforeEach
    void beforeEach() throws SQLException {
        this.adminConnection = container.createConnection("");
        this.factory = new MySqlObjectFactory(container.createConnection(""));
    }

    @Test
    void testCreateSchema() {
        final Schema schema = this.factory.createSchema("THE_SCHEMA");
        assertSchemaExistsInDatabase(schema.getName());
    }

    private void assertSchemaExistsInDatabase(final String schemaName) {
        final String sql = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = \"" + schemaName
                + "\"";
        assertObjectExistsInDatabase(sql, schemaName);
    }

    private void assertObjectExistsInDatabase(final String sql, final String objectName) {
        try (final PreparedStatement objectExistenceStatement = this.adminConnection.prepareStatement(sql)) {
            final ResultSet resultSet = objectExistenceStatement.executeQuery();
            assertAll( //
                    () -> assertThat(resultSet.next(), equalTo(true)), //
                    () -> assertThat(resultSet.getString(1), equalTo(objectName)));
        } catch (final SQLException exception) {
            throw new AssertionError("Unable to determine existence of object: " + objectName, exception);
        }
    }

    @Test
    void testCreateTable() {
        final MySqlSchema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_TABLE");
        final Table table = schema.createTable("THE_TABLE", "COL1", "DATE", "COL2", "INT");
        assertTableExistsInDatabase(table.getName());
    }

    private void assertTableExistsInDatabase(final String tableName) {
        final String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = \"" + tableName + "\"";
        assertObjectExistsInDatabase(sql, tableName);
    }

    @Test
    void testCreateUser() {
        final User user = this.factory.createUser("THE_USER");
        assertUserExistsInDatabase(user.getName());
    }

    private void assertUserExistsInDatabase(final String userName) {
        final String sql = "SELECT user FROM mysql.user WHERE user = \"" + userName + "\"";
        assertObjectExistsInDatabase(sql, userName);
    }

    @Test
    void testGrantGlobalPrivilegeToUser() {
        final User user = this.factory.createUser("SYSPRIVUSER").grant(CREATE_USER, CREATE_ROLE);
        assertAll(() -> assertUserHasGlobalPrivilege(user.getName(), CREATE_USER, "Create_user_priv"),
                () -> assertUserHasGlobalPrivilege(user.getName(), CREATE_ROLE, "Create_role_priv"));
    }

    private void assertUserHasGlobalPrivilege(final String username, final GlobalPrivilege expectedPrivilege,
            final String columnName) throws AssertionError {
        final String sql = "SELECT `" + columnName + "` FROM mysql.user WHERE User = '" + username + "'";
        assertObjectExistsInDatabase(sql, "Y");
    }

    @Test
    void testGrantSchemaPrivilegeToUser() throws InterruptedException {
        final Schema schema = this.factory.createSchema("OBJPRIVSCHEMA");
        final User user = this.factory.createUser("OBJPRIVUSER").grant(schema, SELECT, DELETE);
        assertAll(() -> assertUserHasSchemaPrivilege(user.getName(), schema.getName(), SELECT, "Select_priv"),
                () -> assertUserHasSchemaPrivilege(user.getName(), schema.getName(), DELETE, "Delete_priv"));
    }

    private void assertUserHasSchemaPrivilege(final String username, final String objectName,
            final ObjectPrivilege expectedPrivilege, final String columnName) throws AssertionError {
        final String sql = "SELECT `" + columnName + "` FROM mysql.db WHERE User = '" + username + "' AND Db = '"
                + objectName + "'";
        assertObjectExistsInDatabase(sql, "Y");
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
            assert (result.next());
            final int id1 = result.getInt(1);
            final String name1 = result.getString(2);
            assert (result.next());
            final int id2 = result.getInt(1);
            final String name2 = result.getString(2);
            assertAll(() -> assertThat("row 1, column 1", id1, equalTo(1)),
                    () -> assertThat("row 1, column 2", name1, equalTo("FOO")),
                    () -> assertThat("row 2, column 1", id2, equalTo(2)),
                    () -> assertThat("row 2, column 2", name2, equalTo("BAR")));
        } catch (final SQLException exception) {
            throw new AssertionError("Unable to validate contents of table " + table.getFullyQualifiedName(),
                    exception);
        }
    }
}