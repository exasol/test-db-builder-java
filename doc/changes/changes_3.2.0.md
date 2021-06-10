# Test Database Builder 3.2.0, released 2021-06-10

Code name: Truncating tables

## Summary

In this release following changes are made:

* Added support for truncating tables.
* Used Error Message Builder for Error Messages.
* Unified the interfaces for creation of dB Objects

## Features

* #67: Add Table#truncate()
* #60: Used Error Message Builder for Error Messages
* #55: Unified the interfaces for creation of dB Objects

## Bug Fixes:

* #80: Fixed broken url link in the pom.xml

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:error-reporting-java:0.2.2` to `0.4.0`

### Runtime Dependency Updates

* Updated `com.exasol:exasol-jdbc:7.0.4` to `7.0.7`
* Updated `mysql:mysql-connector-java:8.0.23` to `8.0.25`
* Updated `org.postgresql:postgresql:42.2.18` to `42.2.20.jre7`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:3.5.0` to `3.5.3`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.5.4` to `3.6.1`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.7.1` to `5.7.2`
* Updated `org.junit.jupiter:junit-jupiter-params:5.7.1` to `5.7.2`
* Updated `org.mockito:mockito-junit-jupiter:3.7.7` to `3.11.0`
* Updated `org.testcontainers:junit-jupiter:1.15.1` to `1.15.3`
* Updated `org.testcontainers:mysql:1.15.1` to `1.15.3`
* Updated `org.testcontainers:postgresql:1.15.1` to `1.15.3`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:0.4.2` to `0.7.2`
* Added `io.github.zlika:reproducible-build-maven-plugin:0.13`
