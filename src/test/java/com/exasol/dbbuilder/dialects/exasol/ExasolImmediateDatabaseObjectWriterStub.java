package com.exasol.dbbuilder.dialects.exasol;

import java.sql.Connection;

import com.exasol.dbbuilder.dialects.DatabaseObject;

class ExasolImmediateDatabaseObjectWriterStub extends ExasolImmediateDatabaseObjectWriter {
    private String lastQuery = "";

    public ExasolImmediateDatabaseObjectWriterStub(final Connection connection,
            final ExasolObjectConfiguration configuration) {
        super(connection, configuration);
    }

    @Override
    protected void writeToObject(final DatabaseObject object, final String sql, final Object... parameters) {
        this.lastQuery = sql;
    }

    public String getLastQuery() {
        return this.lastQuery;
    }
}
