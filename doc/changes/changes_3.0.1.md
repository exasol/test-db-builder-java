# Test Database Builder 3.0.1, released 2021-02-24

Code name: Made `script.execute` robust against quotes

## Summary

TDB 3.0.1 allows you to create and execute Exasol scripts. In this released we fixed a bug in the implementation of execute that lead to an exception when string parameters containing quotes were passed to the execute function.

## Features

* #48: Added support for multiple JARs in BucketFS content.

## Bugfixes

* #70: Made `script.execute` robust against quotes

## Documentation

* #58: Added UDF script creation examples to user guide
* #64: Fixed typo in connection definition example in user guide

## Dependency Updates

* Updated `com.exasol:exasol-testcontainers:3.4.0` to `3.5.0`
* Updated `com.exasol:hamcrest-resultset-matcher:1.3.0` to `1.4.0`
* Updated `com.exasol:error-code-crawler-maven-plugin:0.1.0` to `0.1.1`
* Updated `mysql:mysql-connector-java:8.0.22` to `8.0.23`
* Updated `org.mockito:mockito-junit-jupiter:3.7.0` to `3.7.7`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.7` to `2.8.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.5` to `0.8.6`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.7.0` to `5.7.1`
* Updated `org.junit.jupiter:junit-jupiter-params:5.7.0` to `5.7.1`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.5.1` to `3.5.4`
