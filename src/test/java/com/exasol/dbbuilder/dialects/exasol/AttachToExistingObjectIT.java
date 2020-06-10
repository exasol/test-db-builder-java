package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.testcontainers.containers.JdbcDatabaseContainer.NoDriverFoundException;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.exasol.containers.ExasolContainer;
import com.exasol.dbbuilder.dialects.DatabaseObjectException;

@Testcontainers
class AttachToExistingObjectIT {
    @Container
    private static final ExasolContainer<? extends ExasolContainer<?>> container = new ExasolContainer<>();
    private static ExasolObjectFactory factory;

    @BeforeAll
    static void beforeAll() throws NoDriverFoundException, SQLException {
        factory = new ExasolObjectFactory(container.createConnection(""));
    }

    // [itest->dsn~controlling-existing-scripts~1]
    @Test
    void testAttachToScript() {
        final String scriptName = "THE_SCRIPT";
        final ExasolSchema exasolSchema = factory.createSchema("SCHEMA_FOR_ATTACHING_TO_EXISTING_SCRIPT");
        exasolSchema.createScript(scriptName, "exit({rows_affected=1337})");
        final Script existingScriptHandle = exasolSchema.getScript(scriptName);
        final int result = existingScriptHandle.execute();
        assertThat(result, equalTo(1337));
    }

    // [itest->dsn~creating-objects-through-sql-files~1]
    @Test
    void testAttachToScriptFromSqlFile(@TempDir final Path tempDir) throws IOException {
        final ExasolSchema exasolSchema = factory.createSchema("SCHEMA_FOR_ATTACHING_TO_SCRIPT_FROM_SQL_FILE");
        final String scriptName = "THE_SCRIPT";
        final Path scriptFile = tempDir.resolve("script.sql");
        Files.writeString(scriptFile, "CREATE SCRIPT " + exasolSchema.getName() + "." + scriptName + " AS\n" //
                + "exit({rows_affected=314})\n" //
                + "/");
        factory.executeSqlFile(scriptFile);
        final Script existingScriptHandle = exasolSchema.getScript(scriptName);
        final int result = existingScriptHandle.execute();
        assertThat(result, equalTo(314));
    }

    // [itest->dsn~creating-objects-through-sql-files~1]
    @Test
    @SuppressWarnings("java:S5778") // creating lists is not an Exception throwing method
    void testAttachToScriptThrowsExceptionOnNonExistingFile() {
        assertThrows(DatabaseObjectException.class, () -> factory.executeSqlFile(Path.of("non/existent/file.sql")));
    }
}