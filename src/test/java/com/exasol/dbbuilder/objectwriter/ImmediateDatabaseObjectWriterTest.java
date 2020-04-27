package com.exasol.dbbuilder.objectwriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.ConnectionDefinition;
import com.exasol.dbbuilder.DatabaseObjectException;

@ExtendWith(MockitoExtension.class)
class ImmediateDatabaseObjectWriterTest {
    private DatabaseObjectWriter writer;

    @BeforeEach
    void beforeEach() {
        this.writer = new ImmediateDatabaseObjectWriter(null);
    }

    @Test
    void testWriteConnectionDefinitionThrowsExceptionWhenUserIsGivenWithoutPassword(
            @Mock final ConnectionDefinition definition) {
        when(definition.hasUserName()).thenReturn(true);
        when(definition.hasPassword()).thenReturn(false);
        final DatabaseObjectException exception = assertThrows(DatabaseObjectException.class,
                () -> this.writer.write(definition));
        assertThat(exception.getMessage(), startsWith("Password missing"));
    }

    @Test
    void testWriteConnectionDefinitionThrowsExceptionWhenPasswordIsGivenWithoutUser(
            @Mock final ConnectionDefinition definition) {
        when(definition.hasUserName()).thenReturn(false);
        when(definition.hasPassword()).thenReturn(true);
        final DatabaseObjectException exception = assertThrows(DatabaseObjectException.class,
                () -> this.writer.write(definition));
        assertThat(exception.getMessage(), startsWith("User name missing"));
    }
}