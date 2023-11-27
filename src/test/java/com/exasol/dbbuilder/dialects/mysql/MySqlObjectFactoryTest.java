package com.exasol.dbbuilder.dialects.mysql;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.*;

@ExtendWith(MockitoExtension.class)
class MySqlObjectFactoryTest extends AbstractObjectFactoryTest {
    @Mock
    MySqlImmediateDatabaseObjectWriter writerMock;

    @Override
    protected DatabaseObjectFactory testee() {
        return new MySqlObjectFactory(writerMock);
    }

    @Override
    protected AbstractImmediateDatabaseObjectWriter getWriterMock() {
        return writerMock;
    }
}
