<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                 | License          |
| ------------------------------------------ | ---------------- |
| [Exasol Database fundamentals for Java][0] | [MIT License][1] |
| [error-reporting-java][2]                  | [MIT License][3] |

## Test Dependencies

| Dependency                                      | License                                                                |
| ----------------------------------------------- | ---------------------------------------------------------------------- |
| [Exasol JDBC Driver][4]                         | [EXAClient License][5]                                                 |
| [MySQL Connector/J][6]                          | The GNU General Public License, v2 with Universal FOSS Exception, v1.0 |
| [Protocol Buffers [Core]][7]                    | [BSD-3-Clause][8]                                                      |
| [PostgreSQL JDBC Driver][9]                     | [BSD-2-Clause][10]                                                     |
| [ojdbc11][11]                                   | [Oracle Free Use Terms and Conditions (FUTC)][12]                      |
| [junit-pioneer][13]                             | [Eclipse Public License v2.0][14]                                      |
| [Test containers for Exasol on Docker][15]      | [MIT License][16]                                                      |
| [Testcontainers :: JUnit Jupiter Extension][17] | [MIT][18]                                                              |
| [Testcontainers :: JDBC :: MySQL][17]           | [MIT][18]                                                              |
| [Testcontainers :: JDBC :: PostgreSQL][17]      | [MIT][18]                                                              |
| [Testcontainers :: JDBC :: Oracle XE][17]       | [MIT][18]                                                              |
| [Matcher for SQL Result Sets][19]               | [MIT License][20]                                                      |
| [Hamcrest][21]                                  | [BSD-3-Clause][22]                                                     |
| [JUnit Jupiter API][23]                         | [Eclipse Public License v2.0][14]                                      |
| [JUnit Jupiter Engine][23]                      | [Eclipse Public License v2.0][14]                                      |
| [mockito-junit-jupiter][24]                     | [MIT][25]                                                              |
| [EqualsVerifier \| release normal jar][26]      | [Apache License, Version 2.0][27]                                      |
| [SLF4J JDK14 Provider][28]                      | [MIT License][29]                                                      |

## Plugin Dependencies

| Dependency                                              | License                               |
| ------------------------------------------------------- | ------------------------------------- |
| [SonarQube Scanner for Maven][30]                       | [GNU LGPL 3][31]                      |
| [Apache Maven Toolchains Plugin][32]                    | [Apache-2.0][27]                      |
| [OpenFastTrace Maven Plugin][33]                        | [GNU General Public License v3.0][34] |
| [Project Keeper Maven plugin][35]                       | [The MIT License][36]                 |
| [Apache Maven Compiler Plugin][37]                      | [Apache-2.0][27]                      |
| [Apache Maven Enforcer Plugin][38]                      | [Apache-2.0][27]                      |
| [Maven Flatten Plugin][39]                              | [Apache Software Licenese][27]        |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][40] | [ASL2][41]                            |
| [Maven Surefire Plugin][42]                             | [Apache-2.0][27]                      |
| [Versions Maven Plugin][43]                             | [Apache License, Version 2.0][27]     |
| [duplicate-finder-maven-plugin Maven Mojo][44]          | [Apache License 2.0][45]              |
| [Apache Maven Deploy Plugin][46]                        | [Apache-2.0][27]                      |
| [Apache Maven GPG Plugin][47]                           | [Apache-2.0][27]                      |
| [Apache Maven Source Plugin][48]                        | [Apache License, Version 2.0][27]     |
| [Apache Maven Javadoc Plugin][49]                       | [Apache-2.0][27]                      |
| [Nexus Staging Maven Plugin][50]                        | [Eclipse Public License][51]          |
| [Maven Failsafe Plugin][52]                             | [Apache-2.0][27]                      |
| [JaCoCo :: Maven Plugin][53]                            | [EPL-2.0][54]                         |
| [error-code-crawler-maven-plugin][55]                   | [MIT License][56]                     |
| [Reproducible Build Maven Plugin][57]                   | [Apache 2.0][41]                      |

[0]: https://github.com/exasol/db-fundamentals-java/
[1]: https://github.com/exasol/db-fundamentals-java/blob/main/LICENSE
[2]: https://github.com/exasol/error-reporting-java/
[3]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[4]: http://www.exasol.com/
[5]: https://repo1.maven.org/maven2/com/exasol/exasol-jdbc/24.1.2/exasol-jdbc-24.1.2-license.txt
[6]: http://dev.mysql.com/doc/connector-j/en/
[7]: https://developers.google.com/protocol-buffers/protobuf-java/
[8]: https://opensource.org/licenses/BSD-3-Clause
[9]: https://jdbc.postgresql.org
[10]: https://jdbc.postgresql.org/license/
[11]: https://www.oracle.com/database/technologies/maven-central-guide.html
[12]: https://www.oracle.com/downloads/licenses/oracle-free-license.html
[13]: https://junit-pioneer.org/
[14]: https://www.eclipse.org/legal/epl-v20.html
[15]: https://github.com/exasol/exasol-testcontainers/
[16]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[17]: https://java.testcontainers.org
[18]: http://opensource.org/licenses/MIT
[19]: https://github.com/exasol/hamcrest-resultset-matcher/
[20]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[21]: http://hamcrest.org/JavaHamcrest/
[22]: https://raw.githubusercontent.com/hamcrest/JavaHamcrest/master/LICENSE
[23]: https://junit.org/junit5/
[24]: https://github.com/mockito/mockito
[25]: https://opensource.org/licenses/MIT
[26]: https://www.jqno.nl/equalsverifier
[27]: https://www.apache.org/licenses/LICENSE-2.0.txt
[28]: http://www.slf4j.org
[29]: http://www.opensource.org/licenses/mit-license.php
[30]: http://sonarsource.github.io/sonar-scanner-maven/
[31]: http://www.gnu.org/licenses/lgpl.txt
[32]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[33]: https://github.com/itsallcode/openfasttrace-maven-plugin
[34]: https://www.gnu.org/licenses/gpl-3.0.html
[35]: https://github.com/exasol/project-keeper/
[36]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[37]: https://maven.apache.org/plugins/maven-compiler-plugin/
[38]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[39]: https://www.mojohaus.org/flatten-maven-plugin/
[40]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[41]: http://www.apache.org/licenses/LICENSE-2.0.txt
[42]: https://maven.apache.org/surefire/maven-surefire-plugin/
[43]: https://www.mojohaus.org/versions/versions-maven-plugin/
[44]: https://basepom.github.io/duplicate-finder-maven-plugin
[45]: http://www.apache.org/licenses/LICENSE-2.0.html
[46]: https://maven.apache.org/plugins/maven-deploy-plugin/
[47]: https://maven.apache.org/plugins/maven-gpg-plugin/
[48]: https://maven.apache.org/plugins/maven-source-plugin/
[49]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[50]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[51]: http://www.eclipse.org/legal/epl-v10.html
[52]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[53]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[54]: https://www.eclipse.org/legal/epl-2.0/
[55]: https://github.com/exasol/error-code-crawler-maven-plugin/
[56]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[57]: http://zlika.github.io/reproducible-build-maven-plugin
