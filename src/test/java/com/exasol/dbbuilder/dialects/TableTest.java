package com.exasol.dbbuilder.dialects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.db.ExasolIdentifier;

@ExtendWith(MockitoExtension.class)
public class TableTest {
    @Mock
    private DatabaseObjectWriter writerMock;
    @Mock
    private Schema schemaMock;

    @Test
    void testGetType() {
        assertThat(Table.builder(this.writerMock, this.schemaMock, ExasolIdentifier.of("A")).build().getType(),
                equalTo("table"));
    }

    @Test
    void testGetName() {
        final Table table = Table.builder(this.writerMock, this.schemaMock, ExasolIdentifier.of("FOO")).build();
        assertThat(table.getName(), equalTo("FOO"));
    }

    @Test
    void testGetFullyQualifiedName() {
        Mockito.when(this.schemaMock.getFullyQualifiedName()).thenReturn("\"THE_SCHEMA\"");
        final Table table = Table.builder(this.writerMock, this.schemaMock, ExasolIdentifier.of("THE_TABLE")).build();
        assertThat(table.getFullyQualifiedName(), equalTo("\"THE_SCHEMA\".\"THE_TABLE\""));
    }

    @Test
    void testHasParent() {
        assertThat(Table.builder(this.writerMock, this.schemaMock, ExasolIdentifier.of("A")).build().hasParent(),
                equalTo(true));
    }

    @Test
    void testGetParent() {
        assertThat(Table.builder(this.writerMock, this.schemaMock, ExasolIdentifier.of("A")).build().getParent(),
                sameInstance(this.schemaMock));
    }

    @Test
    void testGetColumns() {
        final Table table = Table.builder(this.writerMock, this.schemaMock, ExasolIdentifier.of("BAR")) //
                .column("COL1", "VARCHAR(40)") //
                .column("COL2", "DATE") //
                .build();
        final List<Column> columns = table.getColumns();
        assertAll(() -> assertThat("size", columns, iterableWithSize(2)), //
                () -> assertThat(columns.get(0).getName(), equalTo("COL1")), //
                () -> assertThat(columns.get(1).getType(), equalTo("DATE")), //
                () -> assertThat(table.getColumnCount(), equalTo(2)));
    }

    @Test
    void testInsert() {
        final Table table = Table.builder(this.writerMock, this.schemaMock, ExasolIdentifier.of("TABLEWITHCONTENT")) //
                .column("NAME", "VARCHAR(40)") //
                .column("BIRTHDAY", "DATE") //
                .build() //
                .insert("Claudia", "2001-01-01") //
                .insert("Steven", "2002-02-02");
        final List<List<Object>> rows = table.getRows();
        assertThat(rows, contains(contains("Claudia", "2001-01-01"), contains("Steven", "2002-02-02")));
    }

    @Test
    void testInsertThrowsExceptionOnValueCountMismatch() {
        final Table table = Table.builder(this.writerMock, this.schemaMock, ExasolIdentifier.of("ONECOLUMNTABLE")) //
                .column("FOO", "DATE") //
                .build();
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> table.insert("1", "2"));
        assertThat(exception.getMessage(), startsWith("Column count mismatch"));
    }
}