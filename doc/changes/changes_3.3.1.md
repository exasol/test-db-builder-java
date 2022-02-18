# Test Database Builder for Java 3.3.1, released 2022-02-18

Code name: Added Oracle support

## Summary

This release fixes [CVE-2022-21724](https://nvd.nist.gov/vuln/detail/CVE-2022-21724) in the PostgreSQL dependency. This CVE only affected users who used TDDB for PostgreSQL databases.

## Bugfixes

* #92: Fixed Dependabot finding by upgrading to PostgreSQL 42.3.3

## Dependency Updates

### Runtime Dependency Updates

* Updated `org.postgresql:postgresql:42.3.2` to `42.3.3`
