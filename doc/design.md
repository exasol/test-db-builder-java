# Introduction

## Acknowledgments

This document's section structure is derived from the "[arc42](https://arc42.org/)" architectural template by Dr. Gernot Starke, Dr. Peter Hruschka.

## Terms and Abbreviations

<dl>
    <dt>TDDB</dt><dd>Test Database Builder</dd>
    <dt>Top-level database object</dt><dd>Database object that has no parent scope in the database.</dd>
</dl>

# Constraints

This section introduces technical system constraints.

# Solution Strategy

TODO: add

## Requirement Overview

Please refer to the [System Requirement Specification](system_requirements.md) for user-level requirements.

# Building Blocks

This section introduces the building blocks of the software. Together those building blocks make up the big picture of the software structure.

## `DatabaseObjectFactory`
`dsn~database-object-factory~1`

The `DatabaseObjectFactory` is the factory for top-level database objects.

Needs: impl

# Runtime

This section describes the runtime behavior of the software.

## Creating Database Objects

### Creating Database Users
`dsn~creating-database-users~1`

Users can create database users by providing a username and a password.

Covers:

* `req~creating-users~1`

Needs: impl, utest, itest

### Granting System Privileges to Database Users
`dsn~granting-system-privileges-to-database-users~1`

Users can grant System Privileges to created database users.

Covers:

* `req~creating-users~1`

Needs: impl, utest, itest

### Creating Scripts
`dsn~creating-scripts~1`

Users can create script objects from inside schema objects by providing the script content programmatically.

Covers:

* `req~creating-scripts~1`

Needs: impl, itest

### Creating Scripts from Files
`dsn~creating-scripts-from-files~1`

Users can create scripts from inside a schema object by loading the script contents from a file.

Covers:

* `req~creating-scripts-from-files~1`

Needs: impl, itest

### Creating Object Through SQL Files
`dsn~creating-objects-through-sql-files~1`

Users can create database objects by executing the statements in an SQL file. 

Covers:

* `req~creating-objects-through-sql-files~1`

Needs: impl, itest

## Populating Database With Test Data

## Running Executable Database Content

### Running Scripts That Have no Return
`dsn~running-scripts-that-have-no-return~1`

Users can execute scripts directly from a script Java object.

Covers:

* `req~running-scripts-that-have-no-return~1`

Needs: impl, itest

## Controlling Existing Database Objects

### Controlling Existing Scripts
`dsn~controlling-existing-scripts~1`

Users get a control object for a script from within a schema by specifying the existing scripts name.

Covers:

* `req~controlling-existing-scripts~1`
* `dsn~attaching-to-existing-database-object-by-name~1`

Needs: impl, itest

# Cross-cutting Concerns

# Design Decisions

## How Do we Let Users Attach to Existing Database Object

As explained in [the system requirements section "Controlling Existing Database Objects"](system_requirements#Controlling-existing-database-objects), sometimes users need to control existing database objects in integration tests.

The decision on how to do this is architecture relevant because it impacts usability, code maintainability (both of the TDDB and integration tests written with it) and safety of the created code.

### Alternatives Considered

#### Creating a Complete Control Object but not Writing it

While this gives TDDB the best possible information about the contents of the object and also works without administrative privileges for all object types, it requires users to provide the same information twice, which is inconvenient and error prone.

#### Reading the Object Metadata from the Database

Depending on the type of the object this can require administrative privileges. It is also a complex operation that generates a lot of development effort in the TDDB. We still will keep this in mind as a backup plan for selected object types when the current simple solution turns out to be insufficient.

### Decisions

#### Attaching to Existing Database Object by Name
`dsn~attaching-to-existing-database-object-by-name~1`

When users attach to an existing database object, all they need is a fully-qualified name. The control object holds no metadata of that object.

Comment:

The downside of this approach is that TDDB cannot do any validation on an object that it only knows the name of. For this metadata would be required. This means for example that TDDB cannot check whether the number of arguments passed to a script is in line with the script definition.

This is not necessary though since the database itself implements those checks and the error messages are good enough to present them to the users.

On the upside this makes the implementation comparably simple and using the API convenient.

Covers:

* `req~controlling-existing-scripts~1`

Needs: dsn

# Quality Scenarios

# Risks
