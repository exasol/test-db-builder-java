# System Requirement Specification Exasol Test Container

## Introduction

Exasol's Test Database Builder for Java (short "TDDB") is a library that helps authors of integration test against databases writing compact and readable test code.

**&#9888; Important:** The TDDB is meant to be used in tests only. It is neither designed nor particularly suited to be used in production scenarios!

## About This Document

### Target Audience

The target audience are end-users, requirement engineers, software designers and quality assurance. See section ["Stakeholders"](#stakeholders) for more details.

### Goal

TDDB It is targeted at functional testing mainly with the central design goal to make writing and reading the test code as convenient as possible for developers.

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

## Functional Requirements

In this section lists functional requirements from the user's perspective. The requirements are grouped by feature where they belong to a single feature.

### Creating Scripts
`req~creating-scripts~1`

TDDB creates Scripts.

Rationale:

(Lua) scripts are the native way of extending the Exasol database.

Covers:

* [feat~creating-database-objects~1](#creating-database-objects)

Needs: dsn

### Creating Database Objects

### Populating the Database With Test Data
