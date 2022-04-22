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
| [SonarQube Scanner for Maven][33]                       | [GNU LGPL 3][34]                               |
| [Apache Maven Compiler Plugin][35]                      | [Apache License, Version 2.0][32]              |
| [Apache Maven Enforcer Plugin][37]                      | [Apache License, Version 2.0][32]              |
| [Maven Flatten Plugin][39]                              | [Apache Software Licenese][40]                 |
| [OpenFastTrace Maven Plugin][41]                        | [GNU General Public License v3.0][42]          |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][43] | [ASL2][40]                                     |
| [Reproducible Build Maven Plugin][45]                   | [Apache 2.0][40]                               |
| [Maven Surefire Plugin][47]                             | [Apache License, Version 2.0][32]              |
| [Versions Maven Plugin][49]                             | [Apache License, Version 2.0][32]              |
| [Project keeper maven plugin][51]                       | [The MIT License][52]                          |
| [Apache Maven Deploy Plugin][53]                        | [Apache License, Version 2.0][32]              |
| [Apache Maven GPG Plugin][55]                           | [Apache License, Version 2.0][32]              |
| [Apache Maven Source Plugin][57]                        | [Apache License, Version 2.0][32]              |
| [Apache Maven Javadoc Plugin][59]                       | [Apache License, Version 2.0][32]              |
| [Nexus Staging Maven Plugin][61]                        | [Eclipse Public License][62]                   |
| [Maven Failsafe Plugin][63]                             | [Apache License, Version 2.0][32]              |
| [JaCoCo :: Maven Plugin][65]                            | [Eclipse Public License 2.0][66]               |
| [error-code-crawler-maven-plugin][67]                   | [MIT][1]                                       |
| [Maven Clean Plugin][69]                                | [The Apache Software License, Version 2.0][40] |
| [Maven Resources Plugin][71]                            | [The Apache Software License, Version 2.0][40] |
| [Maven JAR Plugin][73]                                  | [The Apache Software License, Version 2.0][40] |
| [Maven Install Plugin][75]                              | [The Apache Software License, Version 2.0][40] |
| [Maven Site Plugin 3][77]                               | [The Apache Software License, Version 2.0][40] |

[2]: https://github.com/exasol/error-reporting-java
[40]: http://www.apache.org/licenses/LICENSE-2.0.txt
[47]: https://maven.apache.org/surefire/maven-surefire-plugin/
[5]: https://www.exasol.com/support/secure/attachment/155343/EXASOL_SDK-7.0.11.tar.gz
[8]: https://jdbc.postgresql.org/about/license.html
[69]: http://maven.apache.org/plugins/maven-clean-plugin/
[10]: https://www.oracle.com/downloads/licenses/oracle-free-license.html
[1]: https://opensource.org/licenses/MIT
[29]: https://github.com/mockito/mockito
[49]: http://www.mojohaus.org/versions-maven-plugin/
[51]: https://github.com/exasol/project-keeper/
[24]: http://opensource.org/licenses/BSD-3-Clause
[35]: https://maven.apache.org/plugins/maven-compiler-plugin/
[41]: https://github.com/itsallcode/openfasttrace-maven-plugin
[66]: https://www.eclipse.org/legal/epl-2.0/
[53]: https://maven.apache.org/plugins/maven-deploy-plugin/
[34]: http://www.gnu.org/licenses/lgpl.txt
[65]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[30]: https://github.com/mockito/mockito/blob/main/LICENSE
[21]: https://github.com/exasol/hamcrest-resultset-matcher
[45]: http://zlika.github.io/reproducible-build-maven-plugin
[33]: http://sonarsource.github.io/sonar-scanner-maven/
[6]: http://dev.mysql.com/doc/connector-j/en/
[25]: https://junit.org/junit5/
[39]: https://www.mojohaus.org/flatten-maven-plugin/flatten-maven-plugin
[57]: https://maven.apache.org/plugins/maven-source-plugin/
[23]: http://hamcrest.org/JavaHamcrest/
[71]: http://maven.apache.org/plugins/maven-resources-plugin/
[9]: https://www.oracle.com/database/technologies/maven-central-guide.html
[0]: https://github.com/exasol/db-fundamentals-java
[7]: https://jdbc.postgresql.org
[61]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[63]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[14]: http://opensource.org/licenses/MIT
[62]: http://www.eclipse.org/legal/epl-v10.html
[11]: https://github.com/exasol/exasol-testcontainers
[52]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[42]: https://www.gnu.org/licenses/gpl-3.0.html
[73]: http://maven.apache.org/plugins/maven-jar-plugin/
[32]: https://www.apache.org/licenses/LICENSE-2.0.txt
[31]: https://www.jqno.nl/equalsverifier
[37]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[4]: http://www.exasol.com
[26]: https://www.eclipse.org/legal/epl-v20.html
[75]: http://maven.apache.org/plugins/maven-install-plugin/
[43]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[55]: https://maven.apache.org/plugins/maven-gpg-plugin/
[13]: https://testcontainers.org
[77]: http://maven.apache.org/plugins/maven-site-plugin/
[59]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[67]: https://github.com/exasol/error-code-crawler-maven-plugin
