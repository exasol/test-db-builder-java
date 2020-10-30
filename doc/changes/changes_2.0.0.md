# Test Database Builder 2.0.0, released 2020-XX-XX

Code name: 

## Refactoring

* #40: Refactored debugger connection to global JVM options
   This change caused an API change for adding a debugger. 
   See the [user guide](../user_guide/user_guide.md). 
* #42: UDF support
    For supporting UDFs we unified the API for of Script, Udf and Adapter scripts.
    We changed `Schema#createAdapterScriptBuilder()` into `Schema#createAdapterScriptBuilder(name)`
    and removed `AdapterScript.Builder#name(name)`.
    We also removed `AdapterScript.Builder#parentSchema(schema)` and `AdapterScript.Builder#writer(writer)` but these methods should not be used from outside anyway.  
    
## Dependency Updates

* Updated `com.exasol:exasol-jdbc` from 6.2.5 to 7.0.3
* Updated `mysql:mysql-connector-java` from 8.0.20 to 8.0.22
* Updated `com.exasol:db-fundamentals-java` from 0.1.0 to 0.1.1
* Updated `com.exasol:exasol-testcontainers` from 2.0.3 to 3.2.0
* Updated `com.exasol:hamcrest-resultset-matcher` from 1.1.1 to 1.2.1
* Updated `org.mockito:mockito-junit-jupiter` from 3.3.3 to 3.6.0
* Updated `nl.jqno.equalsverifier:equalsverifier` from 3.4.1 to 3.5
