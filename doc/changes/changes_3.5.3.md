# Test Database Builder for Java 3.5.3, released 2023-??-??

Code name: Make DB objects `AutoClosable`

## Summary

This release let's all database object classes implement the `AutoClosable` interface. This allows deleting them automatically using a try-with-resources block.

## Features

* #115: Implemented `AutoClosable` interface in all database objects

## Dependency Updates

### Compile Dependency Updates

* Removed `org.slf4j:slf4j-jdk14:2.0.9`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.6.3` to `7.0.0`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.2` to `1.6.3`
* Updated `org.junit-pioneer:junit-pioneer:2.1.0` to `2.2.0`
* Updated `org.postgresql:postgresql:42.6.0` to `42.7.0`
* Added `org.slf4j:slf4j-jdk14:2.0.9`
* Updated `org.testcontainers:junit-jupiter:1.19.0` to `1.19.3`
* Updated `org.testcontainers:mysql:1.19.0` to `1.19.3`
* Updated `org.testcontainers:oracle-xe:1.19.0` to `1.19.3`
* Updated `org.testcontainers:postgresql:1.19.0` to `1.19.3`
