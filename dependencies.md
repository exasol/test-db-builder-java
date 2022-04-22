<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                 | License  |
| ------------------------------------------ | -------- |
| [Exasol Database fundamentals for Java][0] | [MIT][1] |
| [error-reporting-java][2]                  | [MIT][1] |

## Test Dependencies

| Dependency                                      | License                                                |
| ----------------------------------------------- | ------------------------------------------------------ |
| [EXASolution JDBC Driver][4]                    | [EXAClient License][5]                                 |
| [MySQL Connector/J][6]                          | The GNU General Public License, v2 with FOSS exception |
| [PostgreSQL JDBC Driver][7]                     | [BSD-2-Clause][8]                                      |
| [ojdbc11][9]                                    | [Oracle Free Use Terms and Conditions (FUTC)][10]      |
| [Test containers for Exasol on Docker][11]      | [MIT][1]                                               |
| [Testcontainers :: JUnit Jupiter Extension][13] | [MIT][14]                                              |
| [Testcontainers :: JDBC :: MySQL][13]           | [MIT][14]                                              |
| [Testcontainers :: JDBC :: PostgreSQL][13]      | [MIT][14]                                              |
| [Testcontainers :: JDBC :: Oracle XE][13]       | [MIT][14]                                              |
| [Matcher for SQL Result Sets][21]               | [MIT][1]                                               |
| [Hamcrest][23]                                  | [BSD License 3][24]                                    |
| [JUnit Jupiter Engine][25]                      | [Eclipse Public License v2.0][26]                      |
| [JUnit Jupiter Params][25]                      | [Eclipse Public License v2.0][26]                      |
| [mockito-junit-jupiter][29]                     | [The MIT License][30]                                  |
| [EqualsVerifier | release normal jar][31]       | [Apache License, Version 2.0][32]                      |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Maven Surefire Plugin][33]                             | [Apache License, Version 2.0][32]              |
| [JaCoCo :: Maven Plugin][35]                            | [Eclipse Public License 2.0][36]               |
| [Apache Maven Compiler Plugin][37]                      | [Apache License, Version 2.0][32]              |
| [Maven Failsafe Plugin][39]                             | [Apache License, Version 2.0][32]              |
| [Apache Maven Source Plugin][41]                        | [Apache License, Version 2.0][32]              |
| [Apache Maven Javadoc Plugin][43]                       | [Apache License, Version 2.0][32]              |
| [Apache Maven GPG Plugin][45]                           | [Apache License, Version 2.0][32]              |
| [OpenFastTrace Maven Plugin][47]                        | [GNU General Public License v3.0][48]          |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][49] | [ASL2][50]                                     |
| [Versions Maven Plugin][51]                             | [Apache License, Version 2.0][32]              |
| [Apache Maven Enforcer Plugin][53]                      | [Apache License, Version 2.0][32]              |
| [Project keeper maven plugin][55]                       | [MIT][1]                                       |
| [Maven Deploy Plugin][57]                               | [The Apache Software License, Version 2.0][50] |
| [Nexus Staging Maven Plugin][59]                        | [Eclipse Public License][60]                   |
| [error-code-crawler-maven-plugin][61]                   | [MIT][1]                                       |
| [Reproducible Build Maven Plugin][63]                   | [Apache 2.0][50]                               |
| [Maven Clean Plugin][65]                                | [The Apache Software License, Version 2.0][50] |
| [Maven Resources Plugin][67]                            | [The Apache Software License, Version 2.0][50] |
| [Maven JAR Plugin][69]                                  | [The Apache Software License, Version 2.0][50] |
| [Maven Install Plugin][71]                              | [The Apache Software License, Version 2.0][50] |
| [Maven Site Plugin 3][73]                               | [The Apache Software License, Version 2.0][50] |

[55]: https://github.com/exasol/project-keeper-maven-plugin
[2]: https://github.com/exasol/error-reporting-java
[9]: https://www.oracle.com/database/technologies/maven-central-guide.html
[0]: https://github.com/exasol/db-fundamentals-java
[7]: https://jdbc.postgresql.org
[50]: http://www.apache.org/licenses/LICENSE-2.0.txt
[33]: https://maven.apache.org/surefire/maven-surefire-plugin/
[59]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[5]: https://www.exasol.com/support/secure/attachment/155343/EXASOL_SDK-7.0.11.tar.gz
[8]: https://jdbc.postgresql.org/about/license.html
[65]: http://maven.apache.org/plugins/maven-clean-plugin/
[10]: https://www.oracle.com/downloads/licenses/oracle-free-license.html
[1]: https://opensource.org/licenses/MIT
[29]: https://github.com/mockito/mockito
[39]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[51]: http://www.mojohaus.org/versions-maven-plugin/
[24]: http://opensource.org/licenses/BSD-3-Clause
[37]: https://maven.apache.org/plugins/maven-compiler-plugin/
[14]: http://opensource.org/licenses/MIT
[47]: https://github.com/itsallcode/openfasttrace-maven-plugin
[36]: https://www.eclipse.org/legal/epl-2.0/
[60]: http://www.eclipse.org/legal/epl-v10.html
[11]: https://github.com/exasol/exasol-testcontainers
[35]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[30]: https://github.com/mockito/mockito/blob/main/LICENSE
[21]: https://github.com/exasol/hamcrest-resultset-matcher
[63]: http://zlika.github.io/reproducible-build-maven-plugin
[48]: https://www.gnu.org/licenses/gpl-3.0.html
[69]: http://maven.apache.org/plugins/maven-jar-plugin/
[32]: https://www.apache.org/licenses/LICENSE-2.0.txt
[31]: https://www.jqno.nl/equalsverifier
[53]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[6]: http://dev.mysql.com/doc/connector-j/en/
[4]: http://www.exasol.com
[26]: https://www.eclipse.org/legal/epl-v20.html
[71]: http://maven.apache.org/plugins/maven-install-plugin/
[25]: https://junit.org/junit5/
[49]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[45]: https://maven.apache.org/plugins/maven-gpg-plugin/
[13]: https://testcontainers.org
[41]: https://maven.apache.org/plugins/maven-source-plugin/
[23]: http://hamcrest.org/JavaHamcrest/
[57]: http://maven.apache.org/plugins/maven-deploy-plugin/
[73]: http://maven.apache.org/plugins/maven-site-plugin/
[67]: http://maven.apache.org/plugins/maven-resources-plugin/
[43]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[61]: https://github.com/exasol/error-code-crawler-maven-plugin
