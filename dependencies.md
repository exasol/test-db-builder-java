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
| [Test containers for Exasol on Docker][10]      | [MIT][1]                                               |
| [Testcontainers :: JUnit Jupiter Extension][11] | [MIT][12]                                              |
| [Testcontainers :: JDBC :: MySQL][11]           | [MIT][12]                                              |
| [Testcontainers :: JDBC :: PostgreSQL][11]      | [MIT][12]                                              |
| [Testcontainers :: JDBC :: Oracle XE][11]       | [MIT][12]                                              |
| [Matcher for SQL Result Sets][13]               | [MIT][1]                                               |
| [Hamcrest][14]                                  | [BSD License 3][15]                                    |
| [JUnit Jupiter Engine][16]                      | [Eclipse Public License v2.0][17]                      |
| [JUnit Jupiter Params][16]                      | [Eclipse Public License v2.0][17]                      |
| [mockito-junit-jupiter][18]                     | [The MIT License][19]                                  |
| [EqualsVerifier | release normal jar][20]       | [Apache License, Version 2.0][21]                      |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][22]                       | [GNU LGPL 3][23]                               |
| [Apache Maven Compiler Plugin][24]                      | [Apache License, Version 2.0][21]              |
| [Apache Maven Enforcer Plugin][25]                      | [Apache License, Version 2.0][21]              |
| [Maven Flatten Plugin][26]                              | [Apache Software Licenese][27]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][28] | [ASL2][27]                                     |
| [Reproducible Build Maven Plugin][29]                   | [Apache 2.0][27]                               |
| [Maven Surefire Plugin][30]                             | [Apache License, Version 2.0][21]              |
| [Versions Maven Plugin][31]                             | [Apache License, Version 2.0][21]              |
| [Apache Maven Deploy Plugin][32]                        | [Apache License, Version 2.0][21]              |
| [Apache Maven GPG Plugin][33]                           | [Apache License, Version 2.0][21]              |
| [Apache Maven Source Plugin][34]                        | [Apache License, Version 2.0][21]              |
| [Apache Maven Javadoc Plugin][35]                       | [Apache License, Version 2.0][21]              |
| [Nexus Staging Maven Plugin][36]                        | [Eclipse Public License][37]                   |
| [Maven Failsafe Plugin][38]                             | [Apache License, Version 2.0][21]              |
| [JaCoCo :: Maven Plugin][39]                            | [Eclipse Public License 2.0][40]               |
| [error-code-crawler-maven-plugin][41]                   | [MIT][1]                                       |
| [OpenFastTrace Maven Plugin][42]                        | [GNU General Public License v3.0][43]          |
| [Project keeper maven plugin][44]                       | [The MIT License][45]                          |
| [Maven Clean Plugin][46]                                | [The Apache Software License, Version 2.0][27] |
| [Maven Resources Plugin][47]                            | [The Apache Software License, Version 2.0][27] |
| [Maven JAR Plugin][48]                                  | [The Apache Software License, Version 2.0][27] |
| [Maven Install Plugin][49]                              | [The Apache Software License, Version 2.0][27] |
| [Maven Site Plugin 3][50]                               | [The Apache Software License, Version 2.0][27] |

[0]: https://github.com/exasol/db-fundamentals-java
[1]: https://opensource.org/licenses/MIT
[2]: https://github.com/exasol/error-reporting-java
[3]: http://www.exasol.com
[4]: https://www.exasol.com/support/secure/attachment/155343/EXASOL_SDK-7.0.11.tar.gz
[5]: http://dev.mysql.com/doc/connector-j/en/
[6]: https://jdbc.postgresql.org
[7]: https://jdbc.postgresql.org/about/license.html
[8]: https://www.oracle.com/database/technologies/maven-central-guide.html
[9]: https://www.oracle.com/downloads/licenses/oracle-free-license.html
[10]: https://github.com/exasol/exasol-testcontainers
[11]: https://testcontainers.org
[12]: http://opensource.org/licenses/MIT
[13]: https://github.com/exasol/hamcrest-resultset-matcher
[14]: http://hamcrest.org/JavaHamcrest/
[15]: http://opensource.org/licenses/BSD-3-Clause
[16]: https://junit.org/junit5/
[17]: https://www.eclipse.org/legal/epl-v20.html
[18]: https://github.com/mockito/mockito
[19]: https://github.com/mockito/mockito/blob/main/LICENSE
[20]: https://www.jqno.nl/equalsverifier
[21]: https://www.apache.org/licenses/LICENSE-2.0.txt
[22]: http://sonarsource.github.io/sonar-scanner-maven/
[23]: http://www.gnu.org/licenses/lgpl.txt
[24]: https://maven.apache.org/plugins/maven-compiler-plugin/
[25]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[26]: https://www.mojohaus.org/flatten-maven-plugin
[27]: http://www.apache.org/licenses/LICENSE-2.0.txt
[28]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[29]: http://zlika.github.io/reproducible-build-maven-plugin
[30]: https://maven.apache.org/surefire/maven-surefire-plugin/
[31]: http://www.mojohaus.org/versions-maven-plugin/
[32]: https://maven.apache.org/plugins/maven-deploy-plugin/
[33]: https://maven.apache.org/plugins/maven-gpg-plugin/
[34]: https://maven.apache.org/plugins/maven-source-plugin/
[35]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[36]: http://www.sonatype.com/public-parent/nexus-maven-plugins/nexus-staging/nexus-staging-maven-plugin/
[37]: http://www.eclipse.org/legal/epl-v10.html
[38]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[39]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[40]: https://www.eclipse.org/legal/epl-2.0/
[41]: https://github.com/exasol/error-code-crawler-maven-plugin
[42]: https://github.com/itsallcode/openfasttrace-maven-plugin
[43]: https://www.gnu.org/licenses/gpl-3.0.html
[44]: https://github.com/exasol/project-keeper/
[45]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[46]: http://maven.apache.org/plugins/maven-clean-plugin/
[47]: http://maven.apache.org/plugins/maven-resources-plugin/
[48]: http://maven.apache.org/plugins/maven-jar-plugin/
[49]: http://maven.apache.org/plugins/maven-install-plugin/
[50]: http://maven.apache.org/plugins/maven-site-plugin/
