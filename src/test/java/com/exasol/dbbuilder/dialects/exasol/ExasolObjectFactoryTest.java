package com.exasol.dbbuilder.dialects.exasol;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.AbstractImmediateDatabaseObjectWriter;
import com.exasol.dbbuilder.dialects.AbstractObjectFactoryTest;

@ExtendWith(MockitoExtension.class)
class ExasolObjectFactoryTest extends AbstractObjectFactoryTest {

    @Mock
    ExasolImmediateDatabaseObjectWriter writerMock;

    @Test
    void createConnectionDefinitionWritesObject() {
        final ConnectionDefinition connection = testee().createConnectionDefinition("name", "to");
        verify(writerMock).write(same(connection));
    }

    @Test
    void createConnectionDefinitionWithCredentialsWritesObject() {
        final ConnectionDefinition connection = testee().createConnectionDefinition("name", "to", "user", "password");
        verify(writerMock).write(same(connection));
    }

    @Override
    protected ExasolObjectFactory testee() {
        return new ExasolObjectFactory(writerMock);
    }

    @Override
    protected AbstractImmediateDatabaseObjectWriter getWriterMock() {
        return writerMock;
    }
}
