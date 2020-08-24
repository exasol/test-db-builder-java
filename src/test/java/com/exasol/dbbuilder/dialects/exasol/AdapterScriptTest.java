package com.exasol.dbbuilder.dialects.exasol;

import static com.exasol.dbbuilder.dialects.exasol.AdapterScript.Language.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.sameInstance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        assertThat(createContentlessJavaAdapterScript().getName(), equalTo(ADAPTER_NAME));
    }

    private AdapterScript createContentlessJavaAdapterScript() {
        return AdapterScript.builder().writer(this.writerMock).parentSchema(this.schemaMock).name(ADAPTER_NAME)
                .language(JAVA).content("").build();
    }

    @Test
    void testGetFullyQualifiedName() {
        Mockito.when(this.schemaMock.getFullyQualifiedName()).thenReturn("\"PARENT\"");
        assertThat(createContentlessJavaAdapterScript().getFullyQualifiedName(),
                equalTo("\"PARENT\".\"" + ADAPTER_NAME + "\""));
    }

    @Test
    void testGetType() {
        assertThat(AdapterScript.builder().writer(this.writerMock).parentSchema(this.schemaMock).name(ADAPTER_NAME)
                .language(LUA).content("").build().getType(), equalTo("adapter script"));
    }

    @Test
    void testHasParent() {
        assertThat(createContentlessJavaAdapterScript().hasParent(), equalTo(true));
    }

    @Test
    void testGetParent() {
        assertThat(createContentlessJavaAdapterScript().getParent(), sameInstance(this.schemaMock));
    }

    @Test
    void testGetLaguage() {
        final String expectedContent = "content";
        assertThat(AdapterScript.builder().writer(this.writerMock).parentSchema(this.schemaMock).name(ADAPTER_NAME)
                .language(R).content(expectedContent).build().getLanguage(), equalTo(R));
    }

    @Test
    void testGetContent() {
        final String expectedContent = "content";
        assertThat(AdapterScript.builder().writer(this.writerMock).parentSchema(this.schemaMock).name(ADAPTER_NAME)
                .language(PYTHON).content(expectedContent).build().getContent(), equalTo(expectedContent));
    }

    @Test
    void testGetDebuggerConnection() {
        final String expectedDebuggerConnection = "127.0.0.2:8000";
        assertThat(AdapterScript.builder().writer(this.writerMock).parentSchema(this.schemaMock).name(ADAPTER_NAME)
                .language(PYTHON).content("").debuggerConnection(expectedDebuggerConnection).build()
                .getDebuggerConnection().orElseThrow(), equalTo(expectedDebuggerConnection));
    }
}