package com.exasol.dbbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

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
                equalTo(CONNECTION_NAME));
    }

    @Test
    void testGetType() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "IRRELEVANT").getType(),
                equalTo("connection definition"));
    }

    @Test
    void testGetTo() {
        assertThat(new ConnectionDefinition(this.writerMock, CONNECTION_NAME, "FOOBAR").getTo(), equalTo("FOOBAR"));
    }

    @Test
    void testGetCredentials() {
        final ConnectionDefinition connectionDefinition = new ConnectionDefinition(this.writerMock, CONNECTION_NAME,
                "IRRELEVANT", "JOHNDOE", "SECRET");
        assertAll(() -> assertThat("user name", connectionDefinition.getUserName(), equalTo("JOHNDOE")),
                () -> assertThat("password", connectionDefinition.getPassword(), equalTo("SECRET")));
    }
}