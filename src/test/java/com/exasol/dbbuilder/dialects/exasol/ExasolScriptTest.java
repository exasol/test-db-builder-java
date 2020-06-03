package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.shaded.com.google.common.io.Files;

import com.exasol.dbbuilder.dialects.QuoteApplier;
import com.exasol.dbbuilder.dialects.exasol.Script.Builder;

@ExtendWith(MockitoExtension.class)
class ExasolScriptTest {
    private final QuoteApplier quoteApplier = new ExasolQuoteApplier();
    private static final String SRIPT_NAME = "THE_SCRIPT";
    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;
    @Mock
    private ExasolSchema exasolSchemaMock;
    private Builder builder;

    @BeforeEach
    void beforeEach() {
        this.builder = Script.builder(this.writerMock, this.quoteApplier, this.exasolSchemaMock, SRIPT_NAME);
    }

    @Test
    void testGetType() {
        assertThat(this.builder.build().getType(), equalTo("script"));
    }

    @Test
    void testHasParent() {
        assertThat(this.builder.build().hasParent(), equalTo(true));
    }

    @Test
    void testGetParent() {
        assertThat(this.builder.build().getParent(), sameInstance(this.exasolSchemaMock));
    }

    @Test
    void testGetName() {
        assertThat(this.builder.build().getName(), equalTo("THE_SCRIPT"));
    }

    @Test
    void testGetContent() {
        assertThat(this.builder.content("the content").build().getContent(), equalTo("the content"));
    }

    @Test
    void testGetParameters() {
        assertThat(this.builder.parameter("foo", "bar").build().getParameters(),
                contains(new ScriptParameter("foo", false), new ScriptParameter("bar", false)));
    }

    @Test
    void testGetParametersWithArray() {
        assertThat(this.builder.parameter("foo").arrayParameter("bar").build().getParameters(),
                contains(new ScriptParameter("foo", false), new ScriptParameter("bar", true)));
    }

    @Test
    void testReturnsTableFalseByDefault() {
        assertThat(this.builder.build().returnsTable(), equalTo(false));
    }

    @Test
    void testLoadContentFromFile(@TempDir final Path tempDir) throws IOException {
        final String expected_content = "-- this is a comment";
        final Path tempFile = tempDir.resolve("script.lua");
        Files.write(expected_content.getBytes(), tempFile.toFile());
        assertThat(this.builder.content(tempFile).build().getContent(), equalTo(expected_content));
    }
}