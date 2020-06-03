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

import com.exasol.dbbuilder.dialects.*;

@ExtendWith(MockitoExtension.class)
class ExasolSchemaTest {
    private final QuoteApplier quoteApplier = new ExasolQuoteApplier();
    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;

    @Test
    void testGetType() {
        assertThat(new ExasolSchema(this.writerMock, this.quoteApplier, "A").getType(), equalTo("schema"));
    }

    @Test
    void testHasParent() {
        assertThat(new ExasolSchema(this.writerMock, this.quoteApplier, "A").hasParent(), equalTo(false));
    }

    @Test
    void testGetParentThrowsException() {
        assertThrows(DatabaseObjectException.class,
                () -> new ExasolSchema(this.writerMock, this.quoteApplier, "A").getParent());
    }

    @Test
    void testGetName() {
        final ExasolSchema exasolSchema = new ExasolSchema(this.writerMock, this.quoteApplier, "FOO");
        assertThat(exasolSchema.getName(), equalTo("FOO"));
    }

    @Test
    void testGetFullyQualifiedName() {
        final ExasolSchema exasolSchema = new ExasolSchema(this.writerMock, this.quoteApplier, "BAR");
        assertThat(exasolSchema.getFullyQualifiedName(), equalTo("\"BAR\""));
    }

    @Test
    void testGetTables() {
        final ExasolSchema exasolSchema = new ExasolSchema(this.writerMock, this.quoteApplier, "THE_SCHEMA");
        final Table tableA = exasolSchema.createTable("TABLE_A", "FOO", "VARCHAR(20)");
        final Table tableB = exasolSchema.createTable("TABLE_B", "FOO", "VARCHAR(20)", "BAR", "NUMBER");
        final Table tableC = exasolSchema.createTable("TABLE_C", "FOO", "VARCHAR(20)", "BAR", "NUMBER", "ZOO", "DATE");
        assertThat(exasolSchema.getTables(), contains(tableA, tableB, tableC));
    }

    @Test
    void testCreateTableBuilder() {
        final ExasolSchema exasolSchema = new ExasolSchema(this.writerMock, this.quoteApplier, "THE_SCHEMA");
        final Table table = exasolSchema.createTableBuilder("TABLE_D").column("A", "DATE").build();
        assertThat(table.getName(), equalTo("TABLE_D"));
    }

    @Test
    void createTableThrowsExceptionIfNumberOfColumnNamesAndTypesDoNotMatch() {
        final ExasolSchema exasolSchema = new ExasolSchema(this.writerMock, this.quoteApplier,
                "COLUMN_PARAMTER_MISMATCH_SCHEMA");
        assertThrows(IllegalArgumentException.class,
                () -> exasolSchema.createTable("MISMATCH_TABLE", List.of("A"), List.of("DATE", "NUMBER")));
    }
}