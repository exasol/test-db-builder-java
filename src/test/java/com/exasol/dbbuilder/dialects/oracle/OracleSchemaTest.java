package com.exasol.dbbuilder.dialects.oracle;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.AbstractSchemaTest;
import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;

@ExtendWith(MockitoExtension.class)
@SuppressWarnings("java:S2187") // Tests are defined in super class
class OracleSchemaTest extends AbstractSchemaTest {
    @Mock
    private OracleImmediateDatabaseObjectWriter writerMock;

    @Override
    protected OracleSchema createSchema(final String name) {
        return new OracleSchema(this.writerMock, OracleIdentifier.of(name));
    }

    @Override
    protected DatabaseObjectWriter getWriterMock() {
        return writerMock;
    }
}
