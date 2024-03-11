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
| [EXASolution JDBC Driver][4]                    | [EXAClient License][5]                                                 |
| [MySQL Connector/J][6]                          | The GNU General Public License, v2 with Universal FOSS Exception, v1.0 |
| [PostgreSQL JDBC Driver][7]                     | [BSD-2-Clause][8]                                                      |
| [ojdbc11][9]                                    | [Oracle Free Use Terms and Conditions (FUTC)][10]                      |
| [junit-pioneer][11]                             | [Eclipse Public License v2.0][12]                                      |
| [Test containers for Exasol on Docker][13]      | [MIT License][14]                                                      |
| [Testcontainers :: JUnit Jupiter Extension][15] | [MIT][16]                                                              |
| [Testcontainers :: JDBC :: MySQL][15]           | [MIT][16]                                                              |
| [Testcontainers :: JDBC :: PostgreSQL][15]      | [MIT][16]                                                              |
| [Testcontainers :: JDBC :: Oracle XE][15]       | [MIT][16]                                                              |
| [Matcher for SQL Result Sets][17]               | [MIT License][18]                                                      |
| [Hamcrest][19]                                  | [BSD License 3][20]                                                    |
| [JUnit Jupiter API][21]                         | [Eclipse Public License v2.0][12]                                      |
| [JUnit Jupiter Engine][21]                      | [Eclipse Public License v2.0][12]                                      |
| [mockito-junit-jupiter][22]                     | [MIT][23]                                                              |
| [EqualsVerifier \| release normal jar][24]      | [Apache License, Version 2.0][25]                                      |
| [SLF4J JDK14 Provider][26]                      | [MIT License][27]                                                      |

## Plugin Dependencies

| Dependency                                              | License                               |
| ------------------------------------------------------- | ------------------------------------- |
| [SonarQube Scanner for Maven][28]                       | [GNU LGPL 3][29]                      |
| [Apache Maven Toolchains Plugin][30]                    | [Apache License, Version 2.0][25]     |
| [Apache Maven Compiler Plugin][31]                      | [Apache-2.0][25]                      |
| [Apache Maven Enforcer Plugin][32]                      | [Apache-2.0][25]                      |
| [Maven Flatten Plugin][33]                              | [Apache Software Licenese][25]        |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][34] | [ASL2][35]                            |
| [Maven Surefire Plugin][36]                             | [Apache-2.0][25]                      |
| [Versions Maven Plugin][37]                             | [Apache License, Version 2.0][25]     |
| [duplicate-finder-maven-plugin Maven Mojo][38]          | [Apache License 2.0][39]              |
| [Apache Maven Deploy Plugin][40]                        | [Apache-2.0][25]                      |
| [Apache Maven GPG Plugin][41]                           | [Apache-2.0][25]                      |
| [Apache Maven Source Plugin][42]                        | [Apache License, Version 2.0][25]     |
| [Apache Maven Javadoc Plugin][43]                       | [Apache-2.0][25]                      |
| [Nexus Staging Maven Plugin][44]                        | [Eclipse Public License][45]          |
| [Maven Failsafe Plugin][46]                             | [Apache-2.0][25]                      |
| [JaCoCo :: Maven Plugin][47]                            | [Eclipse Public License 2.0][48]      |
| [error-code-crawler-maven-plugin][49]                   | [MIT License][50]                     |
| [Reproducible Build Maven Plugin][51]                   | [Apache 2.0][35]                      |
| [OpenFastTrace Maven Plugin][52]                        | [GNU General Public License v3.0][53] |
| [Project Keeper Maven plugin][54]                       | [The MIT License][55]                 |

[0]: https://github.com/exasol/db-fundamentals-java/
[1]: https://github.com/exasol/db-fundamentals-java/blob/main/LICENSE
[2]: https://github.com/exasol/error-reporting-java/
[3]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[4]: http://www.exasol.com
[5]: https://repo1.maven.org/maven2/com/exasol/exasol-jdbc/7.1.20/exasol-jdbc-7.1.20-license.txt
[6]: http://dev.mysql.com/doc/connector-j/en/
[7]: https://jdbc.postgresql.org
[8]: https://jdbc.postgresql.org/license/
[9]: https://www.oracle.com/database/technologies/maven-central-guide.html
[10]: https://www.oracle.com/downloads/licenses/oracle-free-license.html
[11]: https://junit-pioneer.org/
[12]: https://www.eclipse.org/legal/epl-v20.html
[13]: https://github.com/exasol/exasol-testcontainers/
[14]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[15]: https://java.testcontainers.org
[16]: http://opensource.org/licenses/MIT
[17]: https://github.com/exasol/hamcrest-resultset-matcher/
[18]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[19]: http://hamcrest.org/JavaHamcrest/
[20]: http://opensource.org/licenses/BSD-3-Clause
[21]: https://junit.org/junit5/
[22]: https://github.com/mockito/mockito
[23]: https://opensource.org/licenses/MIT
[24]: https://www.jqno.nl/equalsverifier
[25]: https://www.apache.org/licenses/LICENSE-2.0.txt
[26]: http://www.slf4j.org
[27]: http://www.opensource.org/licenses/mit-license.php
[28]: http://sonarsource.github.io/sonar-scanner-maven/
[29]: http://www.gnu.org/licenses/lgpl.txt
[30]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[31]: https://maven.apache.org/plugins/maven-compiler-plugin/
[32]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[33]: https://www.mojohaus.org/flatten-maven-plugin/
[34]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[35]: http://www.apache.org/licenses/LICENSE-2.0.txt
[36]: https://maven.apache.org/surefire/maven-surefire-plugin/
[37]: https://www.mojohaus.org/versions/versions-maven-plugin/
[38]: https://basepom.github.io/duplicate-finder-maven-plugin
[39]: http://www.apache.org/licenses/LICENSE-2.0.html
[40]: https://maven.apache.org/plugins/maven-deploy-plugin/
[41]: https://maven.apache.org/plugins/maven-gpg-plugin/
[42]: https://maven.apache.org/plugins/maven-source-plugin/
[43]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[44]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[45]: http://www.eclipse.org/legal/epl-v10.html
[46]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[47]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[48]: https://www.eclipse.org/legal/epl-2.0/
[49]: https://github.com/exasol/error-code-crawler-maven-plugin/
[50]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[51]: http://zlika.github.io/reproducible-build-maven-plugin
[52]: https://github.com/itsallcode/openfasttrace-maven-plugin
[53]: https://www.gnu.org/licenses/gpl-3.0.html
[54]: https://github.com/exasol/project-keeper/
[55]: https://github.com/exasol/project-keeper/blob/main/LICENSE
