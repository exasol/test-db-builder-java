package com.exasol.dbbuilder.dialects.exasol;

import static com.exasol.dbbuilder.dialects.exasol.AdapterScript.Language.PYTHON;
import static com.exasol.dbbuilder.dialects.exasol.ExasolGlobalPrivilege.CREATE_SESSION;
import static com.exasol.dbbuilder.dialects.exasol.ExasolGlobalPrivilege.KILL_ANY_SESSION;
import static com.exasol.dbbuilder.dialects.exasol.ExasolObjectPrivilege.DELETE;
import static com.exasol.dbbuilder.dialects.exasol.ExasolObjectPrivilege.UPDATE;
import static com.exasol.matcher.ResultSetStructureMatcher.table;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.exasol.containers.ExasolContainer;
import com.exasol.dbbuilder.dialects.*;
import com.exasol.dbbuilder.dialects.exasol.udf.UdfScript;

@Tag("integration")
@Testcontainers
// [itest->dsn~exasol-object-factory~1]
class ExasolDatabaseObjectCreationAndDeletionIT extends AbstractDatabaseObjectCreationAndDeletionIT {
    @Container
    private static final ExasolContainer<? extends ExasolContainer<?>> container = new ExasolContainer<>()
            .withReuse(true);
    private static final String ADAPTER_SCRIPT_CONTENT = "def adapter_call(request):\n" + //
            "\tif 'createVirtualSchema' in request:\n"
            + "\t\treturn '{\"type\":\"createVirtualSchema\",\"schemaMetadata\":{\"tables\":[]}}'\n" + "\telse:\n"
            + "\t\treturn '{\"type\":\"dropVirtualSchema\"}'\t\t";

    @Override
    protected Connection getAdminConnection() throws SQLException {
        return container.createConnection("");
    }

    @Test
    // [itest->dsn~creating-adapter-scripts~1]
    void testCreateAdapterScript() {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory.createSchema("PARENT_SCHEMA_FOR_ADAPTER_SCRIPT");
        assertThat(exasolSchema.createAdapterScript("THE_ADAPTER_SCRIPT", PYTHON, ADAPTER_SCRIPT_CONTENT),
                existsInDatabase());
    }

    @Test
    // [itest->dsn~dropping-adapter-scripts~1]
    void testDropAdapterScript() {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory
                .createSchema("PARENT_SCHEMA_FOR_ADAPTER_SCRIPT_TO_DROP");
        final AdapterScript adapterScript = exasolSchema.createAdapterScript("THE_ADAPTER_SCRIPT_TO_DROP", PYTHON,
                ADAPTER_SCRIPT_CONTENT);
        adapterScript.drop();
        assertThat(adapterScript, not(existsInDatabase()));
    }

    private static String getSysName(final DatabaseObject object) {
        if (object instanceof AdapterScript || object instanceof UdfScript) {
            return "SCRIPT";
        } else if (object instanceof VirtualSchema) {
            return "SCHEMA";
        } else {
            return object.getType().toUpperCase().replace(" ", "_");
        }
    }

    @Override
    protected DatabaseObjectFactory getDatabaseObjectFactory(final Connection adminConnection) throws SQLException {
        return new ExasolObjectFactory(container.createConnection(""),
                ExasolObjectConfiguration.builder().withJvmOptions("-DsomeProperty=\"1\"").build());
    }

    @Test
    // [itest->dsn~creating-connections~1]
    void testCreateConnectionWithCredentials() {
        final ExasolObjectFactory exasolFactory = new ExasolObjectFactory(this.adminConnection);
        assertThat(exasolFactory.createConnectionDefinition("CONNECTION_B", "TO", "USER", "PWD"), existsInDatabase());
    }

    @Test
    // [itest->dsn~creating-connections~1]
    void testCreateConnectionWithoutCredentials() {
        final ExasolObjectFactory exasolFactory = new ExasolObjectFactory(this.adminConnection);
        assertThat(exasolFactory.createConnectionDefinition("CONNECTION_A", "TO"), existsInDatabase());
    }

    @Test
    // [itest->dsn~dropping-connections~1]
    void testDropConnection() {
        final ExasolObjectFactory exasolFactory = new ExasolObjectFactory(this.adminConnection);
        final ConnectionDefinition connection = exasolFactory.createConnectionDefinition("CONNECTION_A", "TO");
        connection.drop();
        assertThat(connection, not(existsInDatabase()));
    }

    @Test
    // [itest->dsn~creating-scripts~1]
    void testCreateScript() {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory.createSchema("PARENT_SCHEMA_FOR_SCRIPT");
        assertThat(exasolSchema.createScript("THE_SCRIPT", "print(\"Hello World\")"), existsInDatabase());
    }

    @Test
    // [itest->dsn~dropping-scripts~1]
    void testDropScript() {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory.createSchema("PARENT_SCHEMA_FOR_SCRIPT_2");
        final Script script = exasolSchema.createScript("THE_SCRIPT_2", "print(\"Hello World\")");
        script.drop();
        assertThat(script, not(existsInDatabase()));
    }

    @Test
    // [itest->dsn~creating-scripts-from-files~1]
    // [itest->dsn~running-scripts-that-have-no-return~1]
    void testCreateScriptFromFile(@TempDir final Path tempDir) throws IOException, SQLException {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory.createSchema("PARENT_SCHEMA_FOR_SCRIPT_FILE");
        final Table table = exasolSchema.createTable("LUA_RESULT", "CHECK", "BOOLEAN");
        final Path tempFile = tempDir.resolve("script.lua");
        final String content = "query([[INSERT INTO " + table.getFullyQualifiedName() + " VALUES (true)]])";
        Files.write(tempFile, content.getBytes());
        final Script script = exasolSchema.createScript("LUA_SCRIPT", tempFile);
        script.execute();
        final Statement statement = this.adminConnection.createStatement();
        final ResultSet result = statement.executeQuery("SELECT * FROM " + table.getFullyQualifiedName());
        assertThat(result.next(), equalTo(true));
    }

    @Test
    // [itest->dsn~running-scripts-that-have-no-return~1]
    void testExecuteScriptWithParameters() throws SQLException {
        final String param1 = "foobar";
        final double param2 = 3.1415;
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory
                .createSchema("PARENT_SCHEMA_FOR_SCRIPT_WITH_PARAMETERS");
        final Table table = exasolSchema.createTable("LUA_RESULT", "A", "VARCHAR(20)", "B", "DOUBLE");
        final String content = "query([[INSERT INTO " + table.getFullyQualifiedName()
                + " VALUES (:p1, :p2)]], {p1=param1, p2=param2})";
        final Script script = exasolSchema.createScript("LUA_SCRIPT", content, "param1", "param2");
        script.execute(param1, param2);
        final Statement statement = this.adminConnection.createStatement();
        final ResultSet result = statement.executeQuery("SELECT * FROM " + table.getFullyQualifiedName());
        assertAll(() -> assertThat("Result has entry", result.next(), equalTo(true)),
                () -> assertThat(result.getString(1), equalTo(param1)),
                () -> assertThat(result.getDouble(2), equalTo(param2)));
    }

    @Test
    void testExecuteScriptReturningRowCount() {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory
                .createSchema("PARENT_SCHEMA_FOR_SCRIPT_RETURNING_ROW_COUNT");
        final Script script = exasolSchema.createScriptBuilder("LUA_SCRIPT").content("exit({rows_affected=42})")
                .build();
        final int rowCount = script.execute();
        assertThat(rowCount, equalTo(42));
    }

    @Test
    void testExecuteScriptReturningTable() {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory
                .createSchema("PARENT_SCHEMA_FOR_SCRIPT_RETURNING_TABLE");
        final Script script = exasolSchema.createScriptBuilder("LUA_SCRIPT") //
                .content("exit({{\"foo\", true}, {\"bar\", false}}, \"C CHAR(3), B BOOLEAN\")") //
                .returnsTable() //
                .build();
        final List<List<Object>> result = script.executeQuery();
        assertThat(result, contains(contains("foo", true), contains("bar", false)));
    }

    @Test
    // [itest->dsn~creating-udfs~1]
    void testCreateUdf() throws SQLException {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory.createSchema("PARENT_SCHEMA_FOR_UDF");
        exasolSchema.createUdfBuilder("UDF_TEST").inputType(UdfScript.InputType.SET).language(UdfScript.Language.PYTHON)
                .emits().content("print('HI')").build();
        assertThat(getScriptDescription(exasolSchema),
                table().row("CREATE PYTHON SET SCRIPT \"UDF_TEST\" (...) EMITS (...) AS\nprint('HI')\n").matches());
    }

    @Test
    // [itest->dsn~dropping-udfs~1]
    void testDropUdf() {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory.createSchema("PARENT_SCHEMA_FOR_UDF_DROP");
        final UdfScript udf = exasolSchema.createUdfBuilder("UDF_TO_DROP").inputType(UdfScript.InputType.SET)
                .language(UdfScript.Language.PYTHON).emits().content("print('HI')").build();
        udf.drop();
        assertThat(udf, not(existsInDatabase()));
    }

    @Test
    void testCreateUdfWithParameter() throws SQLException {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory
                .createSchema("PARENT_SCHEMA_FOR_UDF_WITH_PARAMETER");
        exasolSchema.createUdfBuilder("UDF_TEST").inputType(UdfScript.InputType.SET).language(UdfScript.Language.PYTHON)
                .parameter("test", "VARCHAR(254)").emits().content("print('HI')").build();
        assertThat(getScriptDescription(exasolSchema), table().row(
                "CREATE PYTHON SET SCRIPT \"UDF_TEST\" (\"test\" VARCHAR(254) UTF8) EMITS (...) AS\n" + "print('HI')\n")
                .matches());
    }

    @Test
    void testCreateUdfWithPredefinedEmits() throws SQLException {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory
                .createSchema("PARENT_SCHEMA_FOR_UDF_WITH_PREDEFINED_EMITS");
        exasolSchema.createUdfBuilder("UDF_TEST").inputType(UdfScript.InputType.SET).language(UdfScript.Language.PYTHON)
                .parameter("test", "VARCHAR(254)")
                .emits(new Column("C1", "VARCHAR(254)"), new Column("C2", "VARCHAR(254)")).content("print('HI')")
                .build();
        assertThat(getScriptDescription(exasolSchema), table().row(
                "CREATE PYTHON SET SCRIPT \"UDF_TEST\" (\"test\" VARCHAR(254) UTF8) EMITS (\"C1\" VARCHAR(254) UTF8, \"C2\" VARCHAR(254) UTF8) AS\nprint('HI')\n")
                .matches());
    }

    @Test
    void testCreateJavaUdf() throws SQLException {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory.createSchema("PARENT_SCHEMA_FOR_JAVA_UDF");
        exasolSchema.createUdfBuilder("UDF_TEST").inputType(UdfScript.InputType.SET).language(UdfScript.Language.JAVA)
                .emits().bucketFsContent("com.exasol.MyClass", "/buckets/bfsdefault/default/test.jar").build();
        assertThat(getScriptDescription(exasolSchema), table().row(
                "CREATE JAVA SET SCRIPT \"UDF_TEST\" (...) EMITS (...) AS\n%jvmoption -DsomeProperty=\"1\";\n\n%scriptclass com.exasol.MyClass;\n%jar /buckets/bfsdefault/default/test.jar;\n\n")
                .matches());
    }

    @Test
    void testCreateReturnsUdf() throws SQLException {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory
                .createSchema("PARENT_SCHEMA_FOR_UDF_WITH_RETURNS");
        exasolSchema.createUdfBuilder("UDF_TEST").inputType(UdfScript.InputType.SCALAR)
                .language(UdfScript.Language.PYTHON).returns("VARCHAR(254)").content("print('HI')").build();
        assertThat(getScriptDescription(exasolSchema), table()
                .row("CREATE PYTHON SCALAR SCRIPT \"UDF_TEST\" (...) RETURNS VARCHAR(254) UTF8 AS\n" + "print('HI')\n")
                .matches());
    }

    private ResultSet getScriptDescription(final ExasolSchema exasolSchema) throws SQLException {
        final String sql = "SELECT SCRIPT_TEXT FROM EXA_ALL_SCRIPTS WHERE SCRIPT_SCHEMA = '" + exasolSchema.getName()
                + "'";
        final ResultSet result = getAdminConnection().createStatement().executeQuery(sql);
        return result;
    }

    @ValueSource(booleans = { true, false })
    @ParameterizedTest
    void testExecuteScriptThrowsException(final boolean returnsTable) {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory.createSchema(
                "PARENT_SCHEMA_FOR_SCRIPT" + (returnsTable ? "_RETURING_TABLE" : "") + "_THROWING_EXCEPTION");
        final Script.Builder builder = exasolSchema.createScriptBuilder("LUA_SCRIPT").content("error()");
        if (returnsTable) {
            builder.returnsTable();
        }
        final Script build = builder.build();
        assertThrows(DatabaseObjectException.class, build::executeQuery);
    }

    @Test
    void testExecuteScriptWithArrayParameter() {
        final ExasolSchema exasolSchema = (ExasolSchema) this.factory
                .createSchema("PARENT_SCHEMA_FOR_SCRIPT_WITH_ARRAY_PARAMETER");
        final Script script = exasolSchema.createScriptBuilder("SUM_UP") //
                .arrayParameter("operands") //
                .content("s = 0\n" //
                        + "for i=1, #operands do\n" //
                        + "    s = s + operands[i]\n" //
                        + "end\n" //
                        + "exit({rows_affected=s})") //
                .build();
        final int sum = script.execute(List.of(1, 2, 3, 4, 5));
        assertThat(sum, equalTo(15));
    }

    @Test
    // [itest->dsn~creating-database-users~1]
    void testCreateLoginUser() throws SQLException {
        final User user = this.factory.createLoginUser("LOGIN_USER");
        try (final Connection connection = container.createConnectionForUser(user.getName(), user.getPassword())) {
            assertThat(connection.isClosed(), equalTo(false));
        }
    }

    @Test
    // [itest->dsn~creating-database-users~1]
    void testCreateLoginUserWithPassword() throws SQLException {
        final User user = this.factory.createLoginUser("LOGIN_USER_WITH_PASSWORD", "THE_PASSWORD");
        try (final Connection connection = container.createConnectionForUser(user.getName(), user.getPassword())) {
            assertThat(connection.isClosed(), equalTo(false));
        }
    }

    @Test
    // [itest->dsn~creating-virtual-schemas~1]
    void testCreateVirtualSchema() {
        final ExasolObjectFactory exasolFactory = new ExasolObjectFactory(this.adminConnection);
        final ConnectionDefinition connectionDefinition = exasolFactory.createConnectionDefinition("THE_CONNECTION",
                "destination");
        final ExasolSchema exasolSchema = exasolFactory.createSchema("PARENT_SCHEMA_FOR_VIRTUAL_SCHEMA");
        final AdapterScript adapterScript = exasolSchema.createAdapterScript("ADAPTER_SCRIPT_FOR_VS", PYTHON,
                ADAPTER_SCRIPT_CONTENT);
        assertThat(
                exasolFactory.createVirtualSchemaBuilder("THE_VIRTUAL_SCHEMA").dialectName("Exasol")
                        .adapterScript(adapterScript).connectionDefinition(connectionDefinition).build(),
                existsInDatabase());
    }

    @Test
    // [itest->dsn~dropping-virtual-schemas~2]
    void testDropVirtualSchema() {
        final ExasolObjectFactory exasolFactory = new ExasolObjectFactory(this.adminConnection);
        final ConnectionDefinition connectionDefinition = exasolFactory.createConnectionDefinition("THE_CONNECTION_2",
                "destination");
        final ExasolSchema exasolSchema = exasolFactory.createSchema("PARENT_SCHEMA_FOR_VIRTUAL_SCHEMA_2");
        final AdapterScript adapterScript = exasolSchema.createAdapterScript("THE_ADAPTER_SCRIPT_2", PYTHON,
                ADAPTER_SCRIPT_CONTENT);
        final VirtualSchema virtualSchema = exasolFactory.createVirtualSchemaBuilder("THE_VIRTUAL_SCHEMA_2")
                .dialectName("Exasol").adapterScript(adapterScript).connectionDefinition(connectionDefinition).build();
        virtualSchema.drop();
        assertThat(virtualSchema, not(existsInDatabase()));
    }

    @Test
    // [itest->dsn~granting-system-privileges-to-database-users~1]
    void testGrantGlobalPrivilegeToUser() {
        final User user = this.factory.createUser("SYSPRIVUSER").grant(CREATE_SESSION, KILL_ANY_SESSION);
        assertAll(() -> assertUserHasGlobalPrivilege(user, CREATE_SESSION),
                () -> assertUserHasGlobalPrivilege(user, KILL_ANY_SESSION));
    }

    private void assertUserHasGlobalPrivilege(final User user, final GlobalPrivilege expectedPrivilege)
            throws AssertionError {
        try (final PreparedStatement statement = this.adminConnection
                .prepareStatement("SELECT 1 FROM SYS.EXA_DBA_SYS_PRIVS WHERE GRANTEE=? AND PRIVILEGE=?")) {
            statement.setString(1, user.getName());
            statement.setString(2, expectedPrivilege.getSqlName());
            final ResultSet result = statement.executeQuery();
            assertThat("User " + user.getFullyQualifiedName() + " has global privilege " + expectedPrivilege,
                    result.next(), equalTo(true));
        } catch (final SQLException exception) {
            throw new AssertionError("Unable to determine if user " + user.getFullyQualifiedName()
                    + " has global privilege " + expectedPrivilege + ".", exception);
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
            statement.setString(3, expectedObjectPrivilege.getSqlName());
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
        final Table table = schema.createTable("INSERTTABLE", "ID", "INT", "NAME", "VARCHAR(10)");
        table.insert(1, "FOO").insert(2, "BAR");
        try {
            final ResultSet result = this.adminConnection.createStatement()
                    .executeQuery("SELECT ID, NAME FROM " + table.getFullyQualifiedName() + "ORDER BY ID ASC");
            assertThat(result, table().row(1L, "FOO").row(2L, "BAR").matches());
        } catch (final SQLException exception) {
            throw new AssertionError("Unable to validate contents of table " + table.getFullyQualifiedName(),
                    exception);
        }
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
            try (final PreparedStatement objectExistenceStatement = this.connection
                    .prepareStatement("SELECT 1 FROM SYS.EXA_ALL_" + getTableSysName(object) + "S WHERE "
                            + getSysName(object) + "_NAME=?")) {
                objectExistenceStatement.setString(1, object.getName());
                try (final ResultSet resultSet = objectExistenceStatement.executeQuery()) {
                    return resultSet.next();
                }
            } catch (final SQLException exception) {
                throw new AssertionError("Unable to determine existence of " + object.getType() + " "
                        + object.getFullyQualifiedName() + ".", exception);
            }
        }

        private static String getTableSysName(final DatabaseObject object) {
            if (object instanceof AdapterScript || object instanceof UdfScript) {
                return "SCRIPT";
            } else {
                return object.getType().toUpperCase().replace(" ", "_");
            }
        }
    }
}