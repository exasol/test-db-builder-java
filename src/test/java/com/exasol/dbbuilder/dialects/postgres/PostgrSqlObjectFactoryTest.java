package com.exasol.dbbuilder.dialects.postgres;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.*;

@ExtendWith(MockitoExtension.class)
class PostgrSqlObjectFactoryTest extends AbstractObjectFactoryTest {
    @Mock
    PostgreSqlImmediateDatabaseObjectWriter writerMock;

    @Override
    protected DatabaseObjectFactory testee() {
        return new PostgreSqlObjectFactory(writerMock);
    }

    @Override
    protected AbstractImmediateDatabaseObjectWriter getWriterMock() {
        return writerMock;
    }
}
