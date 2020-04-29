package com.exasol.dbbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DatabaseObjectExceptionTest {
    @Test
    void testCreateWithObjectAndMessage(@Mock final DatabaseObject objectMock) {
        final String expectedObjectName = "\"OBJ_A\"";
        final String expectedObjectType = "TYPE_A";
        when(objectMock.getFullyQualifiedName()).thenReturn(expectedObjectName);
        when(objectMock.getType()).thenReturn(expectedObjectType);
        final String expectedMessage = "message A";
        final DatabaseObjectException exception = new DatabaseObjectException(objectMock, expectedMessage);
        assertAll(() -> assertThat(exception.getMessage(), equalTo(expectedMessage)),
                () -> assertThat(exception.getFullyQualifiedObjectName(), equalTo(expectedObjectName)),
                () -> assertThat(exception.getObjectType(), equalTo(expectedObjectType)));
    }

    @Test
    public void testCreateWithObjectAndCause(@Mock final DatabaseObject objectMock, @Mock final Exception causeMock) {
        final String expectedObjectName = "\"OBJ_B\"";
        final String expectedObjectType = "TYPE_B";
        when(objectMock.getFullyQualifiedName()).thenReturn(expectedObjectName);
        when(objectMock.getType()).thenReturn(expectedObjectType);
        final DatabaseObjectException exception = new DatabaseObjectException(objectMock, causeMock);
        assertAll(() -> assertThat(exception.getCause(), sameInstance(causeMock)),
                () -> assertThat(exception.getFullyQualifiedObjectName(), equalTo(expectedObjectName)),
                () -> assertThat(exception.getObjectType(), equalTo(expectedObjectType)));
    }

    @Test
    public void testDatabaseObjectExceptionStringDatabaseObjectException(@Mock final DatabaseObject objectMock,
            @Mock final Exception causeMock) throws Exception {
        final String expectedObjectName = "\"OBJ_C\"";
        final String expectedObjectType = "TYPE_C";
        when(objectMock.getFullyQualifiedName()).thenReturn(expectedObjectName);
        when(objectMock.getType()).thenReturn(expectedObjectType);
        final String expectedMessage = "Message C.";
        final DatabaseObjectException exception = new DatabaseObjectException(objectMock, expectedMessage, causeMock);
        assertAll(() -> assertThat(exception.getMessage(), startsWith(expectedMessage + " In the context")),
                () -> assertThat(exception.getCause(), sameInstance(causeMock)),
                () -> assertThat(exception.getFullyQualifiedObjectName(), equalTo(expectedObjectName)),
                () -> assertThat(exception.getObjectType(), equalTo(expectedObjectType)));
    }

}
