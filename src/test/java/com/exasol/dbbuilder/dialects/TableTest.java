package com.exasol.dbbuilder.dialects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.Table.Builder;

// [utest->dsn~creating-tables~1]
@ExtendWith(MockitoExtension.class)
class TableTest {
    @Mock
    private DatabaseObjectWriter writerMock;
    @Mock
    private Schema schemaMock;

    @Test
    void testGetType() {
        assertThat(tableBuilder("A").build().getType(), equalTo("table"));
    }

    @Test
    void testGetName() {
        final Table table = tableBuilder("FOO").build();
        assertThat(table.getName(), equalTo("FOO"));
    }

    @Test
    void testGetFullyQualifiedName() {
        Mockito.when(this.schemaMock.getFullyQualifiedName()).thenReturn("\"THE_SCHEMA\"");
        final Table table = tableBuilder("THE_TABLE").build();
        assertThat(table.getFullyQualifiedName(), equalTo("\"THE_SCHEMA\".\"THE_TABLE\""));
    }

    @Test
    void testHasParent() {
        assertThat(tableBuilder("A").build().hasParent(), equalTo(true));
    }

    @Test
    void testGetParent() {
        assertThat(tableBuilder("A").build().getParent(), sameInstance(this.schemaMock));
    }

    @Test
    void testGetColumns() {
        final Table table = tableBuilder("BAR") //
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
        final Table table = tableBuilder("TABLEWITHCONTENT") //
                .column("NAME", "VARCHAR(40)") //
                .column("BIRTHDAY", "DATE") //
                .build() //
                .insert("Claudia", "2001-01-01") //
                .insert("Steven", "2002-02-02");
        @SuppressWarnings("unchecked")
        final ArgumentCaptor<Stream<List<Object>>> streamCapture = ArgumentCaptor.forClass(Stream.class);
        verify(this.writerMock, times(2)).write(eq(table), streamCapture.capture());
        assertThat(streamCapture.getAllValues().stream().flatMap(each -> each).collect(Collectors.toList()),
                containsInAnyOrder(List.of("Claudia", "2001-01-01"), List.of("Steven", "2002-02-02")));
    }

    @Test
    void testBulkInsert() {
        final Stream<List<Object>> rows = Stream.of(List.of("Claudia", "2001-01-01"), List.of("Steven", "2002-02-02"));
        final Table table = tableBuilder("TABLEWITHCONTENT2") //
                .column("NAME", "VARCHAR(40)") //
                .column("BIRTHDAY", "DATE") //
                .build() //
                .bulkInsert(rows);
        @SuppressWarnings("unchecked")
        final ArgumentCaptor<Stream<List<Object>>> streamCapture = ArgumentCaptor.forClass(Stream.class);
        verify(this.writerMock, times(1)).write(eq(table), streamCapture.capture());
        assertThat(streamCapture.getAllValues().stream().flatMap(each -> each).collect(Collectors.toList()),
                containsInAnyOrder(List.of("Claudia", "2001-01-01"), List.of("Steven", "2002-02-02")));
    }

    @Test
    void testInsertThrowsExceptionOnValueCountMismatch() {
        final Table table = tableBuilder("ONECOLUMNTABLE") //
                .column("FOO", "DATE") //
                .build();
        table.insert("1", "2");
        @SuppressWarnings("unchecked")
        final ArgumentCaptor<Stream<List<Object>>> streamCapture = ArgumentCaptor.forClass(Stream.class);
        verify(this.writerMock).write(eq(table), streamCapture.capture());
        final Stream<List<Object>> stream = streamCapture.getValue();
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> stream.forEach(each -> { // just consume the items
                }));
        assertThat(exception.getMessage(), startsWith("E-TDBJ-3: Column count mismatch."));
    }

    @Test
    void testDrop() {
        final Table table = tableBuilder("tab").build();
        table.drop();
        verify(writerMock).drop(same(table));
    }

    @Test
    void testDropTwiceFails() {
        final Table table = tableBuilder("tab").build();
        table.drop();
        assertThrows(DatabaseObjectDeletedException.class, table::drop);
    }

    @Test
    void testTruncateDeletedTableFails() {
        final Table table = tableBuilder("tab").build();
        table.drop();
        assertThrows(DatabaseObjectDeletedException.class, table::truncate);
    }

    @Test
    void testInsertIntoDeletedTableFails() {
        final Table table = tableBuilder("tab").build();
        table.drop();
        assertThrows(DatabaseObjectDeletedException.class, () -> table.insert("val1"));
    }

    @Test
    void testBulkInsertIntoDeletedTableFails() {
        final Table table = tableBuilder("tab").build();
        table.drop();
        final Stream<List<Object>> rows = Stream.empty();
        assertThrows(DatabaseObjectDeletedException.class, () -> table.bulkInsert(rows));
    }

    private Builder tableBuilder(final String tableName) {
        return Table.builder(this.writerMock, this.schemaMock, DummyIdentifier.of(tableName));
    }

    static class DummyIdentifier implements Identifier {
        private final String key;

        private DummyIdentifier(final String key) {
            this.key = key;
        }

        static Identifier of(final String key) {
            return new DummyIdentifier(key);
        }

        @Override
        public String toString() {
            return this.key;
        }

        @Override
        public String quote() {
            return "\"" + this.key + "\"";
        }
    }
}