# Introduction

## Acknowledgments

This document's section structure is derived from the "[arc42](https://arc42.org/)" architectural template by Dr. Gernot Starke, Dr. Peter Hruschka.

## Terms and Abbreviations

<dl>
    <dt>TDBJ</dt><dd>Test Database Builder for Java</dd>
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

## `ExasolObjectFactory`

`dsn~exasol-object-factory~1`

The `ExasolObjectFactory` is a factory that allows users to create Exasol-specific database objects.

Covers:

* `req~supporting-exasol-database~1`

Needs: impl, itest

## `MySqlObjectFactory`

`dsn~mysql-object-factory~1`

The `MySqlObjectFactory` is a factory that allows users to create MySQL-specific database objects.

Covers:

* `req~supporting-mysql-database~1`

Needs: impl, itest

# Runtime

This section describes the runtime behavior of the software.

## Creating Database Objects

### Creating Database Users

`dsn~creating-database-users~1`

Users can create database users by providing a username and a password.

Covers:

* `req~creating-users~1`

Needs: impl, utest, itest

### Creating Adapter Scripts

`dsn~creating-adapter-scripts~1`

Users can create Exasol adapter scripts.

Covers:

* `req~creating-adapter-scripts~1`

Needs: impl, utest, itest

### Creating Exasol Objects With JVM Options

`dsn~creating-exasol-java-object-with-jvm-options~1`

Users can create virtual schema adapters and UDFs with JVM options. They can set these options globally and TDBJ will add them to all JAVA adapters and scripts.

Rationale: By that users can for example add a debugger or profiler to all JAVA scripts.

Covers:

* `req~creating-adapter-scripts~1`

Needs: impl, utest

### Creating Connection

`dsn~creating-connections~1`

Users can create Exasol connection definitions.

Covers:

* `req~creating-connections~1`

Needs: impl, utest, itest

### Creating Schemas

`dsn~creating-schemas~1`

Users can create dialect specific schemas.

Covers:

* `req~creating-schemas~1`

Needs: impl, utest, itest

### Creating Virtual Schemas

`dsn~creating-virtual-schemas~1`

Users can create exasol virtual schemas.

Covers:

* `req~creating-virtual-schemas~1`

Needs: impl, utest, itest

### Creating Tables

`dsn~creating-tables~1`

Users can create tables.

Covers:

* `req~creating-tables~1`

Needs: impl, utest, itest

### Granting System Privileges to Database Users

`dsn~granting-system-privileges-to-database-users~1`

Users can select and grant System Privileges to created database users from the list of supported System Privileges.

Covers:

* `req~granting-system-privileges-to-users~1`

Needs: impl, utest, itest

### Creating Scripts

`dsn~creating-scripts~1`

Users can create script objects from inside schema objects by providing the script content programmatically.

Covers:

* `req~creating-scripts~1`

Needs: impl, itest

### Creating UDFs

`dsn~creating-udfs~1`

Users can create UDF objects from inside schema objects by providing the script from file.

Covers:

* `req~creating-udf~1`

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

## Dropping Database Objects

### Dropping Database Objects via `AutoClosable`

`dsn~dropping-objects-via-AutoClosable~1`

All database objects implement the `AutoClosable` interface.

Rationale:
This allows users to automatically delete objects using Java's try-with-resources.

Needs: impl, utest

### Dropping Users

`dsn~dropping-users~1`

Users can drop database users using TDBJ.

Covers:

* `req~dropping-users~1`

Needs: impl, itest

### Dropping Tables

`dsn~dropping-tables~1`

Users can drop tables using TDBJ.

Covers:

* `req~dropping-tables~1`

Needs: impl, itest

### Dropping Schemas

`dsn~dropping-schemas~2`

Users can drop schemas using TDBJ.

TDBJ uses the `CASCADE` option to drop contained tables.

Covers:

* `req~dropping-schemas~1`

Needs: impl, itest

### Dropping Adapter Scripts

`dsn~dropping-adapter-scripts~1`

Users can drop adapter scripts using TDBJ.

Covers:

* `req~dropping-adapter-scripts~1`

Needs: impl, itest

### Dropping Connections

`dsn~dropping-connections~1`

Users can drop connections using TDBJ.

Covers:

* `req~dropping-connections~1`

Needs: impl, itest

### Dropping Scripts

`dsn~dropping-scripts~1`

Users can drop scripts using TDBJ.

Covers:

* `req~dropping-scripts~1`

Needs: impl, itest

### Dropping UDFs

`dsn~dropping-udfs~1`

Users can drop UDFs using TDBJ.

Covers:

* `req~dropping-udfs~1`

Needs: impl, itest

### Dropping Virtual Schemas

`dsn~dropping-virtual-schemas~2`

Users can drop virtual schemas using TDBJ.

TDBJ uses the `CASCADE` to also drop virtual schemas that contain tables.

Covers:

* `req~dropping-virtual-schemas~1`

Needs: impl, itest

# Cross-cutting Concerns

# Design Decisions

## How Do we Let Users Attach to Existing Database Object

As explained in [the system requirements section "Controlling Existing Database Objects"](system_requirements.md#Controlling-existing-database-objects), sometimes users need to control existing database objects in integration tests.

The decision on how to do this is architecture relevant because it impacts usability, code maintainability (both of the TDBJ and integration tests written with it) and safety of the created code.

### Alternatives Considered

#### Creating a Complete Control Object but not Writing it

While this gives TDBJ the best possible information about the contents of the object and also works without administrative privileges for all object types, it requires users to provide the same information twice, which is inconvenient and error prone.

#### Reading the Object Metadata from the Database

Depending on the type of the object this can require administrative privileges. It is also a complex operation that generates a lot of development effort in the TDBJ. We still will keep this in mind as a backup plan for selected object types when the current simple solution turns out to be insufficient.

### Decisions

#### Attaching to Existing Database Object by Name

`dsn~attaching-to-existing-database-object-by-name~1`

When users attach to an existing database object, all they need is a fully-qualified name. The control object holds no metadata of that object.

Comment:

The downside of this approach is that TDBJ cannot do any validation on an object that it only knows the name of. For this metadata would be required. This means for example that TDBJ cannot check whether the number of arguments passed to a script is in line with the script definition.

This is not necessary though since the database itself implements those checks and the error messages are good enough to present them to the users.

On the upside this makes the implementation comparably simple and using the API convenient.

Covers:

* `req~controlling-existing-scripts~1`

Needs: dsn

# Quality Scenarios

# Risks
