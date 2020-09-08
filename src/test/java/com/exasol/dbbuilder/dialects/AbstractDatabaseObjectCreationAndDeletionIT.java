package com.exasol.dbbuilder.dialects;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.sql.Connection;
import java.sql.SQLException;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@Tag("integration")
@Testcontainers
@SuppressWarnings("java:S5786") // this class should be public as implementation classes are in different packages
public abstract class AbstractDatabaseObjectCreationAndDeletionIT {
    protected DatabaseObjectFactory factory;
    protected Connection adminConnection;

    @BeforeEach
    void beforeEach() throws SQLException {
        this.factory = getDatabaseObjectFactory();
        this.adminConnection = getAdminConnection();
    }

    protected abstract Connection getAdminConnection() throws SQLException;

    protected abstract DatabaseObjectFactory getDatabaseObjectFactory() throws SQLException;

    protected abstract Matcher<DatabaseObject> existsInDatabase();

    @Test
    void testCreateSchema() {
        assertThat(this.factory.createSchema("THE_SCHEMA"), existsInDatabase());
    }

    @Test
    // [itest->dsn~dropping-schemas~2]
    void testDropSchema() {
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
    void testCreateTable() {
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