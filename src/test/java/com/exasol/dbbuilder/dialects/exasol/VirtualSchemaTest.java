package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junitpioneer.jupiter.ClearSystemProperty;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.*;

@ExtendWith(MockitoExtension.class)
// [utest->dsn~creating-virtual-schemas~1]
class VirtualSchemaTest {
    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;
    private VirtualSchema.Builder builder;

    @BeforeEach
    void beforeEach() {
        this.builder = VirtualSchema.builder(this.writerMock, ExasolIdentifier.of("VS"));
    }

    @Test
    void testGetName() {
        assertThat(this.builder.build().getName(), equalTo("VS"));
    }

    @Test
    void testGetFullyQualifiedName() {
        assertThat(this.builder.build().getFullyQualifiedName(), equalTo("\"VS\""));
    }

    @Test
    void testGetType() {
        assertThat(this.builder.build().getType(), equalTo("virtual schema"));
    }

    @Test
    void testHasParent() {
        assertThat(this.builder.build().hasParent(), equalTo(false));
    }

    @Test
    void testGetParent() {
        final VirtualSchema build = this.builder.build();
        assertThrows(DatabaseObjectException.class, build::getParent);
    }

    @Test
    void testGetSourceSchemaName() {
        final String expectedSourceSchemaName = "SOURCESCHEMA";
        assertThat(this.builder.sourceSchemaName(expectedSourceSchemaName).build().getSourceSchemaName(),
                equalTo(expectedSourceSchemaName));
    }

    @Test
    void testBuilderWithSourceSchema(@Mock final Schema sourceSchemaMock) {
        when(sourceSchemaMock.getName()).thenReturn("SRC");
        assertThat(this.builder.sourceSchema(sourceSchemaMock).build().getSourceSchemaName(), equalTo("SRC"));
    }

    @Test
    void testGetAdapterScript(@Mock final AdapterScript adapterScriptMock) {
        assertThat(this.builder.adapterScript(adapterScriptMock).build().getAdapterScript(),
                sameInstance(adapterScriptMock));
    }

    @Test
    void testGetDialectName() {
        assertThat(this.builder.dialectName("DERBY").build().getDialectName(), equalTo("DERBY"));
    }

    @Test
    void testGetConnectionDefinition(@Mock final ConnectionDefinition connectionDefinitionMock) {
        assertThat(this.builder.connectionDefinition(connectionDefinitionMock).build().getConnectionDefinition(),
                sameInstance(connectionDefinitionMock));
    }

    @ParameterizedTest
    @CsvSource(value = { //
            "       ,     ,     ,            ,         ", //
            "1.2.3.4,     ,     ,     1.2.3.4     , ALL", //
            "1.2.3.4, 3000,     ,     1.2.3.4:3000, ALL", //
            "       , 3000,     ,            :3000, ALL", //
            "1.2.3.4,     , INFO,     1.2.3.4     , INFO", //
            "       , 3000, INFO,            :3000, INFO", //
            "       ,     , INFO,                 , INFO", //
            "1.2.3.4, 3000, INFO,     1.2.3.4:3000, INFO" })
    @ClearSystemProperty(key = VirtualSchema.DEBUG_HOST)
    @ClearSystemProperty(key = VirtualSchema.DEBUG_PORT)
    @ClearSystemProperty(key = VirtualSchema.DEBUG_LOG_LEVEL)
    void testDebugProperties(final String host, final String port, final String logLevel, final String expectedAddress,
            final String expectedLogLevel) {
        setSystemProperty(VirtualSchema.DEBUG_HOST, host);
        setSystemProperty(VirtualSchema.DEBUG_PORT, port);
        setSystemProperty(VirtualSchema.DEBUG_LOG_LEVEL, logLevel);
        final Map<String, String> properties = this.builder.build().getProperties();
        assertThat(properties.get("DEBUG_ADDRESS"), equalTo(expectedAddress));
        assertThat(properties.get("LOG_LEVEL"), equalTo(expectedLogLevel));
    }

    private void setSystemProperty(final String key, final String value) {
        if (value == null) {
            System.getProperties().remove(key);
        } else {
            System.setProperty(key, value);
        }
    }

    @Test
    void testGetProperties(@Mock final ConnectionDefinition connectionDefinitionMock) {
        when(connectionDefinitionMock.getName()).thenReturn("THE_CONNECTION");
        final Map<String, String> properties = this.builder //
                .dialectName("EXASOL_VS") //
                .properties(Map.of("FOO", "BAR", "BAZ", "ZOO")) //
                .connectionDefinition(connectionDefinitionMock) //
                .build() //
                .getProperties();
        assertAll(() -> assertThat("dialect property", properties, hasEntry("SQL_DIALECT", "EXASOL_VS")),
                () -> assertThat("connection property", properties, hasEntry("CONNECTION_NAME", "THE_CONNECTION")),
                () -> assertThat(properties, hasEntry("BAZ", "ZOO")),
                () -> assertThat(properties, hasEntry("FOO", "BAR")));
    }

    @Test
    void testDrop() {
        final VirtualSchema schema = builder.build();
        schema.drop();
        verify(writerMock).drop(same(schema));
    }

    @Test
    void testDropTwiceFails() {
        final VirtualSchema schema = builder.build();
        schema.drop();
        assertThrows(DatabaseObjectDeletedException.class, schema::drop);
    }

    @Test
    void testBuilderCallsWrite() {
        final VirtualSchema schema = builder.build();
        verify(writerMock).write(same(schema));
    }
}