package com.exasol.dbbuilder.dialects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
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

@SuppressWarnings("java:S5786") // this class should be public as implementation classes are in different packages
@ExtendWith(MockitoExtension.class)
public abstract class AbstractImmediateDatabaseObjectWriterTest {
    @Mock
    protected Connection connectionMock;
    private DatabaseObjectWriter writer;

    @BeforeEach
    void beforeEach() {
        this.writer = getDatabaseObjectWriter();
    }

    protected abstract DatabaseObjectWriter getDatabaseObjectWriter();

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