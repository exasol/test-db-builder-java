# Test Database Builder for Java 3.7.0, released 2026-04-21

Code name: Disable telemetry for tests

## Summary

This release disables telemetry for UDF scripts and virtual schemas by adding virtual schema property `TELEMETRY=false` and by setting environment variable `EXASOL_TELEMETRY_DISABLE=true` for UDF scripts.

## Features

* #153: Disable telemetry for UDF scripts and virtual schemas

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:db-fundamentals-java:0.1.3` to `0.1.4`
* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:25.2.5` to `26.2.7`
* Updated `com.exasol:exasol-testcontainers:7.2.0` to `7.2.3`
* Updated `com.mysql:mysql-connector-j:9.5.0` to `9.6.0`
* Updated `com.oracle.database.jdbc:ojdbc11:23.26.0.0.0` to `23.26.1.0.0`
* Removed `org.junit.jupiter:junit-jupiter-api:5.13.4`
* Updated `org.junit.jupiter:junit-jupiter-params:5.13.4` to `6.0.3`
* Updated `org.mockito:mockito-junit-jupiter:5.20.0` to `5.23.0`
* Updated `org.postgresql:postgresql:42.7.8` to `42.7.10`
* Updated `org.testcontainers:testcontainers-junit-jupiter:2.0.1` to `2.0.5`
* Updated `org.testcontainers:testcontainers-mysql:2.0.1` to `2.0.5`
* Updated `org.testcontainers:testcontainers-oracle-xe:2.0.1` to `2.0.5`
* Updated `org.testcontainers:testcontainers-postgresql:2.0.1` to `2.0.5`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.5` to `2.0.6`
* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.4.6`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.14.1` to `3.15.0`
* Updated `org.apache.maven.plugins:maven-resources-plugin:3.3.1` to `3.4.0`
* Updated `org.apache.maven.plugins:maven-source-plugin:3.2.1` to `3.4.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.19.1` to `2.21.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.2.0.4988` to `5.5.0.6356`
* Updated `org.sonatype.central:central-publishing-maven-plugin:0.9.0` to `0.10.0`
