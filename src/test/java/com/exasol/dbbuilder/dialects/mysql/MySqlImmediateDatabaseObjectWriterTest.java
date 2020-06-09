package com.exasol.dbbuilder.dialects.mysql;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.AbstractImmediateDatabaseObjectWriterTest;
import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;

@ExtendWith(MockitoExtension.class)
class MySqlImmediateDatabaseObjectWriterTest extends AbstractImmediateDatabaseObjectWriterTest {
    @Override
    protected DatabaseObjectWriter getDatabaseObjectWriter() {
        return new MySqlImmediateDatabaseObjectWriter(this.connectionMock);
    }
}