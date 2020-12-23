package com.exasol.dbbuilder.dialects.postgres;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.AbstractSchemaTest;
import com.exasol.dbbuilder.dialects.Schema;

@ExtendWith(MockitoExtension.class)
class PostgreSqlSchemaTest extends AbstractSchemaTest {
    @Mock
    private PostgreSqlImmediateDatabaseObjectWriter writerMock;

    @Override
    protected Schema createSchema(final String name) {
        return new PostgreSqlSchema(this.writerMock, name);
    }
}
