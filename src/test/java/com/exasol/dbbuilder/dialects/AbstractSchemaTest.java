package com.exasol.dbbuilder.dialects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public abstract class AbstractSchemaTest {
    protected abstract Schema createSchema(String name);

    @Test
    void testGetType() {
        assertThat(createSchema("A").getType(), equalTo("schema"));
    }

    @Test
    void testHasParent() {
        assertThat(createSchema("A").hasParent(), equalTo(false));
    }

    @Test
    void testGetParentThrowsException() {
        assertThrows(DatabaseObjectException.class, () -> createSchema("A").getParent());
    }

    @Test
    void testGetName() {
        final Schema schema = createSchema("FOO");
        assertThat(schema.getName(), equalTo("FOO"));
    }

    @Test
    void testGetTables() {
        final Schema schema = createSchema("THE_SCHEMA");
        final Table tableA = schema.createTable("TABLE_A", "FOO", "VARCHAR(20)");
        final Table tableB = schema.createTable("TABLE_B", "FOO", "VARCHAR(20)", "BAR", "NUMBER");
        final Table tableC = schema.createTable("TABLE_C", "FOO", "VARCHAR(20)", "BAR", "NUMBER", "ZOO", "DATE");
        assertThat(schema.getTables(), contains(tableA, tableB, tableC));
    }

    @Test
    void createTableThrowsExceptionIfNumberOfColumnNamesAndTypesDoNotMatch() {
        final Schema Schema = createSchema("COLUMN_PARAMTER_MISMATCH_SCHEMA");
        assertThrows(IllegalArgumentException.class,
                () -> Schema.createTable("MISMATCH_TABLE", List.of("A"), List.of("DATE", "NUMBER")));
    }
}