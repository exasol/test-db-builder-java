# Test Database Builder Java

[![Build Status](https://github.com/exasol/test-db-builder-java/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/test-db-builder-java/actions/workflows/ci-build.yml)
[![Maven Central &ndash; Test Database Builder for Java](https://img.shields.io/maven-central/v/com.exasol/test-db-builder-java)](https://search.maven.org/artifact/com.exasol/test-db-builder-java)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Atest-db-builder-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Atest-db-builder-java)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Atest-db-builder-java&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Atest-db-builder-java)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Atest-db-builder-java&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Atest-db-builder-java)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Atest-db-builder-java&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Atest-db-builder-java)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Atest-db-builder-java&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Atest-db-builder-java)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Atest-db-builder-java&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Atest-db-builder-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Atest-db-builder-java&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Atest-db-builder-java)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Atest-db-builder-java&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Atest-db-builder-java)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Atest-db-builder-java&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Atest-db-builder-java)

# Overview

Exasol's **Test Database Builder for Java** (TDBJ) is a library that makes writing integration tests for database applications easier.

The main design goals are to make the code of the integration test compact and readable at the same time.

## In a Nutshell

```java
import com.exasol.dbbuilder.dialects.DatabaseObjectFactory;

class OnlineShopIT {
    final static DatabaseObjectFactory factory;

    @BeforeAll

    static void beforeAll() {
        // ... get a JDBC connection and store it in variable "connection"
        factory = new ExasolObjectFactory(connection);
    }

    @Test
    void testShopItemList() {
        // Test preparation in the database:
        final Schema schema = factory.createSchema("ONLINESHOP");
        final Table table = schema.createTable("ITEMS", "PRODUCT_ID", "DECIMAL(18,0)", "NAME", "VARCHAR(40)")
                .insert("1", "Cat food")
                .insert("2", "Toy mouse");
        final User user = factory.createUser("KIMIKO")
                .grant(CREATE_SESSION)
                .grant(table, SELECT, UDPATE);
        // ... the actual test
    }
}
```

For more details, please refer to the [user guide](doc/user_guide/user_guide.md).

## What it is

This module is designed to be used in test code. It helps you write tests, quickly, easily while still focusing on readability.

## What it isn't

TDBJ is not suited for production code. We sacrifice speed and features for compactness and ease-of-use. If you are looking for code that helps writing production code, please refer to

* [SQL Statement Builder](https://github.com/exasol/sql-statement-builder)

## Supported Databases

* Exasol
* MySQL
* PostgreSQL

## Features

* Create: schemas, tables, adapter scripts, users, connection definitions, virtual schemas
* Grant privileges to users
* Insert data into tables

## Customer Support

This is an open source project which is written by enthusiasts at Exasol and not officially supported. We will still try to help you as much as possible. So please create GitHub issue tickets when you want to request features or report bugs.

# Table of Contents

## Information for Users

* [User Guide](doc/user_guide/user_guide.md)
* [Changelog](doc/changes/changelog.md)
* [Dependencies](dependencies.md)
