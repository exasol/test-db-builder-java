# Test Database Builder for Java 5.0.0, released 2026-10-30

Code name: Fixed vulnerabilities CVE-2026-107314, CVE-2026-107315

## Summary

This release fixes the following 2 vulnerabilities.

## Breaking Changes

* This projects requires Java 17 for building and running.
* The following deprecated APIs were removed:
  * Enum value `AdapterScript.Language.PYTHON`. Use `AdapterScript.Language.PYTHON3` instead.
  * Enum value `UdfScript.Language.PYTHON`. Use `UdfScript.Language.PYTHON3` instead.

## Details on Fixed Vulnerabilities

### CVE-2026-107314 (CWE-636) in dependency `org.postgresql:postgresql:jar:42.7.13:test`
pgjdbc, the PostgreSQL JDBC Driver, versions 42.7.11 through 42.7.13 enforce no restriction when the requireAuth connection property excludes all six authentication methods the driver knows, for example requireAuth=!password,!md5,!gss,!sspi,!scram-sha-256,!none. The driver then accepts any method the server asks for, including cleartext password authentication. A value without a method in it, such as requireAuth=, (a single comma), is affected the same way. An attacker positioned between the application and its server can ask for cleartext password authentication and receive the database password. A positive list such as requireAuth=scram-sha-256, and a partial exclusion such as requireAuth=!password,!md5, are enforced correctly. The property has no default value, so a deployment that does not set it is not affected. 42.7.14 fixes the problem: such a connection is refused with SQLState 08004, and a value without a method in it is rejected as invalid.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-107314?component-type=maven&component-name=org.postgresql%2Fpostgresql&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-107314
* https://github.com/pgjdbc/pgjdbc/security/advisories/GHSA-rhp9-mr79-r74h

### CVE-2026-107315 (CWE-201) in dependency `org.postgresql:postgresql:jar:42.7.13:test`
pgjdbc, the PostgreSQL JDBC Driver, versions 42.7.4 through 42.7.13 pads a value that is shorter than its declared length with bytes left in its send buffer instead of zeros, and the server stores those bytes as part of the value. The bytes are messages the driver sent earlier on the same connection: SQL text and parameter values of recent statements, which on a pooled connection can come from other requests. Each padded value can carry up to 8192 bytes of this traffic, or 16320 bytes on a connection with GSS encryption. The padding happens when an application declares a length larger than the data it supplies, through PreparedStatement.setObject with a ByteStreamWriter, CopyIn.writeToCopy, PGCopyOutputStream.write, LargeObject.write, or Blob.setBytes. The driver accepts these calls without an error. An attacker who can make the application store such a value and read it back can collect earlier traffic. Applications whose declared lengths always match their data are not affected. Versions 42.7.3 and earlier pad with zeros.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-107315?component-type=maven&component-name=org.postgresql%2Fpostgresql&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-107315
* https://github.com/pgjdbc/pgjdbc/security/advisories/GHSA-f64h-wr5q-3qf3

## Security

* #171: Fixed vulnerability CVE-2026-107314 in dependency `org.postgresql:postgresql:jar:42.7.13:test`
* #172: Fixed vulnerability CVE-2026-107315 in dependency `org.postgresql:postgresql:jar:42.7.13:test`

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:26.2.9` to `26.2.10`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.3` to `1.7.4`
* Updated `com.mysql:mysql-connector-j:26.7.0` to `26.10.0`
* Removed `com.oracle.database.jdbc:ojdbc11:23.26.3.0.0`
* Added `com.oracle.database.jdbc:ojdbc17:23.26.4.0.0`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.19.4` to `4.5.2`
* Updated `org.junit.jupiter:junit-jupiter-params:5.14.4` to `6.1.3`
* Updated `org.mockito:mockito-junit-jupiter:5.23.0` to `5.24.0`
* Updated `org.postgresql:postgresql:42.7.13` to `42.7.14`
* Updated `org.slf4j:slf4j-jdk14:2.0.19` to `2.0.20`
* Added `org.testcontainers:testcontainers-oracle-free:2.0.5`
* Removed `org.testcontainers:testcontainers-oracle-xe:2.0.5`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.1.1` to `2.1.3`
* Updated `com.exasol:project-keeper-maven-plugin:5.7.5` to `5.8.2`
* Updated `org.apache.maven.plugins:maven-artifact-plugin:3.6.1` to `3.7.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.15.0` to `3.16.0`
* Added `org.apache.maven.plugins:maven-dependency-plugin:3.11.0`
* Updated `org.apache.maven.plugins:maven-deploy-plugin:3.1.4` to `3.2.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.6` to `3.6.1`
* Updated `org.apache.maven.plugins:maven-install-plugin:3.1.4` to `3.2.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.6` to `3.6.1`
* Updated `org.codehaus.mojo:build-helper-maven-plugin:3.6.1` to `3.6.2`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.21.0` to `2.23.0`
* Updated `org.itsallcode:openfasttrace-maven-plugin:3.0.0` to `3.1.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.15` to `0.8.16`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.7.0.6970` to `5.8.0.7211`
* Updated `org.sonatype.central:central-publishing-maven-plugin:0.11.0` to `0.12.0`
