package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.*;
import com.exasol.dbbuilder.dialects.exasol.udf.*;

@ExtendWith(MockitoExtension.class)
class UdfScriptTest {
    @Mock
    ExasolImmediateDatabaseObjectWriter writerMock;

    @Mock
    Schema schema;

    @Test
    void testGetType() {
        assertThat(getDefaultBuilder().build().getType(), equalTo("udf script"));
    }

    @Test
    void testGetLanguage() {
        assertThat(getDefaultBuilder().build().getLanguage(), equalTo(UdfScript.Language.JAVA));
    }

    @Test
    void testGetInputType() {
        assertThat(getDefaultBuilder().build().getInputType(), equalTo(UdfScript.InputType.SET));
    }

    @Test
    void testWithNoParameters() {
        assertThat(getDefaultBuilder().build().getParameters().isEmpty(), equalTo(true));
    }

    @Test
    void testWithParameters() {
        assertThat(getDefaultBuilder().parameter("test", "VARCHAR(254)").build().getParameters().size(), equalTo(1));
    }

    @Test
    void testEmitsWithDynamicEmitsReturnType() {
        assertThat(getDefaultBuilder().emits().build().getReturnType(), instanceOf(UdfReturnTypeDynamicEmits.class));
    }

    @Test
    void testEmitsWithPredefinedEmitsReturnType() {
        final Column column1 = new Column("col1", "VARCHAR");
        final Column column2 = new Column("col2", "VARCHAR");
        final UdfReturnTypePredefinedEmits result = (UdfReturnTypePredefinedEmits) getDefaultBuilder()
                .emits(column1, column2).build().getReturnType();
        assertThat(result.getColumns(), contains(column1, column2));
    }

    @Test
    void testEmitsWithReturnsReturnType() {
        final UdfReturnTypeReturns result = (UdfReturnTypeReturns) getDefaultBuilder().returns("VARCHAR").build()
                .getReturnType();
        assertThat(result.getType(), equalTo("VARCHAR"));
    }

    @Test
    void testMissingReturnType() {
        final UdfScript.Builder builder = UdfScript.builder(this.writerMock, this.schema, ExasolIdentifier.of("test"))
                .inputType(UdfScript.InputType.SET).language(UdfScript.Language.JAVA);
        final IllegalStateException exception = assertThrows(IllegalStateException.class, builder::build);
        assertThat(exception.getMessage(), equalTo(
                "E-TDBJ-7: Missing return type. Please set it by calling emits(), emits(types...) or returns(type) on this builder."));
    }

    @Test
    void testMissingLanguage() {
        final UdfScript.Builder builder = getDefaultBuilder().language(null);
        final IllegalStateException exception = assertThrows(IllegalStateException.class, builder::build);
        assertThat(exception.getMessage(), equalTo(
                "E-TDBJ-15: \'language\' is a required field. Please provide a value by calling language() before build()."));
    }

    private UdfScript.Builder getDefaultBuilder() {
        final UdfScript.Builder builder = UdfScript.builder(this.writerMock, this.schema, ExasolIdentifier.of("test"))
                .inputType(UdfScript.InputType.SET).language(UdfScript.Language.JAVA)
                .emits(new Column("test", "VARCHAR"));
        return builder;
    }

    @Test
    void testDrop() {
        final UdfScript script = getDefaultBuilder().build();
        script.drop();
        verify(writerMock).drop(same(script));
    }

    @Test
    void testDropTwiceFails() {
        final UdfScript script = getDefaultBuilder().build();
        script.drop();
        assertThrows(DatabaseObjectDeletedException.class, script::drop);
    }
}