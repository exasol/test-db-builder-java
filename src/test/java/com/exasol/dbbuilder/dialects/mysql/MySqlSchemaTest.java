package com.exasol.dbbuilder.dialects.mysql;

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
class MySqlSchemaTest {
    private final QuoteApplier quoteApplier = new MySqlQuoteApplier();
    @Mock
    private MySqlImmediateDatabaseObjectWriter writerMock;

    @Test
    void testGetType() {
        assertThat(new MySqlSchema(this.writerMock, this.quoteApplier, "A").getType(), equalTo("schema"));
    }

    @Test
    void testHasParent() {
        assertThat(new MySqlSchema(this.writerMock, this.quoteApplier, "A").hasParent(), equalTo(false));
    }

    @Test
    void testGetParentThrowsException() {
        assertThrows(DatabaseObjectException.class,
                () -> new MySqlSchema(this.writerMock, this.quoteApplier, "A").getParent());
    }

    @Test
    void testGetName() {
        final MySqlSchema mySqlSchema = new MySqlSchema(this.writerMock, this.quoteApplier, "FOO");
        assertThat(mySqlSchema.getName(), equalTo("FOO"));
    }

    @Test
    void testGetFullyQualifiedName() {
        final MySqlSchema mySqlSchema = new MySqlSchema(this.writerMock, this.quoteApplier, "BAR");
        assertThat(mySqlSchema.getFullyQualifiedName(), equalTo("`BAR`"));
    }

    @Test
    void testGetTables() {
        final MySqlSchema mySqlSchema = new MySqlSchema(this.writerMock, this.quoteApplier, "THE_SCHEMA");
        final Table tableA = mySqlSchema.createTable("TABLE_A", "FOO", "VARCHAR(20)");
        final Table tableB = mySqlSchema.createTable("TABLE_B", "FOO", "VARCHAR(20)", "BAR", "NUMBER");
        final Table tableC = mySqlSchema.createTable("TABLE_C", "FOO", "VARCHAR(20)", "BAR", "NUMBER", "ZOO", "DATE");
        assertThat(mySqlSchema.getTables(), contains(tableA, tableB, tableC));
    }

    @Test
    void testCreateTableBuilder() {
        final MySqlSchema mySqlSchema = new MySqlSchema(this.writerMock, this.quoteApplier, "THE_SCHEMA");
        final Table table = mySqlSchema.createTableBuilder("TABLE_D").column("A", "DATE").build();
        assertThat(table.getName(), equalTo("TABLE_D"));
    }

    @Test
    void createTableThrowsExceptionIfNumberOfColumnNamesAndTypesDoNotMatch() {
        final MySqlSchema mySqlSchema = new MySqlSchema(this.writerMock, this.quoteApplier,
                "COLUMN_PARAMTER_MISMATCH_SCHEMA");
        assertThrows(IllegalArgumentException.class,
                () -> mySqlSchema.createTable("MISMATCH_TABLE", List.of("A"), List.of("DATE", "NUMBER")));
    }
}