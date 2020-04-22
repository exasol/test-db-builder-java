package com.exasol.dbbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

@ExtendWith(MockitoExtension.class)
public class VirtualSchemaTest {
    @Mock
    private DatabaseObjectWriter writerMock;
    @Mock
    private Schema parentSchemaMock;
    private VirtualSchema.Builder builder;

    @BeforeEach
    void beforeEach() {
        this.builder = VirtualSchema.builder(this.writerMock, this.parentSchemaMock, "VS");
    }

    @Test
    void testGetName() {
        assertThat(this.builder.build().getName(), equalTo("VS"));
    }

    @Test
    void testGetFullyQuallifiedName() {
        when(this.parentSchemaMock.getFullyQualifiedName()).thenReturn("PARENT");
        assertThat(this.builder.build().getFullyQualifiedName(), equalTo("PARENT.VS"));
    }

    @Test
    void testGetType() {
        assertThat(this.builder.build().getType(), equalTo("virtual schema"));
    }

    @Test
    void testGetSourceSchemaName() {
        final String expectedSourceSchemaName = "SOURCESCHEMA";
        assertThat(this.builder.sourceSchemaName(expectedSourceSchemaName).build().getSourceSchemaName(),
                equalTo(expectedSourceSchemaName));
    }

    @Test
    void testBuilderWithSourceSchema(@Mock final Schema sourceSchemaMock) {
        when(sourceSchemaMock.getFullyQualifiedName()).thenReturn("SRC");
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
    void testGetProperties() {
        final Map<String, String> properties = this.builder //
                .dialectName("EXASOL_VS") //
                .properties(Map.of("FOO", "BAR", "BAZ", "ZOO")) //
                .build() //
                .getProperties();
        assertAll(() -> assertThat(properties, hasEntry("SQL_DIALECT", "EXASOL_VS")),
                () -> assertThat(properties, hasEntry("BAZ", "ZOO")),
                () -> assertThat(properties, hasEntry("FOO", "BAR")));
    }
}