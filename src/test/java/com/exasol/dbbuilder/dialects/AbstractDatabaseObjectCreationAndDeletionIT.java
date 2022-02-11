package com.exasol.dbbuilder.dialects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.sql.*;

import org.hamcrest.*;
import org.junit.jupiter.api.*;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.exasol.errorreporting.ExaError;

@Tag("integration")
@Testcontainers
@SuppressWarnings("java:S5786") // this class should be public as implementation classes are in different packages
public abstract class AbstractDatabaseObjectCreationAndDeletionIT {
    private static final String QUOTES = "` `` ' '' \" \"\"";
    protected DatabaseObjectFactory factory;
    protected Connection adminConnection;

    @BeforeEach
    void beforeEach() throws SQLException {
        this.adminConnection = getAdminConnection();
        this.factory = getDatabaseObjectFactory(this.adminConnection);
    }

    @AfterEach
    void afterEach() throws SQLException {
        this.adminConnection.close();
    }

    protected abstract Connection getAdminConnection() throws SQLException;

    protected abstract DatabaseObjectFactory getDatabaseObjectFactory(Connection adminConnection) throws SQLException;

    protected abstract Matcher<DatabaseObject> existsInDatabase();

    @Test
    // [itest->dsn~creating-schemas~1]
    protected void testCreateSchema() {
        assertThat(this.factory.createSchema("THE_SCHEMA"), existsInDatabase());
    }

    @Test
    // [itest->dsn~dropping-schemas~2]
    protected void testDropSchema() {
        final Schema schema = this.factory.createSchema("SCHEMA_TO_DROP");
        schema.drop();
        assertThat(schema, not(existsInDatabase()));
    }

    @Test
    // [itest->dsn~dropping-schemas~2]
    void testDropSchemaCascades() {
        final Schema schema = this.factory.createSchema("SCHEMA_TO_DROP");
        final Table table = schema.createTable("TABLE_IN_SCHEMA_TO_DROP", "COL1", "DATE");
        schema.drop();
        assertAll(//
                () -> assertThat(schema, not(existsInDatabase())), //
                () -> assertThat(table, not(existsInDatabase())), //
                () -> assertThat(schema.getTables(), empty())//
        );
    }

    @Test
    protected void testCreateSchemaIsSqlInjectionSafe() {
        final Schema schema = this.factory.createSchema("INJECTION_TEST" + QUOTES);
        assertThat(schema, existsInDatabase());
    }

    @Test
    // [itest->dsn~creating-tables~1]
    protected void testCreateTable() {
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_TABLE");
        assertThat(schema.createTable("THE_TABLE", "COL1", "DATE", "COL2", "INT"), existsInDatabase());
    }

    @Test
    // [itest->dsn~dropping-tables~1]
    void testDropTable() {
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_TABLE_2");
        final Table table = schema.createTable("THE_TABLE_TO_DROP", "COL1", "DATE", "COL2", "INT");
        table.drop();
        assertThat(table, not(existsInDatabase()));
    }

    @Test
    protected void testTruncateTable() throws SQLException {
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_TRUNCATE_TABLE");
        final Table table = schema.createTable("MY_TABLE", "COL1", "INTEGER").insert(1);
        table.truncate();
        assertThat(getTableSize(table), equalTo(0));
    }

    protected int getTableSize(final Table table) throws SQLException {
        try (final Statement statement = getAdminConnection().createStatement();
                final ResultSet resultSet = statement
                        .executeQuery("SELECT COUNT(*) FROM " + table.getFullyQualifiedName())) {
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    @Test
    protected void testCreateTableIsSqlInjectionSafe() {
        final Table table = this.factory.createSchema("INJECTION_TEST").createTable("INJECTION_TEST_TABLE " + QUOTES,
                "MY_COL" + QUOTES, "INTEGER");
        assertThat(table, existsInDatabase());
    }

    @Test
    // [itest->dsn~creating-database-users~1]
    void testCreateUser() {
        assertThat(this.factory.createUser("THE_USER"), existsInDatabase());
    }

    @Test
    // [itest->dsn~dropping-users~1]
    void testDropUser() {
        final User user = this.factory.createLoginUser("USER_TO_DROP");
        user.drop();
        assertThat(user, not(existsInDatabase()));
    }

    protected abstract static class ExistsInDatabaseMatcher extends TypeSafeMatcher<DatabaseObject> {
        private final Connection connection;

        protected ExistsInDatabaseMatcher(final Connection connection) {
            this.connection = connection;
        }

        /**
         * Get a SQL command that checks if the database object exists in the database.
         * <p>
         * The sql command must have one placeholder that will be filled with the name of the sql object to match.
         * </p>
         * 
         * @param object database object to match
         * @return sql statement
         */
        protected abstract String getCheckCommand(final DatabaseObject object);

        private boolean matchResult(final DatabaseObject object, final PreparedStatement objectExistenceStatement)
                throws SQLException {
            try (final ResultSet resultSet = objectExistenceStatement.executeQuery()) {
                return resultSet.next();
            }
        }

        @Override
        protected boolean matchesSafely(final DatabaseObject object) {
            try (final PreparedStatement objectExistenceStatement = this.connection
                    .prepareStatement(getCheckCommand(object))) {
                objectExistenceStatement.setString(1, object.getName());
                return matchResult(object, objectExistenceStatement);
            } catch (final SQLException exception) {
                throw new AssertionError(ExaError.messageBuilder("E-TDBJ-19").message("Unable to determine existence of object: {{object}}", object.getName()).toString(), exception);
            }
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("database object exists in database");
        }

        @Override
        protected void describeMismatchSafely(final DatabaseObject item, final Description mismatchDescription) {
            mismatchDescription.appendText("could not find ").appendText(item.getType()).appendText(" ")
                    .appendText(item.getFullyQualifiedName()).appendText(" in the database");
        }
    }
}