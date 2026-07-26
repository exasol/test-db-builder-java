# Test Database Builder for Java 4.0.2, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-60586, CVE-2026-60623, CVE-2026-60624, CVE-2026-61082

## Summary

This release fixes the following 4 vulnerabilities:

### CVE-2026-60586 (CWE-200) in dependency `com.mysql:mysql-connector-j:jar:9.7.0:test`
Vulnerability in the MySQL Connectors product of Oracle MySQL (component: Connector/J).  Supported versions that are affected are 9.7.0-9.7.1. Easily exploitable vulnerability allows low privileged attacker with network access via multiple protocols to compromise MySQL Connectors.  While the vulnerability is in MySQL Connectors, attacks may significantly impact additional products (scope change).  Successful attacks of this vulnerability can result in  unauthorized access to critical data or complete access to all MySQL Connectors accessible data. CVSS 3.1 Base Score 7.7 (Confidentiality impacts).  CVSS Vector: (CVSS:3.1/AV:N/AC:L/PR:L/UI:N/S:C/C:H/I:N/A:N).
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-60586?component-type=maven&component-name=com.mysql%2Fmysql-connector-j&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-60586
* https://www.oracle.com/security-alerts/cpujul2026.html

### CVE-2026-60623 (CWE-668) in dependency `com.mysql:mysql-connector-j:jar:9.7.0:test`
Vulnerability in the MySQL Connectors product of Oracle MySQL (component: Connector/J).  Supported versions that are affected are 9.7.0-9.7.1. Difficult to exploit vulnerability allows low privileged attacker with network access via multiple protocols to compromise MySQL Connectors.  Successful attacks of this vulnerability can result in  unauthorized creation, deletion or modification access to critical data or all MySQL Connectors accessible data as well as  unauthorized access to critical data or complete access to all MySQL Connectors accessible data and unauthorized ability to cause a partial denial of service (partial DOS) of MySQL Connectors. CVSS 3.1 Base Score 7.1 (Confidentiality, Integrity and Availability impacts).  CVSS Vector: (CVSS:3.1/AV:N/AC:H/PR:L/UI:N/S:U/C:H/I:H/A:L).
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-60623?component-type=maven&component-name=com.mysql%2Fmysql-connector-j&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-60623
* https://www.oracle.com/security-alerts/cpujul2026.html

### CVE-2026-60624 (CWE-400) in dependency `com.mysql:mysql-connector-j:jar:9.7.0:test`
Vulnerability in the MySQL Connectors product of Oracle MySQL (component: Connector/J).  Supported versions that are affected are 9.7.0-9.7.1. Easily exploitable vulnerability allows unauthenticated attacker with network access via multiple protocols to compromise MySQL Connectors.  Successful attacks require human interaction from a person other than the attacker. Successful attacks of this vulnerability can result in unauthorized ability to cause a hang or frequently repeatable crash (complete DOS) of MySQL Connectors. CVSS 3.1 Base Score 6.5 (Availability impacts).  CVSS Vector: (CVSS:3.1/AV:N/AC:L/PR:N/UI:R/S:U/C:N/I:N/A:H).
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-60624?component-type=maven&component-name=com.mysql%2Fmysql-connector-j&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-60624
* https://www.oracle.com/security-alerts/cpujul2026.html

### CVE-2026-61082 (CWE-200) in dependency `com.mysql:mysql-connector-j:jar:9.7.0:test`
Vulnerability in the MySQL Connectors product of Oracle MySQL (component: Connector/J).  Supported versions that are affected are 9.7.0-9.7.1. Easily exploitable vulnerability allows unauthenticated attacker with network access via multiple protocols to compromise MySQL Connectors.  Successful attacks require human interaction from a person other than the attacker. Successful attacks of this vulnerability can result in  unauthorized access to critical data or complete access to all MySQL Connectors accessible data. CVSS 3.1 Base Score 6.5 (Confidentiality impacts).  CVSS Vector: (CVSS:3.1/AV:N/AC:L/PR:N/UI:R/S:U/C:H/I:N/A:N).
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-61082?component-type=maven&component-name=com.mysql%2Fmysql-connector-j&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-61082
* https://www.oracle.com/security-alerts/cpujul2026.html

## Security

* #163: Fixed vulnerability CVE-2026-60586 in dependency `com.mysql:mysql-connector-j:jar:9.7.0:test`
* #164: Fixed vulnerability CVE-2026-60623 in dependency `com.mysql:mysql-connector-j:jar:9.7.0:test`
* #165: Fixed vulnerability CVE-2026-60624 in dependency `com.mysql:mysql-connector-j:jar:9.7.0:test`
* #166: Fixed vulnerability CVE-2026-61082 in dependency `com.mysql:mysql-connector-j:jar:9.7.0:test`

## Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-jdbc:26.2.7` to `26.2.8`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.19.4` to `4.5`
* Updated `org.junit.jupiter:junit-jupiter-params:5.14.4` to `6.1.2`
* Updated `org.postgresql:postgresql:42.7.11` to `42.7.13`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.6.2` to `5.7.4`
