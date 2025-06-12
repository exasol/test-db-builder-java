# Test Database Builder for Java 3.6.2, released 2025-06-12

Code name: Fixed vulnerability CVE-2025-49146 in org.postgresql:postgresql:jar:42.7.5:test

## Summary

This release fixes the following vulnerability:

### CVE-2025-49146 (CWE-287) in dependency `org.postgresql:postgresql:jar:42.7.5:test`
postgresql - Improper Authentication

#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2025-49146?component-type=maven&component-name=org.postgresql%2Fpostgresql&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-49146
* https://github.com/advisories/GHSA-hq9p-pm7w-8p54
* https://gitlab.com/gitlab-org/advisories-community/-/blob/main/maven/org.postgresql/postgresql/CVE-2025-49146.yml
* https://nvd.nist.gov/vuln/detail/CVE-2025-49146
* https://osv-vulnerabilities.storage.googleapis.com/Maven/GHSA-hq9p-pm7w-8p54.json

## Security

* #148: Fixed vulnerability CVE-2025-49146 in dependency `org.postgresql:postgresql:jar:42.7.5:test`

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.5` to `7.1.6`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.0` to `1.7.1`
* Updated `com.oracle.database.jdbc:ojdbc11:23.7.0.25.01` to `23.8.0.25.04`
* Updated `org.junit.jupiter:junit-jupiter-api:5.12.0` to `5.13.1`
* Updated `org.junit.jupiter:junit-jupiter-params:5.12.0` to `5.13.1`
* Updated `org.mockito:mockito-junit-jupiter:5.17.0` to `5.18.0`
* Updated `org.postgresql:postgresql:42.7.5` to `42.7.7`
* Updated `org.testcontainers:junit-jupiter:1.21.0` to `1.21.1`
* Updated `org.testcontainers:mysql:1.21.0` to `1.21.1`
* Updated `org.testcontainers:oracle-xe:1.21.0` to `1.21.1`
* Updated `org.testcontainers:postgresql:1.21.0` to `1.21.1`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.0.1` to `5.2.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.2` to `3.5.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.2` to `3.5.3`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.12` to `0.8.13`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.0.0.4389` to `5.1.0.4751`
* Added `org.sonatype.central:central-publishing-maven-plugin:0.7.0`
* Removed `org.sonatype.plugins:nexus-staging-maven-plugin:1.7.0`
