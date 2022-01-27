<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                 | License                                          |
| ------------------------------------------ | ------------------------------------------------ |
| [ojdbc11][0]                               | [Oracle Free Use Terms and Conditions (FUTC)][1] |
| [Exasol Database fundamentals for Java][2] | [MIT][3]                                         |
| [error-reporting-java][4]                  | [MIT][3]                                         |

## Test Dependencies

| Dependency                                     | License                           |
| ---------------------------------------------- | --------------------------------- |
| [Test containers for Exasol on Docker][6]      | [MIT][3]                          |
| [Testcontainers :: JUnit Jupiter Extension][8] | [MIT][9]                          |
| [Testcontainers :: JDBC :: MySQL][8]           | [MIT][9]                          |
| [Testcontainers :: JDBC :: PostgreSQL][8]      | [MIT][9]                          |
| [Testcontainers :: JDBC :: Oracle XE][8]       | [MIT][9]                          |
| [Matcher for SQL Result Sets][16]              | [MIT][3]                          |
| [Hamcrest][18]                                 | [BSD License 3][19]               |
| [JUnit Jupiter Engine][20]                     | [Eclipse Public License v2.0][21] |
| [JUnit Jupiter Params][20]                     | [Eclipse Public License v2.0][21] |
| [mockito-junit-jupiter][24]                    | [The MIT License][25]             |
| [EqualsVerifier][26]                           | [Apache License, Version 2.0][27] |

## Runtime Dependencies

| Dependency                    | License                                                |
| ----------------------------- | ------------------------------------------------------ |
| [EXASolution JDBC Driver][28] | [EXAClient License][29]                                |
| [MySQL Connector/J][30]       | The GNU General Public License, v2 with FOSS exception |
| [PostgreSQL JDBC Driver][31]  | [BSD-2-Clause][32]                                     |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Maven Surefire Plugin][33]                             | [Apache License, Version 2.0][34]              |
| [JaCoCo :: Maven Plugin][35]                            | [Eclipse Public License 2.0][36]               |
| [Apache Maven Compiler Plugin][37]                      | [Apache License, Version 2.0][34]              |
| [Maven Failsafe Plugin][39]                             | [Apache License, Version 2.0][34]              |
| [Apache Maven Source Plugin][41]                        | [Apache License, Version 2.0][34]              |
| [Apache Maven Javadoc Plugin][43]                       | [Apache License, Version 2.0][34]              |
| [Apache Maven GPG Plugin][45]                           | [Apache License, Version 2.0][46]              |
| [OpenFastTrace Maven Plugin][47]                        | [GNU General Public License v3.0][48]          |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][49] | [ASL2][46]                                     |
| [Versions Maven Plugin][51]                             | [Apache License, Version 2.0][34]              |
| [Apache Maven Enforcer Plugin][53]                      | [Apache License, Version 2.0][34]              |
| [Project keeper maven plugin][55]                       | [MIT][3]                                       |
| [Maven Deploy Plugin][57]                               | [The Apache Software License, Version 2.0][46] |
| [Nexus Staging Maven Plugin][59]                        | [Eclipse Public License][60]                   |
| [error-code-crawler-maven-plugin][61]                   | [MIT][3]                                       |
| [Reproducible Build Maven Plugin][63]                   | [Apache 2.0][46]                               |
| [Maven Clean Plugin][65]                                | [The Apache Software License, Version 2.0][46] |
| [Maven Resources Plugin][67]                            | [The Apache Software License, Version 2.0][46] |
| [Maven JAR Plugin][69]                                  | [The Apache Software License, Version 2.0][46] |
| [Maven Install Plugin][71]                              | [The Apache Software License, Version 2.0][46] |
| [Maven Site Plugin 3][73]                               | [The Apache Software License, Version 2.0][46] |

[35]: https://www.eclemma.org/jacoco/index.html
[55]: https://github.com/exasol/project-keeper-maven-plugin
[0]: https://www.oracle.com/database/technologies/maven-central-guide.html
[4]: https://github.com/exasol/error-reporting-java
[2]: https://github.com/exasol/db-fundamentals-java
[31]: https://jdbc.postgresql.org
[46]: http://www.apache.org/licenses/LICENSE-2.0.txt
[33]: https://maven.apache.org/surefire/maven-surefire-plugin/
[59]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[29]: https://www.exasol.com/support/secure/attachment/155343/EXASOL_SDK-7.0.11.tar.gz
[32]: https://jdbc.postgresql.org/about/license.html
[65]: http://maven.apache.org/plugins/maven-clean-plugin/
[1]: https://www.oracle.com/downloads/licenses/oracle-free-license.html
[3]: https://opensource.org/licenses/MIT
[24]: https://github.com/mockito/mockito
[39]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[51]: http://www.mojohaus.org/versions-maven-plugin/
[19]: http://opensource.org/licenses/BSD-3-Clause
[37]: https://maven.apache.org/plugins/maven-compiler-plugin/
[9]: http://opensource.org/licenses/MIT
[45]: http://maven.apache.org/plugins/maven-gpg-plugin/
[27]: https:///www.apache.org/licenses/LICENSE-2.0.txt
[47]: https://github.com/itsallcode/openfasttrace-maven-plugin
[36]: https://www.eclipse.org/legal/epl-2.0/
[60]: http://www.eclipse.org/legal/epl-v10.html
[6]: https://github.com/exasol/exasol-testcontainers
[25]: https://github.com/mockito/mockito/blob/main/LICENSE
[16]: https://github.com/exasol/hamcrest-resultset-matcher
[63]: http://zlika.github.io/reproducible-build-maven-plugin
[48]: https://www.gnu.org/licenses/gpl-3.0.html
[69]: http://maven.apache.org/plugins/maven-jar-plugin/
[34]: https://www.apache.org/licenses/LICENSE-2.0.txt
[53]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[30]: http://dev.mysql.com/doc/connector-j/en/
[28]: http://www.exasol.com
[21]: https://www.eclipse.org/legal/epl-v20.html
[71]: http://maven.apache.org/plugins/maven-install-plugin/
[20]: https://junit.org/junit5/
[49]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[8]: https://testcontainers.org
[26]: https:///www.jqno.nl/equalsverifier
[41]: https://maven.apache.org/plugins/maven-source-plugin/
[18]: http://hamcrest.org/JavaHamcrest/
[57]: http://maven.apache.org/plugins/maven-deploy-plugin/
[73]: http://maven.apache.org/plugins/maven-site-plugin/
[67]: http://maven.apache.org/plugins/maven-resources-plugin/
[43]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[61]: https://github.com/exasol/error-code-crawler-maven-plugin
