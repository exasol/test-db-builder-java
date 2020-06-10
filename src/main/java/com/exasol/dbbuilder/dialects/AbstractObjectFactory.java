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

    /**
     * Get a {@link DatabaseObjectWriter}.
     * 
     * @return new {@link DatabaseObjectWriter} instance
     */
    protected abstract DatabaseObjectWriter getWriter();
}