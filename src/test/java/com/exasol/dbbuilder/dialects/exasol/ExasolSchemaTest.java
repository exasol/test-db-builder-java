package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.AbstractSchemaTest;
import com.exasol.dbbuilder.dialects.Schema;
import com.exasol.dbbuilder.dialects.Table;

@ExtendWith(MockitoExtension.class)
class ExasolSchemaTest extends AbstractSchemaTest {
    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;

    @Override
    protected Schema createSchema(final String name) {
        return new ExasolSchema(this.writerMock, name);
    }

    @Test
    void testGetFullyQualifiedName() {
        final Schema schema = createSchema("BAR");
        assertThat(schema.getFullyQualifiedName(), equalTo("\"BAR\""));
    }

    @Test
    void testCreateTableBuilder() {
        final ExasolSchema exasolSchema = new ExasolSchema(this.writerMock, "THE_SCHEMA");
        final Table table = exasolSchema.createTableBuilder("TABLE_D").column("A", "DATE").build();
        assertThat(table.getName(), equalTo("TABLE_D"));
    }
}