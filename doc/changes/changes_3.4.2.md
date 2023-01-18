# Test Database Builder for Java 3.4.2, released 2023-01-18

Code name: Fix vulnerabilities in dependencies on top of 3.4.1

## Summary

Updated dependencies on top of version 3.4.1 to fix vulnerability CVE-2022-41946 in test dependency to `org.postgresql:postgresql:jar:42.5.0`.

## Bugfixes

* #110: Updated dependencies

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:7.1.11` to `7.1.17`
* Updated `com.exasol:exasol-testcontainers:6.2.0` to `6.5.0`
* Updated `com.google.protobuf:protobuf-java:3.21.8` to `3.21.12`
* Updated `com.oracle.database.jdbc:ojdbc11:21.7.0.0` to `21.8.0.0`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.10.1` to `3.12.3`
* Updated `org.junit-pioneer:junit-pioneer:1.7.1` to `1.9.1`
* Updated `org.junit.jupiter:junit-jupiter-api:5.9.1` to `5.9.2`
* Updated `org.junit.jupiter:junit-jupiter:5.9.1` to `5.9.2`
* Updated `org.mockito:mockito-junit-jupiter:4.8.1` to `5.0.0`
* Updated `org.postgresql:postgresql:42.5.0` to `42.5.1`
* Updated `org.testcontainers:junit-jupiter:1.17.5` to `1.17.6`
* Updated `org.testcontainers:mysql:1.17.5` to `1.17.6`
* Updated `org.testcontainers:oracle-xe:1.17.5` to `1.17.6`
* Updated `org.testcontainers:postgresql:1.17.5` to `1.17.6`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.1.2` to `1.2.1`
* Updated `com.exasol:project-keeper-maven-plugin:2.8.0` to `2.9.1`
* Updated `io.github.zlika:reproducible-build-maven-plugin:0.15` to `0.16`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.0.0-M1` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.0.0-M5` to `3.0.0-M7`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.4.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M5` to `3.0.0-M7`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.2.7` to `1.3.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.10.0` to `2.13.0`
