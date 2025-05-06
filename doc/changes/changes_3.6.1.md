# Test Database Builder for Java 3.6.1, released 2025-05-06

Code name: Reporting exception for CVE-2024-55551

## Summary

Exasol's JDBC driver fixed CVE-2024-55551 in version 24.2.1, yet OSSIndex still did not add the fix version.
This release updates the dependencies of this project and suppresses the OSSIndex warning for this CVE.

## Features

* #145: Added reporting exception for CVE-2024-55551

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:24.1.2` to `25.2.3`
* Updated `com.exasol:exasol-testcontainers:7.1.1` to `7.1.5`
* Removed `com.google.protobuf:protobuf-java:4.28.2`
* Updated `com.mysql:mysql-connector-j:9.0.0` to `9.3.0`
* Updated `com.oracle.database.jdbc:ojdbc11:23.5.0.24.07` to `23.7.0.25.01`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.16.2` to `3.19.4`
* Updated `org.junit-pioneer:junit-pioneer:2.2.0` to `2.3.0`
* Updated `org.mockito:mockito-junit-jupiter:5.13.0` to `5.17.0`
* Updated `org.postgresql:postgresql:42.7.4` to `42.7.5`
* Updated `org.slf4j:slf4j-jdk14:2.0.16` to `2.0.17`
* Updated `org.testcontainers:junit-jupiter:1.20.1` to `1.21.0`
* Updated `org.testcontainers:mysql:1.20.1` to `1.21.0`
* Updated `org.testcontainers:oracle-xe:1.20.1` to `1.21.0`
* Updated `org.testcontainers:postgresql:1.20.1` to `1.21.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:4.3.3` to `5.0.1`
* Added `com.exasol:quality-summarizer-maven-plugin:0.2.0`
* Added `io.github.git-commit-id:git-commit-id-maven-plugin:9.0.1`
* Removed `io.github.zlika:reproducible-build-maven-plugin:0.16`
* Added `org.apache.maven.plugins:maven-artifact-plugin:3.6.0`
* Updated `org.apache.maven.plugins:maven-clean-plugin:3.2.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.13.0` to `3.14.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.2` to `3.1.4`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.2.5` to `3.5.2`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:3.2.4` to `3.2.7`
* Updated `org.apache.maven.plugins:maven-install-plugin:3.1.2` to `3.1.4`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.7.0` to `3.11.2`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.12.1` to `3.21.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.2.5` to `3.5.2`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.6.0` to `1.7.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.2` to `2.18.0`
* Updated `org.itsallcode:openfasttrace-maven-plugin:1.8.0` to `2.3.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121` to `5.0.0.4389`
