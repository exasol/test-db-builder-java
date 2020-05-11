# Test DB Builder User Guide

Exasol's Test Database Builder for Java (TDDB) is a framework that helps you write compact and readable integration tests that require setting up a database.

Instead of cluttering your test code with hundreds of lines of boilerplate code, TDDB reduces setup and population of your test database to a handful of easy to read statements. To further increase convenience TDDB supports the [fluent interface](https://en.wikipedia.org/wiki/Fluent_interface) programming style.

## Preparations

The first step of any integration test involving a database is installing that database. In the case of Exasol, we recommend you simplify your life by letting [Exasol Testcontainers](https://github.com/exasol/exasol-testcontainers) do that for you. This will give you a disposable Exasol installation running inside a [Docker](https://www.docker.com/) container. Just the thing you need for integration testing.

Check out the parent project [Test Containers](https://www.testcontainers.org/) to learn more about the framework itself and to see, for which other databases test container modules exist.

## Creating Database Objects

After installing the database you need to set up the structure of your test database and that is the first point where TDDB comes into play.

### The `DatabaseObjectFactory`

The "mother" of all database objects TDDB creates is the `DatabaseObjectFactory`. You need one in order to create any top-level database objects.

Top-level objects are objects in the database which are not scoped by any other objects. A schema for example is a top-level object in Exasol. A table on the other hand lives inside the scope of a schema.

You create a `DatabaseObjectFactory` by choosing an implementation of the concrete database and feeding it with a JDBC connection.

In the example below you see how to create an `ExasolObjectFactory` with a JDBC connection that you created earlier:

```java
final DatabaseObjectFactory factory = new ExasolObjectFactory(connection);
```

That is about as much configuration as you need to get started.

### Creating Schemas

In almost all your integration tests, you will need a schema to hold other objects. At least if you are testing against and Exasol database, so let's start with that.

```java
final Schema schema = factory.createSchema(`GAME_STATISTICS`);
```

### Creating Tables

In Exasol, tables are scoped inside of schemas, so we create a table object from a schema object.

```java
final Table table = schema.createTable("DAYS", "DAY_NAME", "VARCHAR(9), "SHORT_NAME", "VARCHAR(3)");
```

In case you want to create more complex tables, you can also use a builder.

```java
final Table table = schema.createTableBuilder("DAYS")
        .column("DAY_NAME", "VARCHAR(9)"
        .column("SHORT_NAME", "VARCHAR(3)"
        .column("DAY_IN_WEEK", "DECIMAL(1,0)"
        // ...
        .build()
```

### Creating Users

If you want to create users &mdash; for example when testing privilege effects &mdash; simply type:

```java
final User user = factory.createUser("MARIA")
```

#### Granting System Privileges

Sometimes you need to promote one of your users to administrator on a database-wide scale by giving them system privileges. This is as easy as:

```
user.grant(SystemPrivilege.CREATE_USER, SystemPrivilege.DROP_USER);
```

Of course you can use a static import of `SystemPrivilege` to make that code more compact if you wish.

#### Granting Object Privileges

All database objects in TDDB implement the `DataObject` interface. If you want to grant users privileges on the object level, follow this procedure:

```
final Schema schema = factory.createSchema("SALES");
user.grant(schema, ObjectPrivilege.SELECT, ObjectPrivilege.INSERT)
```

#### Creating an Exasol User who can log in

In most integration tests you need user accounts able to log in. In Exasol for example the user needs the system privilege `CREATE SESSION` in order to log in.

So the following code

```java
final User user = factory.createLoginUser("MIKE");
```

in Exasol is equivalent to:

```java
final User user = factory.createUser("MIKE").grant(SystemPrivilege.CREATE_SESSION);
```

### Creating Connection Definitions

Whenever you want to store URLs and / or credentials securely in Exasol, you create a connection definition. You need this for example in case you want to connect to another database or a service via the network in a script.

You can create a definition with only a URL or with additional credentials.

Here are the two variants

```java
final Connection connectionA = factory.createConnection("PUBLIC_CONNECTION", "https://example.org/api/v1");
final Connection connectionB = factory.createConnection("PRIVILEGED_CONNECTION", "https://example.org/api/v1", "FRED", "super secret!");
```

As always the first parameter is the object name of the connection definition. Then there is the URL (e.g. a JDBC URL) and optionally username and password.

### Creating Scripts

Scripts are the main extension point for end-users. In Exasol you can for example define a Lua script like this:

```java
final Script script = schema.createScript("HELLO_LUA", "print(\"Hello World\")");
```

### Creating Adapter Scripts

Adapter Scripts are what drive Virtual Schema adapters. They are scoped inside a schema. 

A basic adapter script definition consists of three parts:

* Adapter name
* Programming language definition
* Script content

Accordingly the creation of an adapter script looks like this:

```sql
final AdapterScript adapterScript = schema.createAdapterScript("HELLO_WORLD", "PYTHON", "print \"Hello World\"");
```

### Creating Virtual Schemas

Virtual Schemas have lots of parameters when you create them. That's why you need a builder in order to make one via the TDDB.

```sql
final VirtualSchema virtualSchema = factory.createVirtualSchemaBuilder("THE_VIRTUAL_SCHEMA")
        .dialectName("Exasol")
        .adapterScript(adapterScript)
        .connectionDefinition(connectionDefinition)
        .properties(Map.of("IS_LOCAL", "true"
                           "LOG_LEVEL", "ALL"))
        .build();
```

## Populating Tables

Populating a table is really simple:

```java
table.insert("Monday", "Mon")
     .insert("Tuesday", "Tue")
     .insert("Wednesday", "Wed")
     // ...
     .insert("Sunday", "Sun");
```

One thing to keep in mind here is that the TDDB's main design goal is expressiveness, not ultimate speed. While this approach here is perfectly suited for the functional integration tests, populating tables with mass data for performance testing is better done using Exasol's `IMPORT` statement.
