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
    public Connection createConnectionDBA(String queryString) throws SQLException, JdbcDatabaseContainer.NoDriverFoundException {
        Properties info = new Properties();
        info.put("user", "SYSTEM");
        //info.put("user", this.getUsername());
        info.put("password", this.getPassword());
        String url = this.constructUrlForConnection(queryString);
        Driver jdbcDriverInstance = this.getJdbcDriverInstance();
        SQLException lastException = null;

        try {
            long start = System.currentTimeMillis();

            while(System.currentTimeMillis() < start + (long)(1000 * this.connectTimeoutSeconds) && this.isRunning()) {
                try {
                    //this.logger().debug("Trying to create JDBC connection using {} to {} with properties: {}", new Object[]{this.driver.getClass().getName(), url, info});
                    return jdbcDriverInstance.connect(url, info);
                } catch (SQLException var9) {
                    lastException = var9;
                    Thread.sleep(100L);
                }
            }
        } catch (InterruptedException var10) {
            Thread.currentThread().interrupt();
        }

        throw new SQLException("Could not create new connection", lastException);
    }
    }

