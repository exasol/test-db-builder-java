package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.AbstractSchemaChild;
import com.exasol.dbbuilder.dialects.Schema;

/**
 * Virtual Schema Adapter Script.
 */
public class AdapterScript extends AbstractSchemaChild {
    private final ExasolImmediateDatabaseObjectWriter writer;
    private final Language language;
    private final String content;

    /**
     * Create a new instance of an {@link AdapterScript}.
     *
     * @param writer       database object writer
     * @param parentSchema parent schema
     * @param name         name of the adapter script
     * @param language     language the the script is implemented in
     * @param content      the actual script
     */
    public AdapterScript(final ExasolImmediateDatabaseObjectWriter writer, final Schema parentSchema, final String name,
            final Language language, final String content) {
        super(parentSchema, ExasolIdentifier.of(name), false);
        this.writer = writer;
        this.language = language;
        this.content = content;
        this.writer.write(this);
    }

    @Override
    public String getType() {
        return "adapter script";
    }

    /**
     * Get the implementation language.
     *
     * @return language in which the adapter script is implemented
     */
    public Language getLanguage() {
        return this.language;
    }

    /**
     * Get the actual script content.
     *
     * @return script content
     */
    public String getContent() {
        return this.content;
    }

    @Override
    // [impl->dsn~dropping-adapter-scripts~1]
    public void drop() {
        this.writer.drop(this);
    }

    public enum Language {
        JAVA, PYTHON, LUA, R
    }
}