package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.Schema;

/**
 * Builder for {@link AbstractScript}, that can in addition add JAVA content from BucketFS.
 *
 * @param <T> this type
 */
public abstract class BucketFsContentAdapterScriptBuilder<T extends BucketFsContentAdapterScriptBuilder<T>>
        extends AbstractScript.Builder<T> {

    /**
     * Create a new instance of {@link BucketFsContentAdapterScriptBuilder}.
     *
     * @param writer       the writer
     * @param parentSchema the parent schema
     * @param name         the name
     */
    protected BucketFsContentAdapterScriptBuilder(final ExasolImmediateDatabaseObjectWriter writer,
            final Schema parentSchema, final Identifier name) {
        super(writer, parentSchema, name);
    }

    /**
     * Set the script's content to a JAR files from BucketFS.
     *
     * @param scriptClass        script class to execute
     * @param jarPathsInBucketfs paths to the jar files in BucketFS
     * @return self
     */
    public T bucketFsContent(final String scriptClass, final String... jarPathsInBucketfs) {
        final StringBuilder bucketContent = new StringBuilder();
        bucketContent.append("%scriptclass ").append(scriptClass).append(";\n");
        for (final String jarPath : jarPathsInBucketfs) {
            bucketContent.append("%jar ").append(jarPath).append(";\n");
        }
        this.content(bucketContent.toString());
        return getSelf();
    }
}
