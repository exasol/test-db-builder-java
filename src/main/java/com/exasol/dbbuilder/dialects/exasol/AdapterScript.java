package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.AbstractSchemaChild;
import com.exasol.dbbuilder.dialects.Schema;

import java.util.Optional;

/**
 * Virtual Schema Adapter Script.
 */
public class AdapterScript extends AbstractSchemaChild {
    private final ExasolImmediateDatabaseObjectWriter writer;
    private final Language language;
    private final String content;
    private final Optional<String> debuggerConnection;

    /**
     * Create a new instance of an {@link AdapterScript} without debugger connection.
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
        this.debuggerConnection = Optional.empty();
        this.writer.write(this);
        if (isAdapterScriptDebuggingEnabled()) {
            throw new IllegalStateException("Adapter script debugging was enabled but no debugger address was passed."
                    + "Please use a different constructor.");
        }
    }

    /**
     * Create a new instance of an {@link AdapterScript} with possible debugger connection. The debugger is only
     * attached if the property test.debugAdapterScripts="true" is set. You can set it by appending -Dtest.debug="true"
     * to your the JVM.
     *
     * @param writer             database object writer
     * @param parentSchema       parent schema
     * @param name               name of the adapter script
     * @param language           language the the script is implemented in
     * @param content            the actual script
     * @param debuggerConnection connection string for the debugger
     */
    public AdapterScript(final ExasolImmediateDatabaseObjectWriter writer, final Schema parentSchema, final String name,
            final Language language, final String content, final String debuggerConnection) {
        super(parentSchema, ExasolIdentifier.of(name), false);
        this.writer = writer;
        this.language = language;
        this.content = content;
        this.debuggerConnection = Optional.of(debuggerConnection);
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

    /**
     * Get if debugging adapter scripts was globally enabled enabled.
     * 
     * @return true if debugging adapter scripts was globally enabled
     */
    public static boolean isAdapterScriptDebuggingEnabled() {
        final String debugProperty = System.getProperty("tests.debugAdapterScripts");
        return debugProperty != null && debugProperty.equals("true");
    }

    /**
     * Get the debugger connection for this adapter script.
     * 
     * @return debugger connection
     */
    public Optional<String> getDebuggerConnection() {
        return this.debuggerConnection;
    }

    public enum Language {
        JAVA, PYTHON, LUA, R
    }
}