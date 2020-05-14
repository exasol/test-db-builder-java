package com.exasol.dbbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer.NoDriverFoundException;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.exasol.containers.ExasolContainer;

@Testcontainers
class AttachToExistingObjectIT {
    @Container
    private static ExasolContainer<? extends ExasolContainer<?>> container = new ExasolContainer<>();
    private static DatabaseObjectFactory factory;

    @BeforeAll
    static void beforeAll() throws NoDriverFoundException, SQLException {
        factory = new ExasolObjectFactory(container.createConnection(""));
    }

    // [itest->dsn~controlling-existing-scripts~1]
    @Test
    void testAttachToScript() {
        final String scriptName = "THE_SCRIPT";
        final Schema schema = factory.createSchema("SCHEMA_FOR_ATTACHING_TO_EXISTING_SCRIPT");
        schema.createScript(scriptName, "exit({rows_affected=1337})");
        final Script existingScriptHandle = schema.getScript(scriptName);
        final int result = existingScriptHandle.execute();
        assertThat(result, equalTo(1337));
    }
}