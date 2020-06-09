package com.exasol.dbbuilder.dialects.mysql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.*;

@ExtendWith(MockitoExtension.class)
class MySqlSchemaTest extends AbstractSchemaTest {
    @Mock
    private MySqlImmediateDatabaseObjectWriter writerMock;

    @Override
    protected Schema createSchema(final String name) {
        return new MySqlSchema(this.writerMock, name);
    }

    @Test
    void testGetFullyQualifiedName() {
        final Schema schema = createSchema("BAR");
        assertThat(schema.getFullyQualifiedName(), equalTo("`BAR`"));
    }

    @Test
    void testCreateTableBuilder() {
        final MySqlSchema mySqlSchema = new MySqlSchema(this.writerMock, "THE_SCHEMA");
        final Table table = mySqlSchema.createTableBuilder("TABLE_D").column("A", "DATE").build();
        assertThat(table.getName(), equalTo("TABLE_D"));
    }
}