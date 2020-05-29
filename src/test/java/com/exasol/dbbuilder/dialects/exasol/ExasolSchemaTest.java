package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.DatabaseObjectException;
import com.exasol.dbbuilder.dialects.Table;

@ExtendWith(MockitoExtension.class)
class ExasolSchemaTest {
    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;

    @Test
    void testGetType() {
        assertThat(new ExasolSchema(this.writerMock, "A").getType(), equalTo("schema"));
    }

    @Test
    void testHasParent() {
        assertThat(new ExasolSchema(this.writerMock, "A").hasParent(), equalTo(false));
    }

    @Test
    void testGetParentThrowsException() {
        assertThrows(DatabaseObjectException.class, () -> new ExasolSchema(this.writerMock, "A").getParent());
    }

    @Test
    void testGetName() {
        final ExasolSchema exasolSchema = new ExasolSchema(this.writerMock, "FOO");
        assertThat(exasolSchema.getName(), equalTo("FOO"));
    }

    @Test
    void testGetFullyQualifiedName() {
        final ExasolSchema exasolSchema = new ExasolSchema(this.writerMock, "BAR");
        assertThat(exasolSchema.getFullyQualifiedName(), equalTo("\"BAR\""));
    }

    @Test
    void testGetTables() {
        final ExasolSchema exasolSchema = new ExasolSchema(this.writerMock, "THE_SCHEMA");
        final Table tableA = exasolSchema.createTable("TABLE_A", "FOO", "VARCHAR(20)");
        final Table tableB = exasolSchema.createTable("TABLE_B", "FOO", "VARCHAR(20)", "BAR", "NUMBER");
        final Table tableC = exasolSchema.createTable("TABLE_C", "FOO", "VARCHAR(20)", "BAR", "NUMBER", "ZOO", "DATE");
        assertThat(exasolSchema.getTables(), contains(tableA, tableB, tableC));
    }

    @Test
    void testCreateTableBuilder() {
        final ExasolSchema exasolSchema = new ExasolSchema(this.writerMock, "THE_SCHEMA");
        final Table table = exasolSchema.createTableBuilder("TABLE_D").column("A", "DATE").build();
        assertThat(table.getName(), equalTo("TABLE_D"));
    }

    @Test
    void createTableThrowsExceptionIfNumberOfColumnNamesAndTypesDoNotMatch() {
        final ExasolSchema exasolSchema = new ExasolSchema(this.writerMock, "COLUMN_PARAMTER_MISMATCH_SCHEMA");
        assertThrows(IllegalArgumentException.class,
                () -> exasolSchema.createTable("MISMATCH_TABLE", List.of("A"), List.of("DATE", "NUMBER")));
    }
}