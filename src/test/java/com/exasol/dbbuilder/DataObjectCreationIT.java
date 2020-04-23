package com.exasol.dbbuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.JdbcDatabaseContainer.NoDriverFoundException;
import org.testcontainers.containers.output.Slf4jLogConsumer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.exasol.containers.ExasolContainer;
import com.exasol.containers.ExasolContainerConstants;

@Tag("integration")
@Testcontainers
class DataObjectCreationIT {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataObjectCreationIT.class);
    @Container
    private static final ExasolContainer<? extends ExasolContainer<?>> container = new ExasolContainer<>(
            ExasolContainerConstants.EXASOL_DOCKER_IMAGE_REFERENCE) //
                    .withLogConsumer(new Slf4jLogConsumer(LOGGER));
    private DatabaseObjectFactory factory;
    private Connection adminConnection;

    @BeforeEach
    void beforeEach() throws NoDriverFoundException, SQLException {
        this.adminConnection = container.createConnection("");
        this.factory = new DatabaseObjectFactory(container.createConnection(""));
    }

    @Test
    void testCreateAdapterScript() {
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_ADAPTER_SCRIPT");
        assertObjectExistsInDatabase(
                schema.createAdapterScript("THE_ADAPTER_SCRIPT", AdapterScript.Language.JAVA, "the content"));
    }

    private void assertObjectExistsInDatabase(final DatabaseObject object) {
        try (final PreparedStatement objectExistenceStatement = this.adminConnection.prepareStatement(
                "SELECT 1 FROM SYS.EXA_ALL_" + getTableSysName(object) + "S WHERE " + getSysName(object) + "_NAME=?")) {
            objectExistenceStatement.setString(1, object.getName());
            final ResultSet resultSet = objectExistenceStatement.executeQuery();
            if (!resultSet.next()) {
                throw new AssertionError("Expected " + object.getType() + " " + object.getFullyQualifiedName()
                        + " to exist in database, but could not find it.");
            }
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
    void testCreateTable() {
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_TABLE");
        assertObjectExistsInDatabase(schema.createTable("THE_TABLE", "COL1", "DATE", "COL2", "NUMBER"));
    }

    @Test
    void testCreateUser() {
        assertObjectExistsInDatabase(this.factory.createUser("THE_USER"));
    }

    @Test
    void testCreateVirtualSchema() {
        final ConnectionDefinition connectionDefinition = this.factory.createConnectionDefinition("THE_CONNECTION",
                "destination");
        final Schema schema = this.factory.createSchema("PARENT_SCHEMA_FOR_VIRTUAL_SCHEMA");
        final AdapterScript adapterScript = schema.createAdapterScript("THE_ADAPTER_SCRIPT", AdapterScript.Language.R,
                "some content");
        assertObjectExistsInDatabase(schema.createVirtualSchemaBuilder("THE_VIRTUAL_SCHEMA").dialectName("Exasol")
                .adapterScript(adapterScript).connectionDefinition(connectionDefinition).build());
    }
}