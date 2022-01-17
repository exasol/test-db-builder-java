package com.exasol.dbbuilder.dialects.exasol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.AbstractSchemaChild;
import com.exasol.dbbuilder.dialects.Schema;

/**
 * Abstract basis for Exasol scripts.
 */
public abstract class AbstractScript extends AbstractSchemaChild {
    /** The Writer. */
    protected final ExasolImmediateDatabaseObjectWriter writer;
    /** The Content. */
    protected final String content;

    /**
     * Create a new instance of {@link AbstractScript}
     * 
     * @param builder builder to read the parameters from
     */
    protected AbstractScript(final Builder<?> builder) {
        super(builder);
        this.writer = builder.writer;
        this.content = builder.content;
    }

    /**
     * Get the script content (i.e. the implementation).
     *
     * @return script content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Abstract builder for {@link AbstractScript}.
     * 
     * @param <T> this type
     */
    public abstract static class Builder<T extends Builder<T>> extends AbstractSchemaChild.Builder {
        private final ExasolImmediateDatabaseObjectWriter writer;
        private String content;

        /**
         * Create a builder.
         * 
         * @param writer       object writer
         * @param parentSchema parent schema
         * @param name         script name
         */
        protected Builder(final ExasolImmediateDatabaseObjectWriter writer, final Schema parentSchema,
                final Identifier name) {
            super(parentSchema, name);
            this.writer = writer;
        }

        /**
         * Set the content of the script (i.e. the implementation).
         *
         * @param content script content
         * @return {@code this} for fluent programming
         */
        public T content(final String content) {
            this.content = content;
            return getSelf();
        }

        /**
         * Load the script content from a file.
         *
         * @param path path to file containing the script content
         * @return {@code this} for fluent programming
         * @throws IOException in case the file could not be read
         */
        public T content(final Path path) throws IOException {
            this.content = Files.readString(path);
            return getSelf();
        }

        @Override
        protected void validate() {
            super.validate();
            requireNotNull(this.writer, "writer");
        }

        /**
         * Get this.
         * 
         * @return self
         */
        protected abstract T getSelf();

        /**
         * Get the object writer.
         * 
         * @return object writer
         */
        protected ExasolImmediateDatabaseObjectWriter getWriter() {
            return this.writer;
        }
    }
}
