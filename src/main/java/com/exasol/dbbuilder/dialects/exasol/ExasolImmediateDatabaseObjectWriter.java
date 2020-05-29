package com.exasol.dbbuilder.dialects.exasol;

import java.sql.*;
import java.util.*;

import com.exasol.dbbuilder.dialects.*;

/**
 * Database object writer that writes objects to the database immediately.
 */
public class ExasolImmediateDatabaseObjectWriter extends AbstractImmediateDatabaseObjectWriter {

    /**
     * Create a new instance of an {@link ExasolImmediateDatabaseObjectWriter}.
     *
     * @param connection JDBC connection
     */
    public ExasolImmediateDatabaseObjectWriter(final Connection connection) {
        super(connection);
    }

    /**
     * Create an adapter script for a Virtual Schema.
     *
     * @param adapterScript the adapter script to be created
     */
    public void write(final AdapterScript adapterScript) {
        writeToObject(adapterScript, "CREATE " + adapterScript.getLanguage() + " ADAPTER SCRIPT "
                + adapterScript.getFullyQualifiedName() + " AS\n" + adapterScript.getContent() + "\n/");
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
     * Execute a script.
     *
     * @param script          script to execute
     * @param parameterValues script parameters
     * @return row count
     */
    public int execute(final Script script, final Object... parameterValues) {
        try (final Statement statement = this.connection.createStatement()) {
            statement.execute(getScriptExecutionSql(script, parameterValues));
            return statement.getUpdateCount();
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(script, "Failed to execute script " + script.getFullyQualifiedName(),
                    exception);
        }
    }

    private String getScriptExecutionSql(final Script script, final Object[] parameterValues) {
        final StringBuilder builder = new StringBuilder("EXECUTE SCRIPT ");
        builder.append(script.getFullyQualifiedName());
        if (parameterValues.length > 0) {
            builder.append(" (");
            boolean first = true;
            for (final Object parameter : parameterValues) {
                if (first) {
                    first = false;
                } else {
                    builder.append(", ");
                }
                appendScriptParameterValue(builder, parameter);
            }
            builder.append(")");
        }
        return builder.toString();
    }

    private void appendScriptParameterValue(final StringBuilder builder, final Object parameter) {
        if (parameter instanceof Collection) {
            builder.append(" ARRAY(");
            int i = 0;
            for (final Object arrayItem : (Collection<?>) parameter) {
                if (i++ > 0) {
                    builder.append(", ");
                }
                appendScriptScalarParameter(builder, arrayItem);
            }
            builder.append(")");
        } else {
            appendScriptScalarParameter(builder, parameter);
        }
    }

    private void appendScriptScalarParameter(final StringBuilder builder, final Object value) {
        if (value instanceof String) {
            builder.append("'").append(value).append("'");
        } else {
            builder.append(value);
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