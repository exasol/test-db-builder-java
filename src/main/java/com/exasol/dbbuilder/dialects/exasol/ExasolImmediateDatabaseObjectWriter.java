package com.exasol.dbbuilder.dialects.exasol;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

import com.exasol.dbbuilder.dialects.*;
import com.exasol.dbbuilder.dialects.exasol.udf.*;
import com.exasol.errorreporting.ExaError;

/**
 * Database object writer that writes objects to the database immediately.
 */
public class ExasolImmediateDatabaseObjectWriter extends AbstractImmediateDatabaseObjectWriter {
    private static final ExasolStringLiteralEscaper SINGLE_QUOTE_ESCAPER = new ExasolStringLiteralEscaper();
    private final ExasolObjectConfiguration configuration;

    /**
     * Create a new instance of an {@link ExasolImmediateDatabaseObjectWriter}.
     *
     * @param connection    JDBC connection
     * @param configuration global {@link ExasolObjectConfiguration}
     */
    public ExasolImmediateDatabaseObjectWriter(final Connection connection,
            final ExasolObjectConfiguration configuration) {
        super(connection);
        this.configuration = configuration;
    }

    /**
     * Create an adapter script for a Virtual Schema.
     *
     * @param adapterScript the adapter script to be created
     */
    // [impl->dsn~creating-adapter-scripts~1]
    public void write(final AdapterScript adapterScript) {
        final StringBuilder sqlBuilder = new StringBuilder("CREATE " + adapterScript.getLanguage() + " ADAPTER SCRIPT "
                + adapterScript.getFullyQualifiedName() + " AS\n");
        if (adapterScript.getLanguage().equals(AdapterScript.Language.JAVA)) {
            sqlBuilder.append(getJvmOptions());
        }
        sqlBuilder.append(adapterScript.getContent());
        sqlBuilder.append("\n");
        sqlBuilder.append("/");
        writeToObject(adapterScript, sqlBuilder.toString());
    }

    // [impl->dsn~creating-exasol-java-object-with-jvm-options~1]
    private String getJvmOptions() {
        if (this.configuration.getJvmOptions().isEmpty()) {
            return "";
        } else {
            return "%jvmoption " + String.join(" ", this.configuration.getJvmOptions()) + ";\n";
        }
    }

    /**
     * Drop an Adapter Script.
     * 
     * @param adapterScript to drop
     */
    void drop(final AdapterScript adapterScript) {
        writeToObject(adapterScript, "DROP ADAPTER SCRIPT " + adapterScript.getFullyQualifiedName());
    }

    /**
     * Create a connection definition.
     *
     * @param definition connection definition to be created
     */
    public void write(final ConnectionDefinition definition) {
        if (definition.hasUserName()) {
            writeToObject(definition,
                    "CREATE CONNECTION " + definition.getFullyQualifiedName() + " TO '" + definition.getTarget()
                            + "' USER '" + definition.getUserName() + ("' IDENTIFIED BY '") + definition.getPassword()
                            + "'");
        } else {
            if (definition.hasPassword()) {
                throw new DatabaseObjectException(definition,
                        "User name missing when trying to write connection definition "
                                + definition.getFullyQualifiedName()
                                + ". Please always provide user name and password together or not at all.");
            } else {
                writeToObject(definition, "CREATE CONNECTION " + definition.getFullyQualifiedName() + " TO '"
                        + definition.getTarget() + "'");
            }
        }
    }

    /**
     * Drop a Connection.
     * 
     * @param connectionDefinition to drop
     */
    public void drop(final ConnectionDefinition connectionDefinition) {
        writeToObject(connectionDefinition, "DROP CONNECTION " + connectionDefinition.getFullyQualifiedName());
    }

    @Override
    protected String getQuotedColumnName(final String columnName) {
        return "\"" + columnName + "\"";
    }

    /**
     * Create a script in the database.
     *
     * @param script script to be written
     */
    public void write(final Script script) {
        final StringBuilder builder = new StringBuilder("CREATE SCRIPT " + script.getFullyQualifiedName());
        final List<ScriptParameter> parameters = script.getParameters();
        if (!parameters.isEmpty()) {
            builder.append(" (");
            int i = 0;
            for (final ScriptParameter parameter : parameters) {
                if (i++ > 0) {
                    builder.append(", ");
                }
                if (parameter.isArray()) {
                    builder.append("ARRAY ");
                }
                builder.append(parameter.getName());
            }
            builder.append(")");
        }
        if (script.returnsTable()) {
            builder.append(" RETURNS TABLE");
        }
        builder.append(" AS\n") //
                .append(script.getContent()) //
                .append("\n/");
        writeToObject(script, builder.toString());
    }

    /**
     * Drop a script.
     *
     * @param script to drop
     */
    public void drop(final Script script) {
        writeToObject(script, "DROP SCRIPT " + script.getFullyQualifiedName());
    }

    // [impl->dsn~creating-udfs~1]
    public void write(final UdfScript udfScript) {
        final StringBuilder sqlBuilder = new StringBuilder("CREATE ");
        sqlBuilder.append(udfScript.getLanguage());
        sqlBuilder.append(" ");
        sqlBuilder.append(udfScript.getInputType());
        sqlBuilder.append(" SCRIPT ");
        sqlBuilder.append(udfScript.getFullyQualifiedName());
        sqlBuilder.append("( ");
        if (udfScript.getParameters().isEmpty()) {
            sqlBuilder.append("...");
        } else {
            sqlBuilder.append(udfScript.getParameters().stream()
                    .map(column -> column.getName() + " " + column.getType()).collect(Collectors.joining(", ")));
        }
        sqlBuilder.append(") ");
        udfScript.getReturnType().accept(new UdfReturnTypeWriter(sqlBuilder));
        sqlBuilder.append("AS ");
        if (udfScript.getLanguage().equals(UdfScript.Language.JAVA)) {
            sqlBuilder.append(getJvmOptions());
        }
        sqlBuilder.append("\n");
        sqlBuilder.append(udfScript.content);
        sqlBuilder.append("\n/");
        writeToObject(udfScript, sqlBuilder.toString());
    }

    // [impl->dsn~dropping-udfs~1]
    public void drop(final UdfScript udfScript) {
        writeToObject(udfScript, "DROP SCRIPT " + udfScript.getFullyQualifiedName());
    }

    private static class UdfReturnTypeWriter implements UdfReturnTypeVisitor {
        private final StringBuilder sqlBuilder;

        private UdfReturnTypeWriter(final StringBuilder sqlBuilder) {
            this.sqlBuilder = sqlBuilder;
        }

        @Override
        public void visit(final UdfReturnTypeReturns returns) {
            this.sqlBuilder.append("RETURNS ");
            this.sqlBuilder.append(returns.getType());
        }

        @Override
        public void visit(final UdfReturnTypeDynamicEmits emits) {
            this.sqlBuilder.append("EMITS(...) ");
        }

        @Override
        public void visit(final UdfReturnTypePredefinedEmits emits) {
            this.sqlBuilder.append("EMITS(");
            this.sqlBuilder.append(emits.getColumns().stream().map(column -> column.getName() + " " + column.getType())
                    .collect(Collectors.joining(", ")));
            this.sqlBuilder.append(")");
        }
    }

    @Override
    public void write(final User user) {
        writeToObject(user,
                "CREATE USER " + user.getFullyQualifiedName() + " IDENTIFIED BY \"" + user.getPassword() + "\"");
    }

    @Override
    public void write(final User user, final GlobalPrivilege... privileges) {
        writeToObject(user,
                "GRANT " + createCommaSeparatedSystemPrivilegeList(privileges) + " TO " + user.getFullyQualifiedName());
    }

    /**
     * Write a virtual schema to the database.
     *
     * @param virtualSchema Virtual Schema to write
     */
    // [impl->dsn~creating-virtual-schemas~1]
    public void write(final VirtualSchema virtualSchema) {
        final StringBuilder builder = new StringBuilder("CREATE VIRTUAL SCHEMA ")
                .append(virtualSchema.getFullyQualifiedName()) //
                .append("\nUSING ") //
                .append(virtualSchema.getAdapterScript().getFullyQualifiedName()) //
                .append(" WITH");
        for (final Map.Entry<String, String> property : virtualSchema.getProperties().entrySet()) {
            builder.append("\n  ");
            builder.append(property.getKey());
            builder.append(" = '");
            builder.append(property.getValue());
            builder.append("'");
        }
        writeToObject(virtualSchema, builder.toString());
    }

    /**
     * Drop a Virtual Schema.
     *
     * @param virtualSchema Virtual Schema to drop.
     */
    // [impl->dsn~dropping-virtual-schemas~2]
    public void drop(final VirtualSchema virtualSchema) {
        writeToObject(virtualSchema, "DROP VIRTUAL SCHEMA " + virtualSchema.getFullyQualifiedName() + " CASCADE");
    }

    @Override
    // [impl->dsn~dropping-schemas~2]
    public void drop(final Schema schema) {
        writeToObject(schema, "DROP SCHEMA " + schema.getFullyQualifiedName() + " CASCADE");
    }

    /**
     * Execute a script.
     *
     * @implNote this method does not use prepared statements but string concatenation, since Exasol currently does not
     *           support prepared statements for script execution (see https://www.exasol.com/support/browse/IDEA-42).
     * 
     * @param script          script to execute
     * @param parameterValues script parameters
     * @return row count
     */
    public int execute(final Script script, final Object... parameterValues) {
        final String query = getScriptExecutionSql(script, parameterValues);
        try (final Statement statement = this.connection.createStatement()) {
            statement.execute(query);
            return statement.getUpdateCount();
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(script, ExaError.messageBuilder("E-TDBJ-4")
                    .message("Failed to execute script query {{query}}.").parameter("query", query).toString(),
                    exception);
        }
    }

    private String getScriptExecutionSql(final Script script, final Object[] parameterValues) {
        final StringBuilder builder = new StringBuilder("EXECUTE SCRIPT ");
        builder.append(script.getFullyQualifiedName());
        if (parameterValues.length > 0) {
            builder.append(" (");
            builder.append(Arrays.stream(parameterValues).map(this::formatScriptParameterValue)
                    .collect(Collectors.joining(", ")));
            builder.append(")");
        }
        return builder.toString();
    }

    private String formatScriptParameterValue(final Object parameter) {
        if (parameter instanceof Collection) {
            return " ARRAY(" + ((Collection<?>) parameter).stream().map(this::formatScriptScalarParameter)
                    .collect(Collectors.joining(", ")) + ")";
        } else {
            return formatScriptScalarParameter(parameter);
        }
    }

    private String formatScriptScalarParameter(final Object value) {
        if (value instanceof String) {
            return "'" + SINGLE_QUOTE_ESCAPER.escape(value.toString()) + "'";
        } else {
            return value.toString();
        }
    }

    /**
     * Execute a script returning a table.
     *
     * @param script          script to execute
     * @param parameterValues script parameters
     * @return table
     */
    public List<List<Object>> executeQuery(final Script script, final Object... parameterValues) {
        final String sql = getScriptExecutionSql(script, parameterValues);
        try (final Statement statement = this.connection.createStatement();
                final ResultSet result = statement.executeQuery(sql)) {
            final int columnCount = result.getMetaData().getColumnCount();
            final List<List<Object>> table = new ArrayList<>();
            while (result.next()) {
                final List<Object> row = new ArrayList<>(columnCount);
                for (int columnIndex = 1; columnIndex <= columnCount; ++columnIndex) {
                    final Object value = result.getObject(columnIndex);
                    row.add(value);
                }
                table.add(row);
            }
            return table;
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(script,
                    "Failed to execute script returning table" + script.getFullyQualifiedName(), exception);
        }
    }
}
