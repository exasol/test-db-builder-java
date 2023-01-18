<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                 | License          |
| ------------------------------------------ | ---------------- |
| [Exasol Database fundamentals for Java][0] | [MIT License][1] |
| [error-reporting-java][2]                  | [MIT License][3] |

## Test Dependencies

| Dependency                                      | License                                           |
| ----------------------------------------------- | ------------------------------------------------- |
| [EXASolution JDBC Driver][4]                    | [EXAClient License][5]                            |
| mysql-connector-java                            |                                                   |
| [Protocol Buffers [Core]][6]                    | [BSD-3-Clause][7]                                 |
| [PostgreSQL JDBC Driver][8]                     | [BSD-2-Clause][9]                                 |
| [ojdbc11][10]                                   | [Oracle Free Use Terms and Conditions (FUTC)][11] |
| [junit-pioneer][12]                             | [Eclipse Public License v2.0][13]                 |
| [Test containers for Exasol on Docker][14]      | [MIT License][15]                                 |
| [Testcontainers :: JUnit Jupiter Extension][16] | [MIT][17]                                         |
| [Testcontainers :: JDBC :: MySQL][16]           | [MIT][17]                                         |
| [Testcontainers :: JDBC :: PostgreSQL][16]      | [MIT][17]                                         |
| [Testcontainers :: JDBC :: Oracle XE][16]       | [MIT][17]                                         |
| [Matcher for SQL Result Sets][18]               | [MIT License][19]                                 |
| [Hamcrest][20]                                  | [BSD License 3][21]                               |
| [JUnit Jupiter (Aggregator)][22]                | [Eclipse Public License v2.0][13]                 |
| [JUnit Jupiter API][22]                         | [Eclipse Public License v2.0][13]                 |
| [mockito-junit-jupiter][23]                     | [The MIT License][24]                             |
| [EqualsVerifier | release normal jar][25]       | [Apache License, Version 2.0][26]                 |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][27]                       | [GNU LGPL 3][28]                               |
| [Apache Maven Compiler Plugin][29]                      | [Apache License, Version 2.0][26]              |
| [Apache Maven Enforcer Plugin][30]                      | [Apache License, Version 2.0][26]              |
| [Maven Flatten Plugin][31]                              | [Apache Software Licenese][26]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][32] | [ASL2][33]                                     |
| [Maven Surefire Plugin][34]                             | [Apache License, Version 2.0][26]              |
| [Versions Maven Plugin][35]                             | [Apache License, Version 2.0][26]              |
| [Apache Maven Deploy Plugin][36]                        | [Apache License, Version 2.0][26]              |
| [Apache Maven GPG Plugin][37]                           | [Apache License, Version 2.0][26]              |
| [Apache Maven Source Plugin][38]                        | [Apache License, Version 2.0][26]              |
| [Apache Maven Javadoc Plugin][39]                       | [Apache License, Version 2.0][26]              |
| [Nexus Staging Maven Plugin][40]                        | [Eclipse Public License][41]                   |
| [Maven Failsafe Plugin][42]                             | [Apache License, Version 2.0][26]              |
| [JaCoCo :: Maven Plugin][43]                            | [Eclipse Public License 2.0][44]               |
| [error-code-crawler-maven-plugin][45]                   | [MIT License][46]                              |
| [Reproducible Build Maven Plugin][47]                   | [Apache 2.0][33]                               |
| [OpenFastTrace Maven Plugin][48]                        | [GNU General Public License v3.0][49]          |
| [Project keeper maven plugin][50]                       | [The MIT License][51]                          |
| [Maven Clean Plugin][52]                                | [The Apache Software License, Version 2.0][33] |
| [Maven Resources Plugin][53]                            | [The Apache Software License, Version 2.0][33] |
| [Maven JAR Plugin][54]                                  | [The Apache Software License, Version 2.0][33] |
| [Maven Install Plugin][55]                              | [The Apache Software License, Version 2.0][33] |
| [Maven Site Plugin 3][56]                               | [The Apache Software License, Version 2.0][33] |

[0]: https://github.com/exasol/db-fundamentals-java/
[1]: https://github.com/exasol/db-fundamentals-java/blob/main/LICENSE
[2]: https://github.com/exasol/error-reporting-java/
[3]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[4]: http://www.exasol.com
[5]: https://docs.exasol.com/db/latest/connect_exasol/drivers/jdbc.htm
[6]: https://developers.google.com/protocol-buffers
[7]: https://opensource.org/licenses/BSD-3-Clause
[8]: https://jdbc.postgresql.org
[9]: https://jdbc.postgresql.org/license/
[10]: https://www.oracle.com/database/technologies/maven-central-guide.html
[11]: https://www.oracle.com/downloads/licenses/oracle-free-license.html
[12]: https://junit-pioneer.org/
[13]: https://www.eclipse.org/legal/epl-v20.html
[14]: https://github.com/exasol/exasol-testcontainers/
[15]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[16]: https://testcontainers.org
[17]: http://opensource.org/licenses/MIT
[18]: https://github.com/exasol/hamcrest-resultset-matcher/
[19]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[20]: http://hamcrest.org/JavaHamcrest/
[21]: http://opensource.org/licenses/BSD-3-Clause
[22]: https://junit.org/junit5/
[23]: https://github.com/mockito/mockito
[24]: https://github.com/mockito/mockito/blob/main/LICENSE
[25]: https://www.jqno.nl/equalsverifier
[26]: https://www.apache.org/licenses/LICENSE-2.0.txt
[27]: http://sonarsource.github.io/sonar-scanner-maven/
[28]: http://www.gnu.org/licenses/lgpl.txt
[29]: https://maven.apache.org/plugins/maven-compiler-plugin/
[30]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[31]: https://www.mojohaus.org/flatten-maven-plugin/
[32]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[33]: http://www.apache.org/licenses/LICENSE-2.0.txt
[34]: https://maven.apache.org/surefire/maven-surefire-plugin/
[35]: https://www.mojohaus.org/versions-maven-plugin/
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
[48]: https://github.com/itsallcode/openfasttrace-maven-plugin
[49]: https://www.gnu.org/licenses/gpl-3.0.html
[50]: https://github.com/exasol/project-keeper/
[51]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[52]: http://maven.apache.org/plugins/maven-clean-plugin/
[53]: http://maven.apache.org/plugins/maven-resources-plugin/
[54]: http://maven.apache.org/plugins/maven-jar-plugin/
[55]: http://maven.apache.org/plugins/maven-install-plugin/
[56]: http://maven.apache.org/plugins/maven-site-plugin/
