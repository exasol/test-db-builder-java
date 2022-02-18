# Test Database Builder for Java 3.3.1, released 2022-02-18

Code name: Added Oracle support

## Summary

This release fixes [CVE-2022-21724](https://nvd.nist.gov/vuln/detail/CVE-2022-21724) in the PostgreSQL dependency. This CVE only affected users who used TDDB for PostgreSQL databases.

## Bugfixes

* #92: Fixed Dependabot finding by upgrading to PostgreSQL 42.3.3

## Dependency Updates

### Compile Dependency Updates

* Updated `com.oracle.database.jdbc:ojdbc11:21.4.0.0.1` to `21.5.0.0`

### Runtime Dependency Updates

* Updated `org.postgresql:postgresql:42.3.2` to `42.3.3`

### Test Dependency Updates

* Updated `nl.jqno.equalsverifier:equalsverifier:3.8.2` to `3.9`
* Updated `org.mockito:mockito-junit-jupiter:4.2.0` to `4.3.1`
* Updated `org.testcontainers:junit-jupiter:1.16.2` to `1.16.3`
* Updated `org.testcontainers:mysql:1.16.2` to `1.16.3`
* Updated `org.testcontainers:postgresql:1.16.2` to `1.16.3`

### Plugin Dependency Updates

* Updated `io.github.zlika:reproducible-build-maven-plugin:0.13` to `0.14`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.0.0-M3` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-gpg-plugin:1.6` to `3.0.1`
* Updated `org.itsallcode:openfasttrace-maven-plugin:1.0.0` to `1.2.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.6` to `0.8.7`
