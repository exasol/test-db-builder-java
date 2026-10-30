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
import com.exasol.dbbuilder.dialects.exasol.udf.UdfScript;

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
        try (final ExasolSchema schema = new ExasolSchema(objectWriter, ExasolIdentifier.of("TEST_SCHEMA"))) {
            final AdapterScript adapterScript = schema.createAdapterScriptBuilder("MY_ADAPTER").content("content")
                    .language(AdapterScript.Language.JAVA).build();
            objectWriter.write(adapterScript);
            assertThat(objectWriter.getLastQuery(),
                    equalTo("CREATE JAVA ADAPTER SCRIPT \"TEST_SCHEMA\".\"MY_ADAPTER\" AS\ncontent\n/"));
        }
    }

    @Test
    // [utest->dsn~creating-exasol-java-object-with-jvm-options~1]
    void testWriteAdapterScriptWithJvmOption() {
        final ExasolImmediateDatabaseObjectWriterStub objectWriter = new ExasolImmediateDatabaseObjectWriterStub(
                this.connectionMock, ExasolObjectConfiguration.builder().withJvmOptions("-DmyProp=1").build());
        try (final ExasolSchema schema = new ExasolSchema(objectWriter, ExasolIdentifier.of("TEST_SCHEMA"))) {
            final AdapterScript adapterScript = schema.createAdapterScriptBuilder("MY_ADAPTER").content("content")
                    .language(AdapterScript.Language.JAVA).build();
            objectWriter.write(adapterScript);
            assertThat(objectWriter.getLastQuery(), equalTo(
                    "CREATE JAVA ADAPTER SCRIPT \"TEST_SCHEMA\".\"MY_ADAPTER\" AS\n%jvmoption -DmyProp=1;\ncontent\n/"));
        }
    }

    @Test
    // [utest->dsn~creating-udfs.disable-telemetry~1]
    void testWriteUdfScriptDisablesTelemetry() {
        final ExasolImmediateDatabaseObjectWriterStub objectWriter = new ExasolImmediateDatabaseObjectWriterStub(
                this.connectionMock, ExasolObjectConfiguration.builder().build());
        try (final ExasolSchema schema = new ExasolSchema(objectWriter, ExasolIdentifier.of("TEST_SCHEMA"))) {
            schema.createUdfBuilder("MY_UDF").language(UdfScript.Language.PYTHON3)
                    .inputType(UdfScript.InputType.SCALAR).returns("VARCHAR").content("return 'value'").build();
            assertThat(objectWriter.getLastQuery(), equalTo(
                    "CREATE PYTHON3 SCALAR SCRIPT \"TEST_SCHEMA\".\"MY_UDF\"( ...) RETURNS VARCHARAS "
                            + "%env EXASOL_TELEMETRY_DISABLE=1;\n\nreturn 'value'\n/"));
        }
    }

    @Test
    void testWriteUdfScriptWithCustomEnvironmentVariable() {
        final ExasolImmediateDatabaseObjectWriterStub objectWriter = new ExasolImmediateDatabaseObjectWriterStub(
                this.connectionMock, ExasolObjectConfiguration.builder().build());
        try (final ExasolSchema schema = new ExasolSchema(objectWriter, ExasolIdentifier.of("TEST_SCHEMA"))) {
            schema.createUdfBuilder("MY_UDF").language(UdfScript.Language.PYTHON3)
                    .inputType(UdfScript.InputType.SCALAR).returns("VARCHAR")
                    .environmentVariable("CUSTOM_SETTING", "custom-value").content("return 'value'").build();
            assertThat(objectWriter.getLastQuery(), equalTo(
                    "CREATE PYTHON3 SCALAR SCRIPT \"TEST_SCHEMA\".\"MY_UDF\"( ...) RETURNS VARCHARAS "
                            + "%env EXASOL_TELEMETRY_DISABLE=1;\n%env CUSTOM_SETTING=custom-value;\n\nreturn 'value'\n/"));
        }
    }

    @Test
    void testWriteUdfScriptCanOverrideDefaultTelemetryEnvironmentVariable() {
        final ExasolImmediateDatabaseObjectWriterStub objectWriter = new ExasolImmediateDatabaseObjectWriterStub(
                this.connectionMock, ExasolObjectConfiguration.builder().build());
        try (final ExasolSchema schema = new ExasolSchema(objectWriter, ExasolIdentifier.of("TEST_SCHEMA"))) {
            schema.createUdfBuilder("MY_UDF").language(UdfScript.Language.PYTHON3)
                    .inputType(UdfScript.InputType.SCALAR).returns("VARCHAR")
                    .environmentVariable("EXASOL_TELEMETRY_DISABLE", "0").content("return 'value'").build();
            assertThat(objectWriter.getLastQuery(), equalTo(
                    "CREATE PYTHON3 SCALAR SCRIPT \"TEST_SCHEMA\".\"MY_UDF\"( ...) RETURNS VARCHARAS "
                            + "%env EXASOL_TELEMETRY_DISABLE=0;\n\nreturn 'value'\n/"));
        }
    }
}
