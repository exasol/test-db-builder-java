# Test Database Builder for Java 3.3.2, released 2022-??-??

Code name: Upgrade dependencies

This release upgrades dependencies and reduces the number of runtime dependencies, fixing [CVE-2022-21724](https://ossindex.sonatype.org/vulnerability/0f319d1b-e964-4471-bded-db3aeb3c3a29?component-type=maven&component-name=org.postgresql.postgresql&utm_source=ossindex-client&utm_medium=integration&utm_content=1.1.1) in the PostgreSQL JDBC driver.

## Dependency Updates

### Compile Dependency Updates

* Removed `com.oracle.database.jdbc:ojdbc11:21.5.0.0`

### Runtime Dependency Updates

* Removed `com.exasol:exasol-jdbc:7.1.4`
* Removed `mysql:mysql-connector-java:8.0.28`
* Removed `org.postgresql:postgresql:42.3.3`

### Test Dependency Updates

* Added `com.exasol:exasol-jdbc:7.1.4`
* Added `com.oracle.database.jdbc:ojdbc11:21.5.0.0`
* Added `mysql:mysql-connector-java:8.0.28`
* Added `org.postgresql:postgresql:42.3.3`
