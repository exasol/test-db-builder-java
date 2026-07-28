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
| [JUnit Jupiter Params][21]                      | [Eclipse Public License v2.0][12]                                      |
| [mockito-junit-jupiter][22]                     | [MIT][23]                                                              |
| [EqualsVerifier \| release normal jar][24]      | [Apache License, Version 2.0][25]                                      |
| [SLF4J JDK14 Provider][26]                      | [MIT][27]                                                              |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][28]                       | [GNU LGPL 3][29]                               |
| [Apache Maven Toolchains Plugin][30]                    | [Apache-2.0][25]                               |
| [OpenFastTrace Maven Plugin][31]                        | [GNU General Public License v3.0][32]          |
| [Project Keeper Maven plugin][33]                       | [The MIT License][34]                          |
| [Apache Maven Compiler Plugin][35]                      | [Apache-2.0][25]                               |
| [Apache Maven Enforcer Plugin][36]                      | [Apache-2.0][25]                               |
| [Maven Flatten Plugin][37]                              | [Apache Software License][25]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][38] | [ASL2][39]                                     |
| [Maven Surefire Plugin][40]                             | [Apache-2.0][25]                               |
| [Versions Maven Plugin][41]                             | [Apache License, Version 2.0][25]              |
| [duplicate-finder-maven-plugin Maven Mojo][42]          | [Apache License 2.0][43]                       |
| [Apache Maven Artifact Plugin][44]                      | [Apache-2.0][25]                               |
| [Apache Maven Deploy Plugin][45]                        | [Apache-2.0][25]                               |
| [Apache Maven Source Plugin][46]                        | [Apache-2.0][25]                               |
| [Apache Maven Javadoc Plugin][47]                       | [Apache-2.0][25]                               |
| [spdx-maven-plugin Maven Plugin][48]                    | [The Apache Software License, Version 2.0][39] |
| [Build Helper Maven Plugin][49]                         | [The MIT License][50]                          |
| [Apache Maven GPG Plugin][51]                           | [Apache-2.0][25]                               |
| [Central Publishing Maven Plugin][52]                   | [The Apache License, Version 2.0][25]          |
| [Maven Failsafe Plugin][53]                             | [Apache-2.0][25]                               |
| [JaCoCo :: Maven Plugin][54]                            | [EPL-2.0][55]                                  |
| [error-code-crawler-maven-plugin][56]                   | [MIT License][57]                              |
| [Git Commit Id Maven Plugin][58]                        | [GNU Lesser General Public License 3.0][59]    |
| [Apache Maven Clean Plugin][60]                         | [Apache-2.0][25]                               |
| [Apache Maven Resources Plugin][61]                     | [Apache-2.0][25]                               |
| [Apache Maven Install Plugin][62]                       | [Apache-2.0][25]                               |
| [Apache Maven Site Plugin][63]                          | [Apache-2.0][25]                               |

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
[28]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[29]: http://www.gnu.org/licenses/lgpl.txt
[30]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[31]: https://github.com/itsallcode/openfasttrace-maven-plugin
[32]: https://www.gnu.org/licenses/gpl-3.0.html
[33]: https://github.com/exasol/project-keeper/
[34]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[35]: https://maven.apache.org/plugins/maven-compiler-plugin/
[36]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[37]: https://www.mojohaus.org/flatten-maven-plugin/
[38]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[39]: http://www.apache.org/licenses/LICENSE-2.0.txt
[40]: https://maven.apache.org/surefire/maven-surefire-plugin/
[41]: https://www.mojohaus.org/versions/versions-maven-plugin/
[42]: https://basepom.github.io/duplicate-finder-maven-plugin
[43]: http://www.apache.org/licenses/LICENSE-2.0.html
[44]: https://maven.apache.org/plugins/maven-artifact-plugin/
[45]: https://maven.apache.org/plugins/maven-deploy-plugin/
[46]: https://maven.apache.org/plugins/maven-source-plugin/
[47]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[48]: https://github.com/spdx/spdx-maven-plugin
[49]: https://www.mojohaus.org/build-helper-maven-plugin/
[50]: https://spdx.org/licenses/MIT.txt
[51]: https://maven.apache.org/plugins/maven-gpg-plugin/
[52]: https://central.sonatype.org
[53]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[54]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[55]: https://www.eclipse.org/legal/epl-2.0/
[56]: https://github.com/exasol/error-code-crawler-maven-plugin/
[57]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[58]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[59]: http://www.gnu.org/licenses/lgpl-3.0.txt
[60]: https://maven.apache.org/plugins/maven-clean-plugin/
[61]: https://maven.apache.org/plugins/maven-resources-plugin/
[62]: https://maven.apache.org/plugins/maven-install-plugin/
[63]: https://maven.apache.org/plugins/maven-site-plugin/
