# Test Database Builder for Java 4.0.1, released 2026-??-??

Code name: Fixed vulnerability CVE-2026-42198 in org.postgresql:postgresql:jar:42.7.10:test

## Summary

This release fixes the following vulnerability:

### CVE-2026-42198 (CWE-770) in dependency `org.postgresql:postgresql:jar:42.7.10:test`
pgjdbc is an open source postgresql JDBC Driver. From version 42.2.0 to before version 42.7.11, pgjdbc is vulnerable to a client-side denial of service during SCRAM-SHA-256 authentication. A malicious server can instruct the driver to perform SCRAM authentication with a very large iteration count. With a large enough value, the client spends an unbounded amount of CPU time inside PBKDF2 before authentication can fail. A single attempt ties up a CPU core. Repeated or concurrent attempts exhaust client CPU and can wedge connection pools. In affected versions, loginTimeout did not fully mitigate this problem. When loginTimeout expired, the caller could stop waiting, but the worker thread performing the connection attempt could continue running and burning CPU inside the SCRAM PBKDF2 computation. This issue has been patched in version 42.7.11.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-42198?component-type=maven&component-name=org.postgresql%2Fpostgresql&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-42198
* https://github.com/advisories/GHSA-98qh-xjc8-98pq

## Security

* #156: Fixed vulnerability CVE-2026-42198 in dependency `org.postgresql:postgresql:jar:42.7.10:test`

## Dependency Updates

### Test Dependency Updates

* Updated `com.mysql:mysql-connector-j:9.6.0` to `9.7.0`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.19.4` to `4.5`
* Updated `org.junit.jupiter:junit-jupiter-params:5.14.3` to `6.0.3`
* Updated `org.postgresql:postgresql:42.7.10` to `42.7.11`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.4.6` to `5.6.1`
