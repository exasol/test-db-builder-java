package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.*;

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

    private ExasolSchema testee() {
        return new ExasolSchema(this.writerMock, ExasolIdentifier.of("THE_SCHEMA"));
    }
}