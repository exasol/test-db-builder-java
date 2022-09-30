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
| [EXASolution JDBC Driver][3]                    | [EXAClient License][4]                                 |
| [MySQL Connector/J][5]                          | The GNU General Public License, v2 with FOSS exception |
| [PostgreSQL JDBC Driver][6]                     | [BSD-2-Clause][7]                                      |
| [ojdbc11][8]                                    | [Oracle Free Use Terms and Conditions (FUTC)][9]       |
| [junit-pioneer][10]                             | [Eclipse Public License v2.0][11]                      |
| [Test containers for Exasol on Docker][12]      | [MIT][1]                                               |
| [Testcontainers :: JUnit Jupiter Extension][13] | [MIT][14]                                              |
| [Testcontainers :: JDBC :: MySQL][13]           | [MIT][14]                                              |
| [Testcontainers :: JDBC :: PostgreSQL][13]      | [MIT][14]                                              |
| [Testcontainers :: JDBC :: Oracle XE][13]       | [MIT][14]                                              |
| [Matcher for SQL Result Sets][15]               | [MIT][1]                                               |
| [Hamcrest][16]                                  | [BSD License 3][17]                                    |
| [JUnit Jupiter (Aggregator)][18]                | [Eclipse Public License v2.0][11]                      |
| [JUnit Jupiter API][18]                         | [Eclipse Public License v2.0][11]                      |
| [mockito-junit-jupiter][19]                     | [The MIT License][20]                                  |
| [EqualsVerifier | release normal jar][21]       | [Apache License, Version 2.0][22]                      |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][23]                       | [GNU LGPL 3][24]                               |
| [Apache Maven Compiler Plugin][25]                      | [Apache License, Version 2.0][22]              |
| [Apache Maven Enforcer Plugin][26]                      | [Apache License, Version 2.0][22]              |
| [Maven Flatten Plugin][27]                              | [Apache Software Licenese][28]                 |
| [OpenFastTrace Maven Plugin][29]                        | [GNU General Public License v3.0][30]          |
| [Project keeper maven plugin][31]                       | [The MIT License][32]                          |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][33] | [ASL2][28]                                     |
| [Maven Surefire Plugin][34]                             | [Apache License, Version 2.0][22]              |
| [Versions Maven Plugin][35]                             | [Apache License, Version 2.0][22]              |
| [Apache Maven Deploy Plugin][36]                        | [Apache License, Version 2.0][22]              |
| [Apache Maven GPG Plugin][37]                           | [Apache License, Version 2.0][22]              |
| [Apache Maven Source Plugin][38]                        | [Apache License, Version 2.0][22]              |
| [Apache Maven Javadoc Plugin][39]                       | [Apache License, Version 2.0][22]              |
| [Nexus Staging Maven Plugin][40]                        | [Eclipse Public License][41]                   |
| [Maven Failsafe Plugin][42]                             | [Apache License, Version 2.0][22]              |
| [JaCoCo :: Maven Plugin][43]                            | [Eclipse Public License 2.0][44]               |
| [error-code-crawler-maven-plugin][45]                   | [MIT License][46]                              |
| [Reproducible Build Maven Plugin][47]                   | [Apache 2.0][28]                               |
| [Maven Clean Plugin][48]                                | [The Apache Software License, Version 2.0][28] |
| [Maven Resources Plugin][49]                            | [The Apache Software License, Version 2.0][28] |
| [Maven JAR Plugin][50]                                  | [The Apache Software License, Version 2.0][28] |
| [Maven Install Plugin][51]                              | [The Apache Software License, Version 2.0][28] |
| [Maven Site Plugin 3][52]                               | [The Apache Software License, Version 2.0][28] |

[0]: https://github.com/exasol/db-fundamentals-java
[1]: https://opensource.org/licenses/MIT
[2]: https://github.com/exasol/error-reporting-java
[3]: http://www.exasol.com
[4]: https://docs.exasol.com/connect_exasol/drivers/jdbc.htm
[5]: http://dev.mysql.com/doc/connector-j/en/
[6]: https://jdbc.postgresql.org
[7]: https://jdbc.postgresql.org/license/
[8]: https://www.oracle.com/database/technologies/maven-central-guide.html
[9]: https://www.oracle.com/downloads/licenses/oracle-free-license.html
[10]: https://junit-pioneer.org/
[11]: https://www.eclipse.org/legal/epl-v20.html
[12]: https://github.com/exasol/exasol-testcontainers
[13]: https://testcontainers.org
[14]: http://opensource.org/licenses/MIT
[15]: https://github.com/exasol/hamcrest-resultset-matcher
[16]: http://hamcrest.org/JavaHamcrest/
[17]: http://opensource.org/licenses/BSD-3-Clause
[18]: https://junit.org/junit5/
[19]: https://github.com/mockito/mockito
[20]: https://github.com/mockito/mockito/blob/main/LICENSE
[21]: https://www.jqno.nl/equalsverifier
[22]: https://www.apache.org/licenses/LICENSE-2.0.txt
[23]: http://sonarsource.github.io/sonar-scanner-maven/
[24]: http://www.gnu.org/licenses/lgpl.txt
[25]: https://maven.apache.org/plugins/maven-compiler-plugin/
[26]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[27]: https://www.mojohaus.org/flatten-maven-plugin
[28]: http://www.apache.org/licenses/LICENSE-2.0.txt
[29]: https://github.com/itsallcode/openfasttrace-maven-plugin
[30]: https://www.gnu.org/licenses/gpl-3.0.html
[31]: https://github.com/exasol/project-keeper/
[32]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[33]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[34]: https://maven.apache.org/surefire/maven-surefire-plugin/
[35]: http://www.mojohaus.org/versions-maven-plugin/
[36]: https://maven.apache.org/plugins/maven-deploy-plugin/
[37]: https://maven.apache.org/plugins/maven-gpg-plugin/
[38]: https://maven.apache.org/plugins/maven-source-plugin/
[39]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[40]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[41]: http://www.eclipse.org/legal/epl-v10.html
[42]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[43]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[44]: https://www.eclipse.org/legal/epl-2.0/
[45]: https://github.com/exasol/error-code-crawler-maven-plugin/
[46]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[47]: http://zlika.github.io/reproducible-build-maven-plugin
[48]: http://maven.apache.org/plugins/maven-clean-plugin/
[49]: http://maven.apache.org/plugins/maven-resources-plugin/
[50]: http://maven.apache.org/plugins/maven-jar-plugin/
[51]: http://maven.apache.org/plugins/maven-install-plugin/
[52]: http://maven.apache.org/plugins/maven-site-plugin/
