package com.exasol.dbbuilder.dialects;

import java.sql.*;
import java.util.logging.Logger;

import com.exasol.errorreporting.ExaError;

/**
 * This class allows temporarily deactivating AutoCommit for a given {@link Connection} and restores the original state
 * in {@link #close()}. If the database does not support deactivating AutoCommit (i.e. throws a
 * {@link SQLFeatureNotSupportedException}), this class will silently ignore it.
 */
class AutoCommit implements AutoCloseable {
    private static final Logger LOG = Logger.getLogger(AutoCommit.class.getName());
    private final Connection connection;

    private AutoCommit(final Connection connection) {
        this.connection = connection;
    }

    static AutoCommit tryDeactivate(final Connection connection) {
        try {
            final boolean originalState = connection.getAutoCommit();
            if (!originalState) {
                return new AutoCommit(null);
            }
            if (deactivatingAutoCommitSuccessful(connection)) {
                return new AutoCommit(connection);
            } else {
                return new AutoCommit(null);
            }
        } catch (final SQLException exception) {
            throw new DatabaseObjectException(
                    ExaError.messageBuilder("E-TDBJ-36").message("Failed to check AutoCommit state").toString(),
                    exception);
        }
    }

    private static boolean deactivatingAutoCommitSuccessful(final Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);
            return true;
        } catch (final SQLFeatureNotSupportedException exception) {
            LOG.fine("Database does not support deactivating AutoCommit: " + exception.getMessage());
            return false;
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (final SQLException exception) {
                throw new DatabaseObjectException(
                        ExaError.messageBuilder("E-TDBJ-37").message("Failed to re-enable AutoCommit").toString(),
                        exception);
            }
        }
    }
}
