# Test DB Builder User Guide

Coming soon: https://github.com/exasol/test-db-builder-java/issues/4

## Creating Users

### Creating a User who can log in

In most integration tests you need need user accounts able to log in. In Exasol for example the user needs the system privilege `CREATE SESSION` in order to log in.

So the following code

```java
final User user = factory.createLoginUser("MIKE");
```

in Exasol is equivalent to:

```java
final User user = factory.createUser("MIKE").grant(SystemPrivilege.CREATE_SESSION);
```