# Test Database Builder (Java)

[![Build Status](https://travis-ci.com/exasol/test-db-builder-java.svg?branch=master)](https://travis-ci.com/exasol/virtual-schemas)

SonarCloud results:

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

Exasol's **Test Database Builder for Java** (TDDB) is a library created to make writing integration tests for database applications easier.

The main design goals were to make the code of the integration test compact and readable at the same time.

## In a Nutshell

```java
// Precondition: Get a JDBC connection and store it in variable "connection"
final DatabaseObjectFactory factory = new ExasolObjectFactory(connection);
final Schema schema factory.createSchema("ONLINESHOP");
final Table table = schema.create("ITEMS", "PRODUCT_ID", "DECIMAL(18,0)", "NAME", "VARCHAR(40)")
        .insert("1", "Cat food")
        .insert("2", "Toy mouse");
final User user = factory.createUser("KIMIKO")
        .grant(CREATE_SESSION)
        .grant(table, SELECT, UDPATE);
```

For more details, please refer to the [user guide](user_guide/user_guide.md).

## What it is

This module is designed to be used in test code. It helps you write tests, quickly, easily while still focusing on readability.

## What it isn't

TDDB is not suited for production code. We sacrificed speed and features for compactness and ease-of-use. If you are looking for code that helps writing production code, please refer to

* [SQL Statement Builder](https://github.com/exasol/sql-statement-builder)
 
## Features

* Create: schemas, tables, adapter scripts, users, connection definitions, virtual schemas
* Grant privileges to users
* Insert data into tables

## Customer Support

This is an open source project which is written by enthusiasts at Exasol and not officially supported. We will still try to help you as much as possible. So pleae create Github issue tickets when you want to request features or report bugs.

# Table of Contents

## Information for Users

* [User Guide](doc/user-guide/user_guide.md)

## Dependencies

### Run Time Dependencies

Running the Test Database Builder requires a Java Runtime version 11 or later.

| Dependency                                                                          | Purpose                                                | License                          |
|-------------------------------------------------------------------------------------|--------------------------------------------------------|----------------------------------|
| [Exasol JDBC Driver][exasol-jdbc-driver]                                            | JDBC driver for Exasol database                        | MIT License                      |

### Test Dependencies

| Dependency                                                                          | Purpose                                                | License                          |
|-------------------------------------------------------------------------------------|--------------------------------------------------------|----------------------------------|
| [Apache Maven](https://maven.apache.org/)                                           | Build tool                                             | Apache License 2.0               |
| [Exasol Testcontainers][exasol-testcontainers]                                      | Exasol extension for the Testcontainers framework      | MIT License                      |
| [Java Hamcrest](http://hamcrest.org/JavaHamcrest/)                                  | Checking for conditions in code via matchers           | BSD License                      |
| [JUnit](https://junit.org/junit5)                                                   | Unit testing framework                                 | Eclipse Public License 1.0       |
| [Mockito](http://site.mockito.org/)                                                 | Mocking framework                                      | MIT License                      |
| [Testcontainers](https://www.testcontainers.org/)                                   | Container-based integration tests                      | MIT License                      |

### Maven Plug-ins

| Plug-in                                                                             | Purpose                                                | License                          |
|-------------------------------------------------------------------------------------|--------------------------------------------------------|----------------------------------|
| [Maven Compiler Plugin](https://maven.apache.org/plugins/maven-compiler-plugin/)    | Setting required Java version                          | Apache License 2.0               |
| [Maven Exec Plugin](https://www.mojohaus.org/exec-maven-plugin/)                    | Executing external applications                        | Apache License 2.0               |
| [Maven GPG Plugin](https://maven.apache.org/plugins/maven-gpg-plugin/)              | Code signing                                           | Apache License 2.0               |
| [Maven Failsafe Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)   | Integration testing                                    | Apache License 2.0               |
| [Maven Javadoc Plugin](https://maven.apache.org/plugins/maven-javadoc-plugin/)      | Creating a Javadoc JAR                                 | Apache License 2.0               |
| [Maven Jacoco Plugin](https://www.eclemma.org/jacoco/trunk/doc/maven.html)          | Code coverage metering                                 | Eclipse Public License 2.0       |
| [Maven Source Plugin](https://maven.apache.org/plugins/maven-source-plugin/)        | Creating a source code JAR                             | Apache License 2.0               |
| [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)   | Unit testing                                           | Apache License 2.0               |


[exasol-jdbc-driver]: https://www.exasol.com/portal/display/DOWNLOAD/Exasol+Download+Section
[exasol-testcontainers]: https://github.com/exasol/exasol-testcontainers