package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.exasol.db.ExasolIdentifier;
import com.exasol.db.Identifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.DatabaseObjectException;

@ExtendWith(MockitoExtension.class)
// [utest->dsn~creating-connections~1]
class ConnectionDefinitionTest {
    private static final String CONNECTION_TARGET = "THE_TARGET";
    private static final Identifier CONNECTION_NAME = ExasolIdentifier.of("THE_CONNECTION_NAME");

    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;

    @Test
    void testGetName() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, CONNECTION_TARGET).getName(),
                equalTo(CONNECTION_NAME.toString()));
    }

    @Test
    void testGetFullyQualifiedName() {
        assertThat(
                new ConnectionDefinition(this.writerMock, CONNECTION_NAME, CONNECTION_TARGET).getFullyQualifiedName(),
                equalTo("\"" + CONNECTION_NAME + "\""));
    }

    @Test
    void testGetType() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, CONNECTION_TARGET).getType(),
                equalTo("connection"));
    }

    @Test
    void testGetTarget() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "FOOBAR").getTarget(), equalTo("FOOBAR"));
    }

    @Test
    void testGetCredentials() {
        final ConnectionDefinition connectionDefinition = new ConnectionDefinition(this.writerMock, CONNECTION_NAME,
                CONNECTION_TARGET, "JOHNDOE", "SECRET");
        assertAll(() -> assertThat("user name", connectionDefinition.getUserName(), equalTo("JOHNDOE")),
                () -> assertThat("password", connectionDefinition.getPassword(), equalTo("SECRET")));
    }

    @Test
    void testHasUserNameFalse() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, CONNECTION_TARGET).hasUserName(),
                equalTo(false));
    }

    @Test
    void testHasUserNameFalseWhenEmpty() {
        assertThat(
                new ConnectionDefinition(this.writerMock, CONNECTION_NAME, CONNECTION_TARGET, "", null).hasUserName(),
                equalTo(false));
    }

    @Test
    void testHasUserNameTrue() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, CONNECTION_TARGET, "THE_USER", null)
                .hasUserName(), equalTo(true));
    }

    @Test
    void testHasPasswordFalse() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, CONNECTION_TARGET).hasPassword(),
                equalTo(false));
    }

    @Test
    void testHasPasswordFalseWhenEmpty() {
        assertThat(
                new ConnectionDefinition(this.writerMock, CONNECTION_NAME, CONNECTION_TARGET, null, "").hasPassword(),
                equalTo(false));
    }

    @Test
    void testHasPasswordTrue() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, CONNECTION_TARGET, null, "THE_PASSWORD")
                .hasPassword(), equalTo(true));
    }

    @Test
    void testHasParent() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, CONNECTION_TARGET).hasParent(),
                equalTo(false));
    }

    @Test
    void testGetParentThrowsException() {
        final ConnectionDefinition connectionDefinition = new ConnectionDefinition(this.writerMock, CONNECTION_NAME,
                CONNECTION_TARGET);
        assertThrows(DatabaseObjectException.class, connectionDefinition::getParent);
    }
}