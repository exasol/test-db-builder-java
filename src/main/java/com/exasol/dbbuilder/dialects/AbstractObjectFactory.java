package com.exasol.dbbuilder.dialects;

import java.nio.file.Path;

/**
 * An abstract base for all ObjectFactories.
 */
public abstract class AbstractObjectFactory implements DatabaseObjectFactory {

    @Override
    // [impl->dsn~creating-objects-through-sql-files~1]
    public void executeSqlFile(final Path... sqlFiles) {
        getWriter().executeSqlFile(sqlFiles);
    }

    protected abstract DatabaseObjectWriter getWriter();
}