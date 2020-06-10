package com.exasol.dbbuilder.dialects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

@SuppressWarnings("java:S5786") // this class should be public as implementation classes are in different packages
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
        final Schema schema = createSchema("A");
        assertThrows(DatabaseObjectException.class, schema::getParent);
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
    @SuppressWarnings("java:S5778") // creating lists is not an Exception throwing method
    void createTableThrowsExceptionIfNumberOfColumnNamesAndTypesDoNotMatch() {
        final Schema Schema = createSchema("COLUMN_PARAMETER_MISMATCH_SCHEMA");
        assertThrows(IllegalArgumentException.class,
                () -> Schema.createTable("MISMATCH_TABLE", List.of("A"), List.of("DATE", "NUMBER")));
    }
}