package com.exasol.dbbuilder.dialects.oracle;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.utility.DockerImageName;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

//reason for this:
//rights:
//https://github.com/testcontainers/testcontainers-java/issues/4615
//https://github.com/gvenzl/oci-oracle-xe/issues/41

public class OracleContainerDBA extends OracleContainer {
    public OracleContainerDBA(String dockerImageName) {
        super(DockerImageName.parse(dockerImageName));
    }
    int connectTimeoutSeconds = 120;

    //Code smell but also how it's done in the class it's inheriting from.
    // Finding a more novel approach isn't the goal here.
    // The only purpose of this class is delivering a DBA user connection
    // (which isn't done by default like for the postgresql or mysql containers).
    @SuppressWarnings("java:S2925")
    public Connection createConnectionDBA(String queryString) throws SQLException, JdbcDatabaseContainer.NoDriverFoundException {
        Properties info = new Properties();
        info.put("user", "SYSTEM");
        info.put("password", this.getPassword());
        String url = this.constructUrlForConnection(queryString);
        Driver jdbcDriverInstance = this.getJdbcDriverInstance();
        SQLException lastException = null;

        try {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() < start + (long)(1000 * this.connectTimeoutSeconds) && this.isRunning()) {
                try {
                    return jdbcDriverInstance.connect(url, info);
                } catch (SQLException exception) {
                    lastException = exception;
                    Thread.sleep(100L);
                }
            }
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }

        throw new SQLException("Could not create new connection", lastException);
    }
    }

