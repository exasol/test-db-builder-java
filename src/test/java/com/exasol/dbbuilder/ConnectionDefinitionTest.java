package com.exasol.dbbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

@ExtendWith(MockitoExtension.class)
class ConnectionDefinitionTest {
    private static final String CONNECTION_NAME = "CONNECTIONNAME";
    @Mock
    private DatabaseObjectWriter writerMock;

    @Test
    void testGetName() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT").getName(),
                equalTo(CONNECTION_NAME));
    }

    @Test
    void testGetFullyQuallifiedName() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT").getFullyQualifiedName(),
                equalTo("\"" + CONNECTION_NAME + "\""));
    }

    @Test
    void testGetType() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT").getType(),
                equalTo("connection"));
    }

    @Test
    void testGetTarget() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "FOOBAR").getTarget(), equalTo("FOOBAR"));
    }

    @Test
    void testGetCredentials() {
        final ConnectionDefinition connectionDefinition = new ConnectionDefinition(this.writerMock, CONNECTION_NAME,
                "IRRELEVANT", "JOHNDOE", "SECRET");
        assertAll(() -> assertThat("user name", connectionDefinition.getUserName(), equalTo("JOHNDOE")),
                () -> assertThat("password", connectionDefinition.getPassword(), equalTo("SECRET")));
    }

    @Test
    void testHasUserNameFalse() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT").hasUserName(),
                equalTo(false));
    }

    @Test
    void testHasUserNameFalseWhenEmpty() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT", "", null).hasUserName(),
                equalTo(false));
    }

    @Test
    void testHasUserNameTrue() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT", "THE_USER", null)
                .hasUserName(), equalTo(true));
    }

    @Test
    void testHasPasswordFalse() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT").hasPassword(),
                equalTo(false));
    }

    @Test
    void testHasPasswordFalseWhenEmpty() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT", null, "").hasPassword(),
                equalTo(false));
    }

    @Test
    void testHasPasswordTrue() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT", null, "THE_PASSWORD")
                .hasPassword(), equalTo(true));
    }

    @Test
    void testHasParent() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT").hasParent(),
                equalTo(false));
    }

    @Test
    void testGetParentThrowsException() {
        assertThrows(DatabaseObjectException.class,
                () -> new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT").getParent());
    }
}