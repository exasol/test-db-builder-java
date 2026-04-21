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
| [PostgreSQL JDBC Driver][7]                     | [BSD-2-Clause][8]                                                      |
| [ojdbc11][9]                                    | [Oracle Free Use Terms and Conditions (FUTC)][10]                      |
| [junit-pioneer][11]                             | [Eclipse Public License v2.0][12]                                      |
| [Test containers for Exasol on Docker][13]      | [MIT License][14]                                                      |
| [Testcontainers :: JUnit Jupiter Extension][15] | [MIT][16]                                                              |
| [Testcontainers :: JDBC :: MySQL][15]           | [MIT][16]                                                              |
| [Testcontainers :: JDBC :: PostgreSQL][15]      | [MIT][16]                                                              |
| [Testcontainers :: JDBC :: Oracle XE][15]       | [MIT][16]                                                              |
| [Matcher for SQL Result Sets][17]               | [MIT License][18]                                                      |
| [Hamcrest][19]                                  | [BSD-3-Clause][20]                                                     |
| [JUnit Jupiter (Aggregator)][21]                | [Eclipse Public License v2.0][12]                                      |
| [mockito-junit-jupiter][22]                     | [MIT][23]                                                              |
| [EqualsVerifier \| release normal jar][24]      | [Apache License, Version 2.0][25]                                      |
| [SLF4J JDK14 Provider][26]                      | [MIT][27]                                                              |

## Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [Apache Maven Clean Plugin][28]                         | [Apache-2.0][25]                            |
| [Apache Maven Install Plugin][29]                       | [Apache-2.0][25]                            |
| [Apache Maven Resources Plugin][30]                     | [Apache-2.0][25]                            |
| [Apache Maven Site Plugin][31]                          | [Apache-2.0][25]                            |
| [SonarQube Scanner for Maven][32]                       | [GNU LGPL 3][33]                            |
| [Apache Maven Toolchains Plugin][34]                    | [Apache-2.0][25]                            |
| [OpenFastTrace Maven Plugin][35]                        | [GNU General Public License v3.0][36]       |
| [Project Keeper Maven plugin][37]                       | [The MIT License][38]                       |
| [Apache Maven Compiler Plugin][39]                      | [Apache-2.0][25]                            |
| [Apache Maven Enforcer Plugin][40]                      | [Apache-2.0][25]                            |
| [Maven Flatten Plugin][41]                              | [Apache Software License][25]               |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][42] | [ASL2][43]                                  |
| [Maven Surefire Plugin][44]                             | [Apache-2.0][25]                            |
| [Versions Maven Plugin][45]                             | [Apache License, Version 2.0][25]           |
| [duplicate-finder-maven-plugin Maven Mojo][46]          | [Apache License 2.0][47]                    |
| [Apache Maven Artifact Plugin][48]                      | [Apache-2.0][25]                            |
| [Apache Maven Deploy Plugin][49]                        | [Apache-2.0][25]                            |
| [Apache Maven GPG Plugin][50]                           | [Apache-2.0][25]                            |
| [Apache Maven Source Plugin][51]                        | [Apache-2.0][25]                            |
| [Apache Maven Javadoc Plugin][52]                       | [Apache-2.0][25]                            |
| [Central Publishing Maven Plugin][53]                   | [The Apache License, Version 2.0][25]       |
| [Maven Failsafe Plugin][54]                             | [Apache-2.0][25]                            |
| [JaCoCo :: Maven Plugin][55]                            | [EPL-2.0][56]                               |
| [Quality Summarizer Maven Plugin][57]                   | [MIT License][58]                           |
| [error-code-crawler-maven-plugin][59]                   | [MIT License][60]                           |
| [Git Commit Id Maven Plugin][61]                        | [GNU Lesser General Public License 3.0][62] |

[0]: https://github.com/exasol/db-fundamentals-java/
[1]: https://github.com/exasol/db-fundamentals-java/blob/main/LICENSE
[2]: https://github.com/exasol/error-reporting-java/
[3]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[4]: https://www.exasol.com/
[5]: https://repo1.maven.org/maven2/com/exasol/exasol-jdbc/26.2.7/exasol-jdbc-26.2.7-license.txt
[6]: http://dev.mysql.com/doc/connector-j/en/
[7]: https://jdbc.postgresql.org
[8]: https://jdbc.postgresql.org/about/license.html
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
[20]: https://raw.githubusercontent.com/hamcrest/JavaHamcrest/master/LICENSE
[21]: https://junit.org/
[22]: https://github.com/mockito/mockito
[23]: https://opensource.org/licenses/MIT
[24]: https://www.jqno.nl/equalsverifier
[25]: https://www.apache.org/licenses/LICENSE-2.0.txt
[26]: http://www.slf4j.org
[27]: https://opensource.org/license/mit
[28]: https://maven.apache.org/plugins/maven-clean-plugin/
[29]: https://maven.apache.org/plugins/maven-install-plugin/
[30]: https://maven.apache.org/plugins/maven-resources-plugin/
[31]: https://maven.apache.org/plugins/maven-site-plugin/
[32]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[33]: http://www.gnu.org/licenses/lgpl.txt
[34]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[35]: https://github.com/itsallcode/openfasttrace-maven-plugin
[36]: https://www.gnu.org/licenses/gpl-3.0.html
[37]: https://github.com/exasol/project-keeper/
[38]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[39]: https://maven.apache.org/plugins/maven-compiler-plugin/
[40]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[41]: https://www.mojohaus.org/flatten-maven-plugin/
[42]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[43]: http://www.apache.org/licenses/LICENSE-2.0.txt
[44]: https://maven.apache.org/surefire/maven-surefire-plugin/
[45]: https://www.mojohaus.org/versions/versions-maven-plugin/
[46]: https://basepom.github.io/duplicate-finder-maven-plugin
[47]: http://www.apache.org/licenses/LICENSE-2.0.html
[48]: https://maven.apache.org/plugins/maven-artifact-plugin/
[49]: https://maven.apache.org/plugins/maven-deploy-plugin/
[50]: https://maven.apache.org/plugins/maven-gpg-plugin/
[51]: https://maven.apache.org/plugins/maven-source-plugin/
[52]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[53]: https://central.sonatype.org
[54]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[55]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[56]: https://www.eclipse.org/legal/epl-2.0/
[57]: https://github.com/exasol/quality-summarizer-maven-plugin/
[58]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[59]: https://github.com/exasol/error-code-crawler-maven-plugin/
[60]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[61]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[62]: http://www.gnu.org/licenses/lgpl-3.0.txt
