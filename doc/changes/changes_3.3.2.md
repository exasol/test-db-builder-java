# Test Database Builder for Java 3.3.2, released 2022-??-??

Code name: Upgrade dependencies

This release upgrades dependencies and reduces the number of runtime dependencies, fixing [CVE-2022-21724](https://ossindex.sonatype.org/vulnerability/0f319d1b-e964-4471-bded-db3aeb3c3a29?component-type=maven&component-name=org.postgresql.postgresql&utm_source=ossindex-client&utm_medium=integration&utm_content=1.1.1) in the PostgreSQL JDBC driver.

## Bugfixes

* #97: Fixed security issue in dependencies

## Dependency Updates

### Compile Dependency Updates

* Removed `com.oracle.database.jdbc:ojdbc11:21.5.0.0`

### Runtime Dependency Updates

* Removed `com.exasol:exasol-jdbc:7.1.4`
* Removed `mysql:mysql-connector-java:8.0.28`
* Removed `org.postgresql:postgresql:42.3.3`

### Test Dependency Updates

* Added `com.exasol:exasol-jdbc:7.1.7`
* Updated `com.exasol:exasol-testcontainers:6.0.0` to `6.1.1`
* Added `com.oracle.database.jdbc:ojdbc11:21.5.0.0`
* Added `mysql:mysql-connector-java:8.0.28`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.9` to `3.10`
* Updated `org.mockito:mockito-junit-jupiter:4.3.1` to `4.5.1`
* Added `org.postgresql:postgresql:42.3.4`
* Updated `org.testcontainers:junit-jupiter:1.16.3` to `1.17.1`
* Updated `org.testcontainers:mysql:1.16.3` to `1.17.1`
* Updated `org.testcontainers:oracle-xe:1.16.2` to `1.17.1`
* Updated `org.testcontainers:postgresql:1.16.3` to `1.17.1`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:0.7.1` to `1.1.0`
* Updated `com.exasol:project-keeper-maven-plugin:1.3.4` to `2.3.1`
* Updated `io.github.zlika:reproducible-build-maven-plugin:0.14` to `0.15`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.8.1` to `3.9.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:2.7` to `3.0.0-M1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M4` to `3.0.0-M5`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M4` to `3.0.0-M5`
* Added `org.codehaus.mojo:flatten-maven-plugin:1.2.7`
* Updated `org.itsallcode:openfasttrace-maven-plugin:1.2.0` to `1.5.0`
* Added `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184`
