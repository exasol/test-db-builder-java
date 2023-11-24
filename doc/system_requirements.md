# System Requirement Specification Exasol Test Container

## Introduction

Exasol's Test Database Builder for Java (short "TDBJ") is a library that helps authors of integration test against databases writing compact and readable test code.

**&#9888; Important:** The TDBJ is meant to be used in tests only. It is neither designed nor particularly suited to be used in production scenarios!

## About This Document

### Target Audience

The target audience are end-users, requirement engineers, software designers and quality assurance. See section ["Stakeholders"](#stakeholders) for more details.

### Goal

TDBJ is targeted at functional testing mainly with the central design goal to make writing and reading the test code as convenient as possible for developers.

### Quality Goals

TDBJ's main quality goals are in descending order of importance:

1. Compact test code
1. Readable test code

## Stakeholders

### Integrators

Integrators integrate their solution with databases (like Exasol). To test this, they need to create objects in those databases and populate them with test data.

### Terms and Abbreviations

The following list gives you an overview of terms and abbreviations commonly used in TDBJ documents.

* ...

## Features

Features are the highest level requirements in this document that describe the main functionality of TDBJ.

### Supporting Different Database
`feat~supporting-different-database~1`

TDBJ supports the following databases:

- Exasol
- MySQL

Needs: req

### Creating Database Objects
`feat~creating-database-objects~1`

TDBJ allows developers to programmatically create database objects.

Needs: req

### Dropping Database Objects
`feat~dropping-existing-database-objects~2`

TDBJ allows developers to drop database content.

TDBJ drops the objects including all contained objects.

These include:
* Generic Database object
* Database specific database objects

Needs: req

### Populating Database With Test Data
`feat~populating-database-with-test-data~1`

TDBJ allows populating the database with test data.

<!-- Needs: req --><!-- See https://github.com/exasol/test-db-builder-java/issues/10 -->

### Running Executable Database Content
`feat~running-executable-database-content~1`

TDBJ allows developers to programmatically running database content that is executable (like scripts).

Needs: req

### Controlling Existing Database Objects
`feat~controlling-existing-database-objects~1`

TDBJ allows developers to attach to existing database objects to control them programmatically.

Needs: req


## Functional Requirements

In this section lists functional requirements from the user's perspective. The requirements are grouped by feature where they belong to a single feature.

### Supporting Different Database

#### Supporting Exasol Database
`req~supporting-exasol-database~1`

User can create objects for Exasol database.

Covers: 

* [feat~supporting-different-database~1](#supporting-different-database)

Needs: dsn

#### Supporting MySQl Database
`req~supporting-mysql-database~1`

User can create objects for MySQL database.

Covers: 

* [feat~supporting-different-database~1](#supporting-different-database)

Needs: dsn

### Creating Database Objects

#### Creating Users
`req~creating-users~1`

Users can create database users through TDBJ.

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn

#### Granting System Privileges to Users
`req~granting-system-privileges-to-users~1`

Users can grant System Privileges to created database users.

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn

#### Creating Scripts
`req~creating-scripts~1`

Users can create scripts where the script content is as part of the test code.

Rationale:

(Lua) scripts are the native way of extending the Exasol database.

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn

#### Creating UDFs
`req~creating-udf~1`

Users can create UDFs with content from file

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn

#### Creating Scripts From Files
`req~creating-scripts-from-files~1`

Users can create scripts where the content is loaded from a file.

Rationale:

Longer scripts are usually not kept directly in the test source code. Also this allows integration testing existing scripts.

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn

#### Creating Adapter Scripts
`req~creating-adapter-scripts~1`

Users can create Exasol adapter scripts using TDBJ.

 * (optional) debugger connection – Users can define a debugger connection. Users can control if the debugger is added by an switch that is independent of the source code. 

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn


#### Creating Tables
`req~creating-tables~1`

Users can create tables using TDBJ.

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn

#### Creating Connections
`req~creating-connections~1`

Users can create Exasol connection definitions using TDBJ.

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn

#### Creating Schemas
`req~creating-schemas~1`

Users can create Exasol schemas using TDBJ.

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn

#### Creating Virtual Schemas
`req~creating-virtual-schemas~1`

Users can create Exasol virtual schemas using TDBJ.

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn

#### Creating Objects Through SQL From Files
`req~creating-objects-through-sql-files~1`

Users can execute SQL files to create objects in the database.

Rationale:

Especially when scripts are part of the implementation under test, they often come in form of SQL files. In order to test them, TDBJ must be able to execute the SQL files that create them.

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn

### Dropping Database Objects

### Dropping Users
`req~dropping-users~1`

Users can drop database users using TDBJ.

Covers:

* [feat~dropping-existing-database-objects~2](#dropping-database-objects)

Needs: dsn

### Dropping Tables
`req~dropping-tables~1`

Users can drop tables using TDBJ.

Covers:

* [feat~dropping-existing-database-objects~2](#dropping-database-objects)

Needs: dsn

### Dropping Schemas
`req~dropping-schemas~1`

Users can drop schemas using TDBJ.

TDBJ uses the `CASCADE` option to drop contained tables.

Covers:

* [feat~dropping-existing-database-objects~2](#dropping-database-objects)

Needs: dsn

### Dropping Adapter Scripts
`req~dropping-adapter-scripts~1`

Users can drop adapter scripts using TDBJ.

Covers:

* [feat~dropping-existing-database-objects~2](#dropping-database-objects)

Needs: dsn

### Dropping Connections
`req~dropping-connections~1`

Users can drop connections using TDBJ.

Covers:

* [feat~dropping-existing-database-objects~2](#dropping-database-objects)

Needs: dsn

### Dropping Scripts
`req~dropping-scripts~1`

Users can drop scripts using TDBJ.

Covers:

* [feat~dropping-existing-database-objects~2](#dropping-database-objects)

Needs: dsn

### Dropping UDFs
`req~dropping-udfs~1`

Users can drop UDFs using TDBJ.

Covers:

* [feat~dropping-existing-database-objects~2](#dropping-database-objects)

Needs: dsn

### Dropping Virtual Schemas
`req~dropping-virtual-schemas~1`

Users can drop virtual schemas using TDBJ.

* TDBJ uses the `CASCADE` to also drop virtual schemas that contain tables.

Covers:

* [feat~dropping-existing-database-objects~2](#dropping-database-objects)

Needs: dsn

#### Executing Scripts Without Returns
`req~executing-scripts-without-returns~1`

### Populating the Database With Test Data

### Running Executable Database Content

#### Running Scripts That Have no Return
`req~running-scripts-that-have-no-return~1`

Users can execute scripts that have no return.

Rationale:

This allows testing scripts and using scripts for more complex test preparation.

Covers:

* [feat~running-executable-database-content~1](#running-executable-database-content)

Needs: dsn

### Controlling Existing Database Objects

In some integration tests users need to manipulate database objects that already exist in the database For example if they were created by your implementation and you need to modify them for a white-box test. Or if they are imported from a SQL file.

TDBJ lets users attach to existing objects to control them.

#### Controlling Existing Scripts
`req~controlling-existing-scripts~1`

Users can control an existing script through TDBJ.

Rationale:

Scripts that are part of the implementation are often loaded from files. In integration tests users need to be able to run those pre-existing scripts.

Covers:

* [feat~controlling-existing-database-objects~1](#controlling-existing-database-objects)

Needs: dsn
