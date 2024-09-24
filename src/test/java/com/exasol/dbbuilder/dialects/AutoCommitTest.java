package com.exasol.dbbuilder.dialects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.*;

import java.sql.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AutoCommitTest {
    @Mock
    Connection connectionMock;

    @Test
    void autoCommitAlreadyDeactivated() throws SQLException {
        when(connectionMock.getAutoCommit()).thenReturn(false);
        AutoCommit.tryDeactivate(connectionMock).close();
        verify(connectionMock, never()).setAutoCommit(anyBoolean());
        verifyNoMoreInteractions(connectionMock);
    }

    @Test
    void autoCommitEnabledAndSupported() throws SQLException {
        when(connectionMock.getAutoCommit()).thenReturn(true);
        AutoCommit.tryDeactivate(connectionMock).close();
        final InOrder inOrder = inOrder(connectionMock);
        inOrder.verify(connectionMock).setAutoCommit(false);
        inOrder.verify(connectionMock).setAutoCommit(true);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    void autoCommitEnabledAndNotSupported() throws SQLException {
        when(connectionMock.getAutoCommit()).thenReturn(true);
        doThrow(new SQLFeatureNotSupportedException("unsupported")).when(connectionMock).setAutoCommit(false);
        AutoCommit.tryDeactivate(connectionMock).close();
        verify(connectionMock).setAutoCommit(false);
        verifyNoMoreInteractions(connectionMock);
    }

    @Test
    void settingAutoCommitFailsWithOtherException() throws SQLException {
        when(connectionMock.getAutoCommit()).thenReturn(true);
        doThrow(new SQLException("mock")).when(connectionMock).setAutoCommit(false);
        final DatabaseObjectException exception = assertThrows(DatabaseObjectException.class,
                () -> AutoCommit.tryDeactivate(connectionMock));
        assertThat(exception.getMessage(), equalTo("E-TDBJ-36: Failed to check AutoCommit state"));
        assertThat(exception.getCause().getMessage(), equalTo("mock"));
    }

    @Test
    void reactivatingAutoCommitFails() throws SQLException {
        when(connectionMock.getAutoCommit()).thenReturn(true);
        final AutoCommit autoCommit = AutoCommit.tryDeactivate(connectionMock);
        doThrow(new SQLException("mock")).when(connectionMock).setAutoCommit(true);
        final DatabaseObjectException exception = assertThrows(DatabaseObjectException.class, autoCommit::close);
        assertThat(exception.getMessage(), equalTo("E-TDBJ-37: Failed to re-enable AutoCommit"));
        assertThat(exception.getCause().getMessage(), equalTo("mock"));
    }
}
