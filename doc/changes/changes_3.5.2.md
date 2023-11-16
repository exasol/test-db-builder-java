# Test Database Builder for Java 3.5.2, released 2023-11-16

Code name: Improved "In a nutshell" section

## Summary

Improved the "in a nutshell" section to make it clearer that we are talking about tests here and what goes typically into `beforeAll()`. Updated dependencies and fixed build.

## Features

* #123: Improved the "in a nutshell" section.

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.6.2` to `6.6.3`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.0` to `1.6.2`
* Updated `com.mysql:mysql-connector-j:8.1.0` to `8.2.0`
* Updated `com.oracle.database.jdbc:ojdbc11:23.2.0.0` to `23.3.0.23.09`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.15.2` to `3.15.3`
* Added `org.junit.jupiter:junit-jupiter-api:5.10.1`
* Added `org.junit.jupiter:junit-jupiter-engine:5.10.1`
* Removed `org.junit.jupiter:junit-jupiter:5.9.3`
* Updated `org.mockito:mockito-junit-jupiter:5.5.0` to `5.7.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.3.0` to `1.3.1`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.12` to `2.9.16`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.4.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.1.2` to `3.2.2`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.5.0` to `3.6.2`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.1.2` to `3.2.2`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.0` to `2.16.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.10` to `0.8.11`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184` to `3.10.0.2594`
