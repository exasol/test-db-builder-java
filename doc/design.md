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

### Creating Scripts
`dsn~creating-scripts~1`

Users can create script objects from inside schema objects.

Covers:

* `req~creating-scripts~1`

Needs: impl, itest

## Populating Database With Test Data

# Cross-cutting Concerns

# Design Decisions

# Quality Scenarios

# Risks
