package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.db.ExasolIdentifier;
import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.DatabaseObjectDeletedException;
import com.exasol.dbbuilder.dialects.DatabaseObjectException;

@ExtendWith(MockitoExtension.class)
// [utest->dsn~creating-connections~1]
class ConnectionDefinitionTest {
    private static final String CONNECTION_TARGET = "THE_TARGET";
    private static final Identifier CONNECTION_NAME = ExasolIdentifier.of("THE_CONNECTION_NAME");

    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;

    @Test
    void testConstructorDoesNotWrite() {
        testee();
        verify(writerMock, never()).write(any(ConnectionDefinition.class));
    }

    @Test
    void testGetName() {
        assertThat(testee().getName(), equalTo(CONNECTION_NAME.toString()));
    }

    @Test
    void testGetFullyQualifiedName() {
        assertThat(testee().getFullyQualifiedName(), equalTo("\"" + CONNECTION_NAME + "\""));
    }

    @Test
    void testGetType() {
        assertThat(testee().getType(), equalTo("connection"));
    }

    @Test
    void testGetTarget() {
        assertThat(testee().getTarget(), equalTo("THE_TARGET"));
    }

    @Test
    void testGetCredentials() {
        final ConnectionDefinition connectionDefinition = testee("JOHNDOE", "SECRET");
        assertAll(() -> assertThat("user name", connectionDefinition.getUserName(), equalTo("JOHNDOE")),
                () -> assertThat("password", connectionDefinition.getPassword(), equalTo("SECRET")));
    }

    @Test
    void testHasUserNameFalse() {
        assertThat(testee().hasUserName(), equalTo(false));
    }

    @Test
    void testHasUserNameFalseWhenEmpty() {
        assertThat(testee("", null).hasUserName(), equalTo(false));
    }

    @Test
    void testHasUserNameTrue() {
        assertThat(testee("THE_USER", null).hasUserName(), equalTo(true));
    }

    @Test
    void testHasPasswordFalse() {
        assertThat(testee().hasPassword(), equalTo(false));
    }

    @Test
    void testHasPasswordFalseWhenEmpty() {
        assertThat(testee("user", "").hasPassword(), equalTo(false));
    }

    @Test
    void testHasPasswordTrue() {
        assertThat(testee("user", "THE_PASSWORD").hasPassword(), equalTo(true));
    }

    @Test
    void testHasParent() {
        assertThat(testee().hasParent(), equalTo(false));
    }

    @Test
    void testGetParentThrowsException() {
        final ConnectionDefinition connectionDefinition = testee();
        assertThrows(DatabaseObjectException.class, connectionDefinition::getParent);
    }

    @Test
    void testDrop() {
        final ConnectionDefinition connectionDefinition = testee();
        connectionDefinition.drop();
        verify(writerMock).drop(same(connectionDefinition));
    }

    @Test
    void testClose() {
        final ConnectionDefinition connectionDefinition = testee();
        connectionDefinition.close();
        verify(writerMock).drop(same(connectionDefinition));
    }

    @Test
    void testDropTwiceFails() {
        final ConnectionDefinition connectionDefinition = testee();
        connectionDefinition.drop();
        assertThrows(DatabaseObjectDeletedException.class, connectionDefinition::drop);
    }

    private ConnectionDefinition testee(final String user, final String password) {
        return new ConnectionDefinition(this.writerMock, CONNECTION_NAME, CONNECTION_TARGET, user, password);
    }

    private ConnectionDefinition testee() {
        return testee(null, null);
    }
}
