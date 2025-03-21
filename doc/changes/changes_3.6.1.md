# Test Database Builder for Java 3.6.1, released 2025-??-??

Code name: Fixed vulnerability CVE-2024-55551 in com.exasol:exasol-jdbc:jar:24.1.2:test

## Summary

This release fixes the following vulnerability:

### CVE-2024-55551 (CWE-94) in dependency `com.exasol:exasol-jdbc:jar:24.1.2:test`
An issue was discovered in Exasol jdbc driver 24.2.0. Attackers can inject malicious parameters into the JDBC URL, triggering JNDI injection during the process when the JDBC Driver uses this URL to connect to the database. This can further lead to remote code execution vulnerability.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-55551?component-type=maven&component-name=com.exasol%2Fexasol-jdbc&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-55551
* https://gist.github.com/azraelxuemo/9565ec9219e0c3e9afd5474904c39d0f

## Security

* #145: Fixed vulnerability CVE-2024-55551 in dependency `com.exasol:exasol-jdbc:jar:24.1.2:test`

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:24.1.2` to `25.2.2`
* Updated `com.exasol:exasol-testcontainers:7.1.1` to `7.1.4`
* Updated `com.google.protobuf:protobuf-java:4.28.2` to `4.30.1`
* Updated `com.mysql:mysql-connector-j:9.0.0` to `9.2.0`
* Updated `com.oracle.database.jdbc:ojdbc11:23.5.0.24.07` to `23.7.0.25.01`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.16.2` to `3.19.2`
* Updated `org.junit-pioneer:junit-pioneer:2.2.0` to `2.3.0`
* Updated `org.junit.jupiter:junit-jupiter-api:5.11.0` to `5.12.1`
* Updated `org.junit.jupiter:junit-jupiter-engine:5.11.0` to `5.12.1`
* Updated `org.mockito:mockito-junit-jupiter:5.13.0` to `5.16.1`
* Updated `org.postgresql:postgresql:42.7.4` to `42.7.5`
* Updated `org.slf4j:slf4j-jdk14:2.0.16` to `2.0.17`
* Updated `org.testcontainers:junit-jupiter:1.20.1` to `1.20.6`
* Updated `org.testcontainers:mysql:1.20.1` to `1.20.6`
* Updated `org.testcontainers:oracle-xe:1.20.1` to `1.20.6`
* Updated `org.testcontainers:postgresql:1.20.1` to `1.20.6`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:4.3.3` to `5.0.0`
