package com.exasol.dbbuilder.dialects.exasol;

import java.io.IOException;
import java.nio.file.Path;

import com.exasol.db.ExasolIdentifier;
import com.exasol.db.Identifier;
import com.exasol.dbbuilder.dialects.AbstractSchema;
import com.exasol.dbbuilder.dialects.DatabaseObjectException;
import com.exasol.dbbuilder.dialects.DatabaseObjectWriter;
import com.exasol.dbbuilder.dialects.exasol.udf.UdfScript;
import com.exasol.errorreporting.ExaError;

/**
 * Exasol database schema.
 */
public class ExasolSchema extends AbstractSchema {
    private final ExasolImmediateDatabaseObjectWriter writer;

    /**
     * Create a new database schema.
     *
     * @param writer database object writer
     * @param name   name of the database schema
     */
    public ExasolSchema(final ExasolImmediateDatabaseObjectWriter writer, final String name) {
        super(ExasolIdentifier.of(name));
        this.writer = writer;
        writer.write(this);
    }

    /**
     * Create an adapter script.
     *
     * @param name     name of the adapter script
     * @param language language the adapter script is implemented in
     * @param content  implementation of the script
     * @return adapter script
     */
    public AdapterScript createAdapterScript(final String name, final AdapterScript.Language language,
            final String content) {
        return AdapterScript.builder(this.writer, this, ExasolIdentifier.of(name)).language(language).content(content)
                .build();
    }

    /**
     * Create a builder for an adapter script.
     *
     * @param name name of the adapter script
     * @return builder (parent schema and writer are already set)
     */
    public AdapterScript.Builder createAdapterScriptBuilder(final Identifier name) {
        return AdapterScript.builder(this.writer, this, name);
    }

    /**
     * Create a builder for an adapter script.
     *
     * @param name name of the adapter script
     * @return builder (parent schema and writer are already set)
     */
    public AdapterScript.Builder createAdapterScriptBuilder(final String name) {
        return createAdapterScriptBuilder(ExasolIdentifier.of(name));
    }

    /**
     * Create a script that does not return anything.
     *
     * @param name           name of the script
     * @param content        implementation of the script
     * @param parameterNames names of the parameters of the script
     * @return script
     */
    // [impl->dsn~creating-scripts~1]
    public Script createScript(final String name, final String content, final String... parameterNames) {
        return createScriptBuilder(name).parameter(parameterNames).content(content).build();
    }

    /**
     * Create a script and load its implementation from a file.
     *
     * @param name           name of the script
     * @param path           path to file containing the script implementation
     * @param parameterNames names of the parameters of the script
     * @return script
     */
    // [impl->dsn~creating-scripts-from-files~1]
    public Script createScript(final String name, final Path path, final String... parameterNames) {
        try {
            return createScriptBuilder(name).parameter(parameterNames).content(path).build();
        } catch (final IOException exception) {
            throw new DatabaseObjectException(this,
                ExaError.messageBuilder("E-TDBJ-9").message("Unable to create script {{script name}} from file {{path}}.", name, path).toString(), exception);
        }
    }

    /**
     * Create a builder for a database script.
     *
     * @param name name of the script
     * @return builder
     */
    public Script.Builder createScriptBuilder(final String name) {
        return Script.builder(this.writer, this, name);
    }

    /**
     * Attach to a script that already exists in the database.
     *
     * @param name name of the script to attach to
     * @return control object for the existing script
     */
    public Script getScript(final String name) {
        return createScriptBuilder(name).attach();
    }

    /**
     * Create a builder for a UDF.
     *
     * @param name name of the UDF
     * @return builder
     */
    public UdfScript.Builder createUdfBuilder(final String name) {
        return UdfScript.builder(this.writer, this, ExasolIdentifier.of(name));
    }

    @Override
    public DatabaseObjectWriter getWriter() {
        return this.writer;
    }

    @Override
    protected Identifier getIdentifier(final String name) {
        return ExasolIdentifier.of(name);
    }
}