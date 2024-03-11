# Test Database Builder for Java 3.5.4, released 2024-03-11

Code name: Fix CVE-2024-25710, CVE-2024-1597. CVE-2024-26308

## Summary

This is a security release in which we updated test dependencies `commons-compress` and `postgresql` to fix the following CVEs:

### CVE-2024-25710

Loop with Unreachable Exit Condition ('Infinite Loop') vulnerability in Apache Commons Compress.This issue affects Apache Commons Compress: from 1.3 through 1.25.0.

Users are recommended to upgrade to version 1.26.0 which fixes the issue.

#### References

* https://ossindex.sonatype.org/vulnerability/CVE-2024-25710?component-type=maven&component-name=org.apache.commons%2Fcommons-compress&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-25710
* https://lists.apache.org/thread/cz8qkcwphy4cx8gltn932ln51cbtq6kf

### CVE-2024-1597

pgjdbc, the PostgreSQL JDBC Driver, allows attacker to inject SQL if using PreferQueryMode=SIMPLE. Note this is not the default. In the default mode there is no vulnerability. A placeholder for a numeric value must be immediately preceded by a minus. There must be a second placeholder for a string value after the first placeholder; both must be on the same line. By constructing a matching string payload, the attacker can inject SQL to alter the query,bypassing the protections that parameterized queries bring against SQL Injection attacks. Versions before 42.7.2, 42.6.1, 42.5.5, 42.4.4, 42.3.9, and 42.2.8 are affected.

#### References

- https://ossindex.sonatype.org/vulnerability/CVE-2024-1597?component-type=maven&component-name=org.postgresql%2Fpostgresql&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
- http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-1597
- https://github.com/pgjdbc/pgjdbc/security/advisories/GHSA-24rp-q3w6-vc56
- https://systemweakness.com/critical-vulnerability-in-postgresql-jdbc-driver-understanding-cve-2024-1597-18eec3bd2eaf

### CVE-2024-26308

Allocation of Resources Without Limits or Throttling vulnerability in Apache Commons Compress.This issue affects Apache Commons Compress: from 1.21 before 1.26.

Users are recommended to upgrade to version 1.26, which fixes the issue.

#### References

- https://ossindex.sonatype.org/vulnerability/CVE-2024-26308?component-type=maven&component-name=org.apache.commons%2Fcommons-compress&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
- http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-26308
- https://lists.apache.org/thread/ch5yo2d21p7vlqrhll9b17otbyq4npfg
- https://www.openwall.com/lists/oss-security/2024/02/19/2

## Dependency Updates

### Compile Dependency Updates

* Removed `org.slf4j:slf4j-jdk14:2.0.9`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.6.2` to `7.0.1`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.0` to `1.6.5`
* Updated `com.mysql:mysql-connector-j:8.1.0` to `8.3.0`
* Updated `com.oracle.database.jdbc:ojdbc11:23.2.0.0` to `23.3.0.23.09`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.15.2` to `3.15.8`
* Updated `org.junit-pioneer:junit-pioneer:2.1.0` to `2.2.0`
* Added `org.junit.jupiter:junit-jupiter-api:5.10.2`
* Added `org.junit.jupiter:junit-jupiter-engine:5.10.2`
* Removed `org.junit.jupiter:junit-jupiter:5.9.3`
* Updated `org.mockito:mockito-junit-jupiter:5.5.0` to `5.11.0`
* Updated `org.postgresql:postgresql:42.6.0` to `42.7.2`
* Added `org.slf4j:slf4j-jdk14:2.0.12`
* Updated `org.testcontainers:junit-jupiter:1.19.0` to `1.19.7`
* Updated `org.testcontainers:mysql:1.19.0` to `1.19.7`
* Updated `org.testcontainers:oracle-xe:1.19.0` to `1.19.7`
* Updated `org.testcontainers:postgresql:1.19.0` to `1.19.7`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.3.0` to `2.0.0`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.12` to `4.1.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.11.0` to `3.12.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.4.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.1.2` to `3.2.5`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.5.0` to `3.6.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.1.2` to `3.2.5`
* Added `org.apache.maven.plugins:maven-toolchains-plugin:3.1.0`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.5.0` to `1.6.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.0` to `2.16.2`
* Updated `org.itsallcode:openfasttrace-maven-plugin:1.6.1` to `1.8.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.10` to `0.8.11`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184` to `3.10.0.2594`
