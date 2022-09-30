# Test Database Builder for Java 3.4.0, released 2022-09-30

Code name: Support system properties to set DEBUG_ADDRESS and LOG_LEVEL.

## Summary

This release adds support to use properties to set `DEBUG_ADDRESS` and `LOG_LEVEL` for virtual schemas, please see the [User Guide](https://github.com/exasol/test-db-builder-java/blob/main/doc/user_guide/user_guide.md#debug-output) for details.

## Features

* #103: Enabled to set `DEBUG_ADDRESS` and `LOG_LEVEL` based on properties.

## Dependency Updates

### Test Dependency Updates

* Added `org.junit-pioneer:junit-pioneer:1.7.1`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.1.1` to `1.1.2`
* Updated `com.exasol:project-keeper-maven-plugin:2.5.0` to `2.8.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0` to `3.1.0`
