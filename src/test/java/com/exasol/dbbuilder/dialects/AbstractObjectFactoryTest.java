package com.exasol.dbbuilder.dialects;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

public abstract class AbstractObjectFactoryTest {

    abstract protected AbstractImmediateDatabaseObjectWriter getWriterMock();

    abstract protected DatabaseObjectFactory testee();

    @Test
    void createSchemaWritesObject() {
        final Schema schema = testee().createSchema("schema");
        verify(getWriterMock()).write(same(schema));
    }

    @Test
    void createUserWritesObject() {
        final User user = testee().createUser("user");
        verify(getWriterMock()).write(same(user));
    }

    @Test
    void createUserWithPasswordWritesObject() {
        final User user = testee().createUser("user", "password");
        verify(getWriterMock()).write(same(user));
    }

    @Test
    void createLoginUserWritesObject() {
        final User user = testee().createLoginUser("user");
        verify(getWriterMock()).write(same(user));
    }

    @Test
    void createLoginUserWithPasswordWritesObject() {
        final User user = testee().createLoginUser("user", "password");
        verify(getWriterMock()).write(same(user));
    }
}
