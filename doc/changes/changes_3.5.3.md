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

* Added `org.slf4j:slf4j-jdk14:2.0.9`
