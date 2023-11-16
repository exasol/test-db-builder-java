<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                 | License          |
| ------------------------------------------ | ---------------- |
| [Exasol Database fundamentals for Java][0] | [MIT License][1] |
| [error-reporting-java][2]                  | [MIT License][3] |
| [SLF4J JDK14 Provider][4]                  | [MIT License][5] |

## Test Dependencies

| Dependency                                      | License                                                                |
| ----------------------------------------------- | ---------------------------------------------------------------------- |
| [EXASolution JDBC Driver][6]                    | [EXAClient License][7]                                                 |
| [MySQL Connector/J][8]                          | The GNU General Public License, v2 with Universal FOSS Exception, v1.0 |
| [PostgreSQL JDBC Driver][9]                     | [BSD-2-Clause][10]                                                     |
| [ojdbc11][11]                                   | [Oracle Free Use Terms and Conditions (FUTC)][12]                      |
| [junit-pioneer][13]                             | [Eclipse Public License v2.0][14]                                      |
| [Test containers for Exasol on Docker][15]      | [MIT License][16]                                                      |
| [Testcontainers :: JUnit Jupiter Extension][17] | [MIT][18]                                                              |
| [Testcontainers :: JDBC :: MySQL][17]           | [MIT][18]                                                              |
| [Testcontainers :: JDBC :: PostgreSQL][17]      | [MIT][18]                                                              |
| [Testcontainers :: JDBC :: Oracle XE][17]       | [MIT][18]                                                              |
| [Matcher for SQL Result Sets][19]               | [MIT License][20]                                                      |
| [Hamcrest][21]                                  | [BSD License 3][22]                                                    |
| [JUnit Jupiter API][23]                         | [Eclipse Public License v2.0][14]                                      |
| [JUnit Jupiter Engine][23]                      | [Eclipse Public License v2.0][14]                                      |
| [mockito-junit-jupiter][24]                     | [MIT][25]                                                              |
| [EqualsVerifier \| release normal jar][26]      | [Apache License, Version 2.0][27]                                      |

## Plugin Dependencies

| Dependency                                              | License                               |
| ------------------------------------------------------- | ------------------------------------- |
| [SonarQube Scanner for Maven][28]                       | [GNU LGPL 3][29]                      |
| [Apache Maven Compiler Plugin][30]                      | [Apache-2.0][27]                      |
| [Apache Maven Enforcer Plugin][31]                      | [Apache-2.0][27]                      |
| [Maven Flatten Plugin][32]                              | [Apache Software Licenese][27]        |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][33] | [ASL2][34]                            |
| [Maven Surefire Plugin][35]                             | [Apache-2.0][27]                      |
| [Versions Maven Plugin][36]                             | [Apache License, Version 2.0][27]     |
| [duplicate-finder-maven-plugin Maven Mojo][37]          | [Apache License 2.0][38]              |
| [Apache Maven Deploy Plugin][39]                        | [Apache-2.0][27]                      |
| [Apache Maven GPG Plugin][40]                           | [Apache-2.0][27]                      |
| [Apache Maven Source Plugin][41]                        | [Apache License, Version 2.0][27]     |
| [Apache Maven Javadoc Plugin][42]                       | [Apache-2.0][27]                      |
| [Nexus Staging Maven Plugin][43]                        | [Eclipse Public License][44]          |
| [Maven Failsafe Plugin][45]                             | [Apache-2.0][27]                      |
| [JaCoCo :: Maven Plugin][46]                            | [Eclipse Public License 2.0][47]      |
| [error-code-crawler-maven-plugin][48]                   | [MIT License][49]                     |
| [Reproducible Build Maven Plugin][50]                   | [Apache 2.0][34]                      |
| [OpenFastTrace Maven Plugin][51]                        | [GNU General Public License v3.0][52] |
| [Project keeper maven plugin][53]                       | [The MIT License][54]                 |

[0]: https://github.com/exasol/db-fundamentals-java/
[1]: https://github.com/exasol/db-fundamentals-java/blob/main/LICENSE
[2]: https://github.com/exasol/error-reporting-java/
[3]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[4]: http://www.slf4j.org
[5]: http://www.opensource.org/licenses/mit-license.php
[6]: http://www.exasol.com
[7]: https://repo1.maven.org/maven2/com/exasol/exasol-jdbc/7.1.20/exasol-jdbc-7.1.20-license.txt
[8]: http://dev.mysql.com/doc/connector-j/en/
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
[22]: http://opensource.org/licenses/BSD-3-Clause
[23]: https://junit.org/junit5/
[24]: https://github.com/mockito/mockito
[25]: https://opensource.org/licenses/MIT
[26]: https://www.jqno.nl/equalsverifier
[27]: https://www.apache.org/licenses/LICENSE-2.0.txt
[28]: http://sonarsource.github.io/sonar-scanner-maven/
[29]: http://www.gnu.org/licenses/lgpl.txt
[30]: https://maven.apache.org/plugins/maven-compiler-plugin/
[31]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[32]: https://www.mojohaus.org/flatten-maven-plugin/
[33]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[34]: http://www.apache.org/licenses/LICENSE-2.0.txt
[35]: https://maven.apache.org/surefire/maven-surefire-plugin/
[36]: https://www.mojohaus.org/versions/versions-maven-plugin/
[37]: https://basepom.github.io/duplicate-finder-maven-plugin
[38]: http://www.apache.org/licenses/LICENSE-2.0.html
[39]: https://maven.apache.org/plugins/maven-deploy-plugin/
[40]: https://maven.apache.org/plugins/maven-gpg-plugin/
[41]: https://maven.apache.org/plugins/maven-source-plugin/
[42]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[43]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[44]: http://www.eclipse.org/legal/epl-v10.html
[45]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[46]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[47]: https://www.eclipse.org/legal/epl-2.0/
[48]: https://github.com/exasol/error-code-crawler-maven-plugin/
[49]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[50]: http://zlika.github.io/reproducible-build-maven-plugin
[51]: https://github.com/itsallcode/openfasttrace-maven-plugin
[52]: https://www.gnu.org/licenses/gpl-3.0.html
[53]: https://github.com/exasol/project-keeper/
[54]: https://github.com/exasol/project-keeper/blob/main/LICENSE
