# System Requirement Specification Exasol Test Container

## Introduction

Exasol's Test Database Builder for Java (short "TDDB") is a library that helps authors of integration test against databases writing compact and readable test code.

**&#9888; Important:** The TDDB is meant to be used in tests only. It is neither designed nor particularly suited to be used in production scenarios!

## About This Document

### Target Audience

The target audience are end-users, requirement engineers, software designers and quality assurance. See section ["Stakeholders"](#stakeholders) for more details.

### Goal

TDDB is targeted at functional testing mainly with the central design goal to make writing and reading the test code as convenient as possible for developers.

### Quality Goals

TDDB's main quality goals are in descending order of importance:

1. Compact test code
1. Readable test code

## Stakeholders

### Integrators

Integrators integrate their solution with databases (like Exasol). To test this, they need to create objects in those databases and populate them with test data.

### Terms and Abbreviations

The following list gives you an overview of terms and abbreviations commonly used in TDDB documents.

* ...

## Features

Features are the highest level requirements in this document that describe the main functionality of TDDB.

### Creating Database Objects
`feat~creating-database-objects~1`

TDDB allows developers to programmatically create database objects.

Needs: req

### Populating Database With Test Data
`feat~populating-database-with-test-data~1`

TDDB allows populating the database with test data.

<!-- Needs: req --><!-- See https://github.com/exasol/test-db-builder-java/issues/10 -->

### Running Executable Database Content
`feat~running-executable-database-content~1`

TDDB allows developers to programmatically running database content that is executable (like scripts).

Needs: req

### Controlling Existing Database Objects
`feat~controlling-existing-database-objects~1`

TDDB allows developers to attach to existing database objects to control them programmatically.

Needs: req

## Functional Requirements

In this section lists functional requirements from the user's perspective. The requirements are grouped by feature where they belong to a single feature.

### Creating Database Objects

#### Creating Scripts
`req~creating-scripts~1`

Users can create scripts where the script content is as part of the test code.

Rationale:

(Lua) scripts are the native way of extending the Exasol database.

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

TDDB lets users attach to existing objects to control them.

#### Controlling Existing Scripts
`req~controlling-existing-scripts~1`

Users can control an existing script through TDDB.

Rationale:

Scripts that are part of the implementation are often loaded from files. In integration tests users need to be able to run those pre-existing scripts.

Covers:

* [feat~controlling-existing-database-objects~1](#controlling-existing-database-objects)

Needs: dsn
