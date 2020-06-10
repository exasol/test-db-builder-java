package com.exasol.dbbuilder.dialects;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.*;
import org.testcontainers.junit.jupiter.Testcontainers;

@Tag("integration")
@Testcontainers
@SuppressWarnings("java:S5786") // this class should be public as implementation classes are in different packages
public abstract class AbstractDatabaseObjectCreationIT {
    protected DatabaseObjectFactory factory;
    protected Connection adminConnection;

    @BeforeEach
    void beforeEach() throws SQLException {
        this.factory = getDatabaseObjectFactory();
        this.adminConnection = getAdminConnection();
    }

    protected abstract Connection getAdminConnection() throws SQLException;

    protected abstract DatabaseObjectFactory getDatabaseObjectFactory() throws SQLException;

    protected abstract void assertSchemaExistsInDatabase(final Schema schema);

    protected abstract void assertTableExistsInDatabase(final Table table);

    protected abstract void assertUserExistsInDatabase(final User user);

    @Test
    void testCreateSchema() {
        assertSchemaExistsInDatabase(this.factory.createSchema("THE_SCHEMA"));
    }

    @Test
    void testCreateTable() {
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_TABLE");
        assertTableExistsInDatabase(schema.createTable("THE_TABLE", "COL1", "DATE", "COL2", "INT"));
    }

    @Test
    // [itest->dsn~creating-database-users~1]
    void testCreateUser() {
        assertUserExistsInDatabase(this.factory.createUser("THE_USER"));
    }
}