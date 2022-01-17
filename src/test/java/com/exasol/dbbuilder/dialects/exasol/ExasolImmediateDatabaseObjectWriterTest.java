package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.AbstractImmediateDatabaseObjectWriterTest;
import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;

@ExtendWith(MockitoExtension.class)
class ExasolImmediateDatabaseObjectWriterTest extends AbstractImmediateDatabaseObjectWriterTest {

    @Override
    protected DatabaseObjectWriter getDatabaseObjectWriter() {
        return new ExasolImmediateDatabaseObjectWriter(this.connectionMock,
                ExasolObjectConfiguration.builder().build());
    }

    @Test
    void testWriteAdapterScript() {
        final ExasolImmediateDatabaseObjectWriterStub objectWriter = spy(new ExasolImmediateDatabaseObjectWriterStub(
                this.connectionMock, ExasolObjectConfiguration.builder().build()));
        final ExasolSchema schema = new ExasolSchema(objectWriter, ExasolIdentifier.of("TEST_SCHEMA"));
        final AdapterScript adapterScript = schema.createAdapterScriptBuilder("MY_ADAPTER").content("content")
                .language(AdapterScript.Language.JAVA).build();
        objectWriter.write(adapterScript);
        assertThat(objectWriter.getLastQuery(),
                equalTo("CREATE JAVA ADAPTER SCRIPT \"TEST_SCHEMA\".\"MY_ADAPTER\" AS\ncontent\n/"));
    }

    @Test
    // [utest->dsn~creating-exasol-java-object-with-jvm-options~1]
    void testWriteAdapterScriptWithJvmOption() {
        final ExasolImmediateDatabaseObjectWriterStub objectWriter = new ExasolImmediateDatabaseObjectWriterStub(
                this.connectionMock, ExasolObjectConfiguration.builder().withJvmOptions("-DmyProp=1").build());
        final ExasolSchema schema = new ExasolSchema(objectWriter, ExasolIdentifier.of("TEST_SCHEMA"));
        final AdapterScript adapterScript = schema.createAdapterScriptBuilder("MY_ADAPTER").content("content")
                .language(AdapterScript.Language.JAVA).build();
        objectWriter.write(adapterScript);
        assertThat(objectWriter.getLastQuery(), equalTo(
                "CREATE JAVA ADAPTER SCRIPT \"TEST_SCHEMA\".\"MY_ADAPTER\" AS\n%jvmoption -DmyProp=1;\ncontent\n/"));
    }
}