package com.exasol.dbbuilder.dialects.oracle;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.*;

@ExtendWith(MockitoExtension.class)
class OracleObjectFactoryTest extends AbstractObjectFactoryTest {
    @Mock
    OracleImmediateDatabaseObjectWriter writerMock;

    @Override
    protected DatabaseObjectFactory testee() {
        return new OracleObjectFactory(writerMock);
    }

    @Override
    protected AbstractImmediateDatabaseObjectWriter getWriterMock() {
        return writerMock;
    }
}
