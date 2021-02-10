package com.exasol.dbbuilder.dialects.exasol;

import static com.exasol.dbbuilder.dialects.exasol.AdapterScript.Language.JAVA;
import static com.exasol.dbbuilder.dialects.exasol.AdapterScript.Language.LUA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.shaded.com.google.common.io.Files;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.Schema;

@ExtendWith(MockitoExtension.class)
class AdapterScriptTest {
    private static final String ADAPTER_NAME = "ADAPTER";
    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;
    @Mock
    private Schema schemaMock;

    @Test
    void testGetName() {
        assertThat(defaultAdapterScriptBuilder().build().getName(), equalTo(ADAPTER_NAME));
    }

    // [utest->dsn~creating-adapter-scripts~1]
    private AdapterScript.Builder defaultAdapterScriptBuilder() {
        return AdapterScript.builder(this.writerMock, this.schemaMock, ExasolIdentifier.of(ADAPTER_NAME)).language(JAVA)
                .content("");
    }

    @Test
    void testGetFullyQualifiedName() {
        Mockito.when(this.schemaMock.getFullyQualifiedName()).thenReturn("\"PARENT\"");
        assertThat(defaultAdapterScriptBuilder().build().getFullyQualifiedName(),
                equalTo("\"PARENT\".\"" + ADAPTER_NAME + "\""));
    }

    @Test
    void testGetType() {
        assertThat(defaultAdapterScriptBuilder().build().getType(), equalTo("adapter script"));
    }

    @Test
    void testHasParent() {
        assertThat(defaultAdapterScriptBuilder().build().hasParent(), equalTo(true));
    }

    @Test
    void testGetParent() {
        assertThat(defaultAdapterScriptBuilder().build().getParent(), sameInstance(this.schemaMock));
    }

    @Test
    void testGetLaguage() {
        assertThat(defaultAdapterScriptBuilder().language(LUA).build().getLanguage(), equalTo(LUA));
    }

    @Test
    void testGetContent() {
        final String expectedContent = "content";
        assertThat(defaultAdapterScriptBuilder().content(expectedContent).build().getContent(),
                equalTo(expectedContent));
    }

    @Test
    void testLoadContentFromFile(@TempDir final Path tempDir) throws IOException {
        final String expected_content = "-- this is a comment";
        final Path tempFile = tempDir.resolve("script.lua");
        Files.write(expected_content.getBytes(), tempFile.toFile());
        assertThat(defaultAdapterScriptBuilder().content(tempFile).build().getContent(), equalTo(expected_content));
    }

    @Test
    void testBucketFsContent() {
        assertThat(
                defaultAdapterScriptBuilder()
                        .bucketFsContent("com.exasol.adapter.RequestDispatcher", "/buckets/bfsdefault/default/test.jar")
                        .build().getContent(),
                equalTo("%scriptclass com.exasol.adapter.RequestDispatcher;\n%jar /buckets/bfsdefault/default/test.jar;\n"));
    }

    @Test
    void testBucketFsContentWithMultipleJars() {
        assertThat(
                defaultAdapterScriptBuilder()
                        .bucketFsContent("com.exasol.adapter.RequestDispatcher", "/buckets/bfs/jars/test1.jar", "/buckets/bfs/jars/test2.jar")
                        .build().getContent(),
                equalTo("%scriptclass com.exasol.adapter.RequestDispatcher;\n%jar /buckets/bfs/jars/test1.jar;\n%jar /buckets/bfs/jars/test2.jar;\n"));
    }
}
