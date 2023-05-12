package com.exasol.dbbuilder.dialects.oracle;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;
import com.exasol.dbbuilder.dialects.Schema;

@ExtendWith(MockitoExtension.class)
class OracleTableTest {
    @Mock
    private DatabaseObjectWriter writerMock;
    @Mock
    private Schema schemaMock;

    @Test
    void testBuilderCallsWrite() {
        final OracleTable table = OracleTable.builder(writerMock, schemaMock, OracleIdentifier.of("name")).build();
        verify(writerMock).write(same(table));
    }
}
