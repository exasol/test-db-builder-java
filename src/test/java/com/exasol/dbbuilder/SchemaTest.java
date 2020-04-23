package com.exasol.dbbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

@ExtendWith(MockitoExtension.class)
class SchemaTest {
    @Mock
    private DatabaseObjectWriter writerMock;

    @Test
    void testGetType() {
        assertThat(new Schema(this.writerMock, "A").getType(), equalTo("schema"));
    }

    @Test
    void testHasParent() {
        assertThat(new Schema(this.writerMock, "A").hasParent(), equalTo(false));
    }

    @Test
    void testGetParentThrowsException() {
        assertThrows(DatabaseObjectException.class, () -> new Schema(this.writerMock, "A").getParent());
    }

    @Test
    void testGetName() {
        final Schema schema = new Schema(this.writerMock, "FOO");
        assertThat(schema.getName(), equalTo("FOO"));
    }

    @Test
    void testGetFullyQualifiedName() {
        final Schema schema = new Schema(this.writerMock, "BAR");
        assertThat(schema.getFullyQualifiedName(), equalTo("\"BAR\""));
    }

    @Test
    void testGetTables() {
        final Schema schema = new Schema(this.writerMock, "THE_SCHEMA");
        final Table tableA = schema.createTable("TABLE_A", "FOO", "VARCHAR(20)");
        final Table tableB = schema.createTable("TABLE_B", "FOO", "VARCHAR(20)", "BAR", "NUMBER");
        final Table tableC = schema.createTable("TABLE_C", "FOO", "VARCHAR(20)", "BAR", "NUMBER", "ZOO", "DATE");
        assertThat(schema.getTables(), contains(tableA, tableB, tableC));
    }

    @Test
    void testCreateTableBuilder() {
        final Schema schema = new Schema(this.writerMock, "THE_SCHEMA");
        final Table table = schema.createTableBuilder("TABLE_D").column("A", "DATE").build();
        assertThat(table.getName(), equalTo("TABLE_D"));
    }
}