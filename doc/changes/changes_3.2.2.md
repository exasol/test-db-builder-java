# Test Database Builder for Java 3.2.2, released 2022-01-17

Code name: Fixed connections with no username

## Bug Fixes:

* #87 Fixed creation of connections with password but empty username

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.4.0` to `0.4.1`

### Runtime Dependency Updates

* Updated `com.exasol:exasol-jdbc:7.0.11` to `7.1.4`
* Updated `mysql:mysql-connector-java:8.0.26` to `8.0.27`
* Updated `org.postgresql:postgresql:42.2.23.jre7` to `42.3.1`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:4.0.0` to `5.1.1`
* Updated `com.exasol:hamcrest-resultset-matcher:1.4.1` to `1.5.1`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.7` to `3.8.2`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.7.2` to `5.8.2`
* Updated `org.junit.jupiter:junit-jupiter-params:5.7.2` to `5.8.2`
* Updated `org.mockito:mockito-junit-jupiter:3.11.2` to `4.2.0`
* Updated `org.testcontainers:junit-jupiter:1.16.0` to `1.16.2`
* Updated `org.testcontainers:mysql:1.16.0` to `1.16.2`
* Updated `org.testcontainers:postgresql:1.16.0` to `1.16.2`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:0.5.1` to `0.7.1`
* Updated `com.exasol:project-keeper-maven-plugin:0.10.0` to `1.3.4`
