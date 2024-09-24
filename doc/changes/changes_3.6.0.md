# Test Database Builder for Java 3.6.0, released 2024-??-??

Code name: Fix CVE-2024-7254 in test dependency `com.google.protobuf:protobuf-java:3.25.1`

## Summary

This release fixes CVE-2024-7254 in test dependency `com.google.protobuf:protobuf-java:3.25.1`.

The release also speeds up inserting rows into a table by using batch insert, allows specifying a charset when creating MySQL tables, see the [user guide](../user_guide/user_guide.md#mysql-specific-database-objects) for details and supports databases that don't support transactions (i.e. setting autoCommit to `false`).

## Security

* #138: Fixed CVE-2024-7254 in test dependency `com.google.protobuf:protobuf-java:3.25.1`

## Features

* #137: Updated `AbstractImmediateDatabaseObjectWriter#write()` to use batching for inserting rows
* #134: Allowed specifying charset for MySQL tables
* #136: Added support for databases without transaction support

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:7.1.20` to `24.1.2`
* Updated `com.exasol:exasol-testcontainers:7.0.1` to `7.1.1`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.5` to `1.7.0`
* Added `com.google.protobuf:protobuf-java:4.28.2`
* Updated `com.mysql:mysql-connector-j:8.3.0` to `9.0.0`
* Updated `com.oracle.database.jdbc:ojdbc11:23.3.0.23.09` to `23.5.0.24.07`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.15.8` to `3.16.2`
* Updated `org.hamcrest:hamcrest:2.2` to `3.0`
* Updated `org.junit.jupiter:junit-jupiter-api:5.10.2` to `5.11.0`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.10.2` to `5.11.0`
* Updated `org.mockito:mockito-junit-jupiter:5.11.0` to `5.13.0`
* Updated `org.postgresql:postgresql:42.7.2` to `42.7.4`
* Updated `org.slf4j:slf4j-jdk14:2.0.12` to `2.0.16`
* Updated `org.testcontainers:junit-jupiter:1.19.7` to `1.20.1`
* Updated `org.testcontainers:mysql:1.19.7` to `1.20.1`
* Updated `org.testcontainers:oracle-xe:1.19.7` to `1.20.1`
* Updated `org.testcontainers:postgresql:1.19.7` to `1.20.1`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.0` to `2.0.3`
* Updated `com.exasol:project-keeper-maven-plugin:4.1.0` to `4.3.3`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.12.1` to `3.13.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.1` to `3.1.2`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.4.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:3.1.0` to `3.2.4`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.6.3` to `3.7.0`
* Updated `org.apache.maven.plugins:maven-toolchains-plugin:3.1.0` to `3.2.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.11` to `0.8.12`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.10.0.2594` to `4.0.0.4121`
* Updated `org.sonatype.plugins:nexus-staging-maven-plugin:1.6.13` to `1.7.0`
