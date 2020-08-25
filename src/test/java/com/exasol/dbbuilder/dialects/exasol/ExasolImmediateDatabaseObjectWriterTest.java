package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.AbstractImmediateDatabaseObjectWriterTest;
import com.exasol.dbbuilder.dialects.DatabaseObjectException;
import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;

@ExtendWith(MockitoExtension.class)
class ExasolImmediateDatabaseObjectWriterTest extends AbstractImmediateDatabaseObjectWriterTest {

    @Override
    protected DatabaseObjectWriter getDatabaseObjectWriter() {
        return new ExasolImmediateDatabaseObjectWriter(this.connectionMock);
    }

    @Test
    void testWriteConnectionDefinitionThrowsExceptionWhenPasswordIsGivenWithoutUser(
            @Mock final ConnectionDefinition definition) {
        final ExasolImmediateDatabaseObjectWriter writer = new ExasolImmediateDatabaseObjectWriter(this.connectionMock);
        when(definition.hasUserName()).thenReturn(false);
        when(definition.hasPassword()).thenReturn(true);
        final DatabaseObjectException exception = assertThrows(DatabaseObjectException.class,
                () -> writer.write(definition));
        assertThat(exception.getMessage(), startsWith("User name missing"));
    }
}