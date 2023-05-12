package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.*;
import com.exasol.dbbuilder.dialects.exasol.AdapterScript.Language;

@ExtendWith(MockitoExtension.class)
class ExasolSchemaTest extends AbstractSchemaTest {
    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;

    @Override
    protected Schema createSchema(final String name) {
        return new ExasolSchema(this.writerMock, ExasolIdentifier.of(name));
    }

    @Override
    protected DatabaseObjectWriter getWriterMock() {
        return writerMock;
    }

    @Test
    void testGetFullyQualifiedName() {
        final Schema schema = createSchema("BAR");
        assertThat(schema.getFullyQualifiedName(), equalTo("\"BAR\""));
    }

    @Test
    void testCreateTableBuilder() {
        final ExasolSchema exasolSchema = testee();
        final Table table = exasolSchema.createTableBuilder("TABLE_D").column("A", "DATE").build();
        assertThat(table.getName(), equalTo("TABLE_D"));
    }

    @Test
    void testCreateAdapterScriptBuilder() {
        final ExasolSchema exasolSchema = testee();
        final AdapterScript adapterScript = exasolSchema.createAdapterScriptBuilder("TEST_SCRIPT")
                .language(AdapterScript.Language.JAVA).content("test").build();
        assertThat(adapterScript.getParent(), equalTo(exasolSchema));
    }

    @Test
    void testCreateAdapterScriptFailsForDroppedSchema() {
        final ExasolSchema schema = testee();
        schema.drop();
        assertThrows(DatabaseObjectDeletedException.class,
                () -> schema.createAdapterScript("script", Language.JAVA, "content"));
    }

    @Test
    void testCreateAdapterScriptBuilderFailsForDroppedSchema() {
        final ExasolSchema schema = testee();
        schema.drop();
        assertThrows(DatabaseObjectDeletedException.class, () -> schema.createAdapterScriptBuilder("script"));
    }

    @Test
    void testCreateScriptBuilderFailsForDroppedSchema() {
        final ExasolSchema schema = testee();
        schema.drop();
        assertThrows(DatabaseObjectDeletedException.class, () -> schema.createScriptBuilder("script"));
    }

    @Test
    void testCreateUdfBuilderFailsForDroppedSchema() {
        final ExasolSchema schema = testee();
        schema.drop();
        assertThrows(DatabaseObjectDeletedException.class, () -> schema.createUdfBuilder("script"));
    }

    private ExasolSchema testee() {
        return new ExasolSchema(this.writerMock, ExasolIdentifier.of("THE_SCHEMA"));
    }
}