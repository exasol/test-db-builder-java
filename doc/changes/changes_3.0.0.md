# Test Database Builder 3.0.0, released 2021-01-XX

Code name: Bulk insert for tables

## Summary

In this release, we removed the `Table#getRows()` method and by that cause an API change. We removed the method, since it requires all rows to be kept in memory which limits the size of the tables that can be built.

The new `Table#bulkInsert()` method allows you to insert multiple rows to a table in one transaction and by that much faster than using multiple calls to `Table#insert()`.

## Features / Enhancements

* #59: Added bulk insert for tables

## Dependency Updates

* Added `com.exasol:error-reporting-java:0.2.2`
* Added `com.exasol:error-code-crawler-maven-plugin:0.1.0`
* Updated `org.mockito:mockito-junit-jupiter:3.6.28` to `3.7.0`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.5` to `3.5.1`
