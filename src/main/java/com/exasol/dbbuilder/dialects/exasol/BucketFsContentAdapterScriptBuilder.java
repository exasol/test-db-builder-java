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

    protected BucketFsContentAdapterScriptBuilder(final ExasolImmediateDatabaseObjectWriter writer,
            final Schema parentSchema, final Identifier name) {
        super(writer, parentSchema, name);
    }

    /**
     * Set the script's content to a JAR file from BucketFS.
     *
     * @param scriptClass         script class to execute
     * @param pathToJarInBucketfs path to the jar in BucketFS
     * @return self
     */
    public T bucketFsContent(final String scriptClass, final String pathToJarInBucketfs) {
        this.content("%scriptclass " + scriptClass + ";\n%jar " + pathToJarInBucketfs + ";\n");
        return getSelf();
    }
}
