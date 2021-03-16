# Test Database Builder 3.1.1, released 2021-03-16

Code name: Fixed SQL injection for create table

## Summary

In this released we fixed a bug that allowed SQL injection in the create table commands.

## Bugfixes

* #62: Fixed identifier quoting for PostgreSQL and MySQL