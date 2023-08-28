# Test Database Builder for Java 3.5.0, released 2023-??-??

Code name: Mark Objects as Deleted

## Summary

This release marks deleted objects and rejects operations on deleted objects. It also updates dependencies on top of 3.4.2 and fixes broken links.

## Features

* #34: Marked deleted objects

## Documentation

* #112: Fixed broken links

## Refactoring

* #53: Partially moved "write()" calls from constructors to factory

## Security

* #117: Fixed failing dependency check

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:1.0.0` to `1.0.1`
* Added `org.slf4j:slf4j-jdk14:2.0.7`

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:7.1.17` to `7.1.20`
* Updated `com.exasol:exasol-testcontainers:6.5.0` to `6.6.1`
* Updated `com.exasol:hamcrest-resultset-matcher:1.5.2` to `1.6.0`
* Removed `com.google.protobuf:protobuf-java:3.21.12`
* Added `com.mysql:mysql-connector-j:8.1.0`
* Updated `com.oracle.database.jdbc:ojdbc11:21.8.0.0` to `23.2.0.0`
* Removed `mysql:mysql-connector-java:8.0.31`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.12.3` to `3.15.1`
* Updated `org.junit-pioneer:junit-pioneer:1.9.1` to `2.0.1`
* Updated `org.junit.jupiter:junit-jupiter-api:5.9.2` to `5.10.0`
* Removed `org.junit.jupiter:junit-jupiter:5.9.2`
* Updated `org.mockito:mockito-junit-jupiter:5.0.0` to `5.5.0`
* Updated `org.postgresql:postgresql:42.5.1` to `42.6.0`
* Updated `org.testcontainers:junit-jupiter:1.17.6` to `1.19.0`
* Updated `org.testcontainers:mysql:1.17.6` to `1.19.0`
* Updated `org.testcontainers:oracle-xe:1.17.6` to `1.19.0`
* Updated `org.testcontainers:postgresql:1.17.6` to `1.19.0`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.2.1` to `1.3.0`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.1` to `2.9.10`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.10.1` to `3.11.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.0.0` to `3.1.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.1.0` to `3.3.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M7` to `3.1.2`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:3.0.1` to `3.1.0`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.4.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M7` to `3.1.2`
* Added `org.basepom.maven:duplicate-finder-maven-plugin:2.0.1`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.3.0` to `1.5.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.13.0` to `2.16.0`
* Updated `org.itsallcode:openfasttrace-maven-plugin:1.5.0` to `1.6.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.8` to `0.8.10`
