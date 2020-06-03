package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.*;

@ExtendWith(MockitoExtension.class)
public class VirtualSchemaTest {
    private final QuoteApplier quoteApplier = new ExasolQuoteApplier();
    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;
    private VirtualSchema.Builder builder;

    @BeforeEach
    void beforeEach() {
        this.builder = VirtualSchema.builder(this.writerMock, this.quoteApplier, "VS");
    }

    @Test
    void testGetName() {
        assertThat(this.builder.build().getName(), equalTo("VS"));
    }

    @Test
    void testGetFullyQuallifiedName() {
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
        assertThrows(DatabaseObjectException.class, () -> this.builder.build().getParent());
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
}