package com.exasol.dbbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

@ExtendWith(MockitoExtension.class)
class SchemaTest {
    @Mock
    DatabaseObjectWriter writerMock;

    @Test
    void testGetType() {
        assertThat(new Schema(this.writerMock, "A").getType(), equalTo("schema"));
    }

    @Test
    void testGetName() {
        final Schema schema = new Schema(this.writerMock, "FOO");
        assertThat(schema.getName(), equalTo("FOO"));
    }

    @Test
    void testGetFullyQualifiedName() {
        final Schema schema = new Schema(this.writerMock, "BAR");
        assertThat(schema.getFullyQualifiedName(), equalTo("BAR"));
    }

    @Test
    void testGetTables() {
        final Schema schema = new Schema(this.writerMock, "THESCHEMA");
        final Table tableA = schema.createTable("TABLE_A", "FOO", "VARCHAR(20)", "BAR", "NUMBER");
        final Table tableB = schema.createTable("TABLE_B", "FOO", "VARCHAR(20)", "BAR", "NUMBER");
        assertThat(schema.getTables(), contains(tableA, tableB));
    }
}