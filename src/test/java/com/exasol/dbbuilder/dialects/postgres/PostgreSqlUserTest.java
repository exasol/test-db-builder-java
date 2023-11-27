package com.exasol.dbbuilder.dialects.postgres;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.*;

@ExtendWith(MockitoExtension.class)
class PostgreSqlUserTest extends AbstractUserTest {
    @Mock
    private PostgreSqlImmediateDatabaseObjectWriter writerMock;

    @Override
    protected User createUser(final String name, final String password) {
        return new PostgreSqlUser(this.writerMock, PostgreSqlIdentifier.of(name), password);
    }

    @Override
    protected DatabaseObjectWriter getWriterMock() {
        return writerMock;
    }
}
