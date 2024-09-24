package com.exasol.dbbuilder.dialects.mysql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;
import com.exasol.dbbuilder.dialects.Schema;

@ExtendWith(MockitoExtension.class)
class MySqlTableTest {

    @Mock
    DatabaseObjectWriter writerMock;
    @Mock
    Schema schemaMock;

    @Test
    void createWithoutCharset() {
        final MySqlTable table = MySqlTable.builder(writerMock, schemaMock, MySQLIdentifier.of("tableName")).build();
        assertThat(table.getCharset(), is(nullValue()));
    }

    @Test
    void createWithCharset() {
        final MySqlTable table = MySqlTable.builder(writerMock, schemaMock, MySQLIdentifier.of("tableName"))
                .charset("myCharset").build();
        assertThat(table.getCharset(), equalTo("myCharset"));
    }
}
