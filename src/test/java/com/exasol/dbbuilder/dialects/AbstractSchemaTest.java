package com.exasol.dbbuilder.dialects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;

@SuppressWarnings("java:S5786") // this class should be public as implementation classes are in different packages
// [utest->dsn~creating-schemas~1]
public abstract class AbstractSchemaTest {
    protected abstract Schema createSchema(String name);

    protected abstract DatabaseObjectWriter getWriterMock();

    @Test
    void testConstructorDoesNotCallWriter() {
        createSchema("BAR");
        verify(getWriterMock(), never()).write(any(Schema.class));
    }

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

    @Test
    void testDrop() {
        final Schema schema = createSchema("schema");
        schema.drop();
        verify(getWriterMock()).drop(same(schema));
    }

    @Test
    void testDropMarksTablesAsDeleted() {
        final Schema schema = createSchema("schema");
        final Table table = schema.createTable("tab", "col1", "type1");
        schema.drop();
        assertThrows(DatabaseObjectDeletedException.class, table::drop);
    }

    @Test
    void testDropTwiceFails() {
        final Schema schema = createSchema("schema");
        schema.drop();
        assertThrows(DatabaseObjectDeletedException.class, schema::drop);
    }

    @Test
    void testCreateTableWithThreeColumnsFailsForDroppedSchema() {
        final Schema schema = createSchema("schema");
        schema.drop();
        assertThrows(DatabaseObjectDeletedException.class,
                () -> schema.createTable("tab", "col1", "type1", "col2", "type2", "col3", "type3"));
    }

    @Test
    void testCreateTableWithTwoColumnsFailsForDroppedSchema() {
        final Schema schema = createSchema("schema");
        schema.drop();
        assertThrows(DatabaseObjectDeletedException.class,
                () -> schema.createTable("tab", "col1", "type1", "col2", "type2"));
    }

    @Test
    void testCreateTableWithOneColumnFailsForDroppedSchema() {
        final Schema schema = createSchema("schema");
        schema.drop();
        assertThrows(DatabaseObjectDeletedException.class, () -> schema.createTable("tab", "col1", "type1"));
    }

    @Test
    void testCreateTableWithColumnListFailsForDroppedSchema() {
        final Schema schema = createSchema("schema");
        schema.drop();
        final List<String> columnNames = List.of("col1");
        final List<String> columnTypes = List.of("type1");
        assertThrows(DatabaseObjectDeletedException.class, () -> schema.createTable("tab", columnNames, columnTypes));
    }

    @Test
    void testCreateTableWithBuilderFailsForDroppedSchema() {
        final Schema schema = createSchema("schema");
        schema.drop();
        assertThrows(DatabaseObjectDeletedException.class, () -> schema.createTableBuilder("tab"));
    }
}
