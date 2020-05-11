package com.exasol.dbbuilder;

import static com.exasol.dbbuilder.AdapterScript.Language.PYTHON;
import static com.exasol.dbbuilder.ObjectPrivilege.DELETE;
import static com.exasol.dbbuilder.ObjectPrivilege.UPDATE;
import static com.exasol.dbbuilder.SystemPrivilege.CREATE_SESSION;
import static com.exasol.dbbuilder.SystemPrivilege.KILL_ANY_SESSION;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer.NoDriverFoundException;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.exasol.containers.ExasolContainer;
import com.exasol.containers.ExasolContainerConstants;

@Tag("integration")
@Testcontainers
class DatabaseObjectCreationIT {
    @Container
    private static final ExasolContainer<? extends ExasolContainer<?>> container = new ExasolContainer<>(
            ExasolContainerConstants.EXASOL_DOCKER_IMAGE_REFERENCE);
    private static final String ADAPTER_SCRIPT_CONTENT = "def adapter_call(request):" //
            + "    return '{\"type\":\"createVirtualSchema\",\"schemaMetadata\":{\"tables\":[]}}'";
    private DatabaseObjectFactory factory;
    private Connection adminConnection;

    @BeforeEach
    void beforeEach() throws NoDriverFoundException, SQLException {
        this.adminConnection = container.createConnection("");
        this.factory = new ExasolObjectFactory(container.createConnection(""));
    }

    @Test
    void testCreateAdapterScript() {
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_ADAPTER_SCRIPT");
        assertObjectExistsInDatabase(schema.createAdapterScript("THE_ADAPTER_SCRIPT", PYTHON, ADAPTER_SCRIPT_CONTENT));
    }

    private void assertObjectExistsInDatabase(final DatabaseObject object) {
        try (final PreparedStatement objectExistenceStatement = this.adminConnection.prepareStatement(
                "SELECT 1 FROM SYS.EXA_ALL_" + getTableSysName(object) + "S WHERE " + getSysName(object) + "_NAME=?")) {
            objectExistenceStatement.setString(1, object.getName());
            final ResultSet resultSet = objectExistenceStatement.executeQuery();
            assertThat("Object" + object.getType() + " " + object.getFullyQualifiedName() + " exists in database",
                    resultSet.next(), equalTo(true));
        } catch (final SQLException exception) {
            throw new AssertionError(
                    "Unable to determine existence of " + object.getType() + " " + object.getFullyQualifiedName() + ".",
                    exception);
        }
    }

    private String getTableSysName(final DatabaseObject object) {
        if (object instanceof AdapterScript) {
            return "SCRIPT";
        } else {
            return object.getType().toUpperCase().replace(" ", "_");
        }
    }

    private String getSysName(final DatabaseObject object) {
        if (object instanceof AdapterScript) {
            return "SCRIPT";
        } else if (object instanceof VirtualSchema) {
            return "SCHEMA";
        } else {
            return object.getType().toUpperCase().replace(" ", "_");
        }
    }

    @Test
    void testCreateConnectionWithCredentials() {
        assertObjectExistsInDatabase(this.factory.createConnectionDefinition("CONNECTION_B", "TO", "USER", "PWD"));
    }

    @Test
    void testCreateConnectionWithoutCredentials() {
        assertObjectExistsInDatabase(this.factory.createConnectionDefinition("CONNECTION_A", "TO"));
    }

    @Test
    void testCreateSchema() {
        assertObjectExistsInDatabase(this.factory.createSchema("THE_SCHEMA"));
    }

    @Test
    // [itest->dsn~creating-scripts~1]
    void testCreateScript() {
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_SCRIPT");
        assertObjectExistsInDatabase(schema.createScript("THE_SCRIPT", "print(\"Hello World\")"));
    }

    @Test
    void testCreateTable() {
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_TABLE");
        assertObjectExistsInDatabase(schema.createTable("THE_TABLE", "COL1", "DATE", "COL2", "NUMBER"));
    }

    @Test
    void testCreateUser() {
        assertObjectExistsInDatabase(this.factory.createUser("THE_USER"));
    }

    @Test
    void testCreateLoginUser() throws SQLException {
        final User user = this.factory.createLoginUser("LOGIN_USER");
        try (final Connection connection = container.createConnectionForUser(user.getName(), user.getPassword())) {
            assertThat(connection.isClosed(), equalTo(false));
        }
    }

    @Test
    void testCreateLoginUserWithPassword() throws SQLException {
        final User user = this.factory.createLoginUser("LOGIN_USER_WITH_PASSWORD", "THE_PASSWORD");
        try (final Connection connection = container.createConnectionForUser(user.getName(), user.getPassword())) {
            assertThat(connection.isClosed(), equalTo(false));
        }
    }

    @Test
    void testCreateVirtualSchema() {
        final ConnectionDefinition connectionDefinition = this.factory.createConnectionDefinition("THE_CONNECTION",
                "destination");
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_VIRTUAL_SCHEMA");
        final AdapterScript adapterScript = schema.createAdapterScript("THE_ADAPTER_SCRIPT", PYTHON,
                ADAPTER_SCRIPT_CONTENT);
        assertObjectExistsInDatabase(this.factory.createVirtualSchemaBuilder("THE_VIRTUAL_SCHEMA").dialectName("Exasol")
                .adapterScript(adapterScript).connectionDefinition(connectionDefinition).build());
    }

    @Test
    void testGrantSystemPrivilegeToUser() {
        final User user = this.factory.createUser("SYSPRIVUSER").grant(CREATE_SESSION, KILL_ANY_SESSION);
        assertAll(() -> assertUserHasSystemPrivilege(user, CREATE_SESSION),
                () -> assertUserHasSystemPrivilege(user, KILL_ANY_SESSION));
    }

    private void assertUserHasSystemPrivilege(final User user, final SystemPrivilege expectedPrivilege)
            throws AssertionError {
        try (final PreparedStatement statement = this.adminConnection
                .prepareStatement("SELECT 1 FROM SYS.EXA_DBA_SYS_PRIVS WHERE GRANTEE=? AND PRIVILEGE=?")) {
            statement.setString(1, user.getName());
            statement.setString(2, expectedPrivilege.toString());
            final ResultSet result = statement.executeQuery();
            assertThat("User " + user.getFullyQualifiedName() + " has system privilege " + expectedPrivilege,
                    result.next(), equalTo(true));
        } catch (final SQLException exception) {
            throw new AssertionError("Unable to determine if user " + user.getFullyQualifiedName()
                    + " has system privilege " + expectedPrivilege + ".", exception);
        }
    }

    @Test
    void testGrantObjectPrivilegeToUser() {
        final DatabaseObject schema = this.factory.createSchema("OBJPRIVSCHEMA");
        final User user = this.factory.createUser("OBJPRIVUSER").grant(schema, UPDATE, DELETE);
        assertAll(() -> assertUserHasObjectPrivilege(user, schema, UPDATE),
                () -> assertUserHasObjectPrivilege(user, schema, DELETE));
    }

    private void assertUserHasObjectPrivilege(final User user, final DatabaseObject object,
            final ObjectPrivilege expectedObjectPrivilege) {
        try (final PreparedStatement statement = this.adminConnection.prepareStatement(
                "SELECT 1 FROM SYS.EXA_DBA_OBJ_PRIVS WHERE GRANTEE=? AND OBJECT_NAME=? AND PRIVILEGE=?")) {
            statement.setString(1, user.getName());
            statement.setString(2, object.getName());
            statement.setString(3, expectedObjectPrivilege.toString());
            final ResultSet result = statement.executeQuery();
            assertThat("User " + user.getFullyQualifiedName() + " has privilege " + expectedObjectPrivilege + " on "
                    + object.getFullyQualifiedName(), result.next(), equalTo(true));
        } catch (final Exception exception) {
            throw new AssertionError("Unable to determine if user " + user.getFullyQualifiedName() + " has privilege "
                    + expectedObjectPrivilege + " on " + object.getFullyQualifiedName() + ".", exception);
        }
    }

    @Test
    void testInsertIntoTable() {
        final Schema schema = this.factory.createSchema("INSERTSCHEMA");
        final Table table = schema.createTable("INSERTTABLE", "ID", "DECIMAL(3,0)", "NAME", "VARCHAR(10)");
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