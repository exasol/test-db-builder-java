package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.DatabaseObject;
import com.exasol.dbbuilder.dialects.DatabaseObjectException;

@ExtendWith(MockitoExtension.class)
class ExasolImmediateDatabaseObjectWriterTest {
    private ExasolImmediateDatabaseObjectWriter writer;
    @Mock
    private Connection connectionMock;

    @BeforeEach
    void beforeEach() {
        this.writer = new ExasolImmediateDatabaseObjectWriter(this.connectionMock);
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

    @Test
    void testWriteToObjectThrowsException(@Mock final DatabaseObject objectMock)
            throws NoSuchMethodException, SecurityException, SQLException {
        when(this.connectionMock.prepareStatement(anyString())).thenThrow(new SQLException("Mock Exception"));
        final Method method = this.writer.getClass().getSuperclass().getDeclaredMethod("writeToObject",
                DatabaseObject.class, String.class, Object[].class);
        method.setAccessible(true);
        final InvocationTargetException exception = assertThrows(InvocationTargetException.class,
                () -> method.invoke(this.writer, objectMock, "INSERT INTO NONEXISTENT_TABLE VALUES (?)",
                        new Object[] { "IRRELEVANT" }));
        assertThat(exception.getCause(), instanceOf(DatabaseObjectException.class));
    }
}