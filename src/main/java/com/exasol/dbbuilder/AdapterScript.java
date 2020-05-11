package com.exasol.dbbuilder;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

/**
 * Virtual Schema Adapter Script
 */
public class AdapterScript extends AbstractSchemaChild {
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
    public AdapterScript(final DatabaseObjectWriter writer, final Schema parentSchema, final String name,
            final Language language, final String content) {
        super(writer, parentSchema, name);
        this.language = language;
        this.content = content;
        writer.write(this);
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

    public enum Language {
        JAVA, PYTHON, LUA, R
    }
}