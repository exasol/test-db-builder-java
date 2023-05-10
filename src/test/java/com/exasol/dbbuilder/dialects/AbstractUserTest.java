package com.exasol.dbbuilder.dialects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

@SuppressWarnings("java:S5786") // this class should be public as implementation classes are in different packages
public abstract class AbstractUserTest {

    protected abstract User createUser(String name);

    protected abstract User createUser(String name, String password);

    protected abstract DatabaseObjectWriter getWriterMock();

    @Test
    void getType() {
        assertThat(createUser("A").getType(), equalTo("user"));
    }

    @Test
    void testName() {
        assertThat(createUser("JANEDOE").getName(), equalTo("JANEDOE"));
    }

    @Test
    void testHasParent() {
        assertThat(createUser("JOHNDOE").hasParent(), equalTo(false));
    }

    @Test
    void testGetParentThrowsException() {
        final User user = createUser("JOHNDOE");
        assertThrows(DatabaseObjectException.class, user::getParent);
    }

    @Test
    void testGetDefaultPassword() {
        assertThat(createUser("SMITH").getPassword(), equalTo("SMITHPWD"));
    }

    @Test
    void testCreateUserWithPassword() {
        final User user = createUser("USER_WITH_PASSWORD", "THE_PASSWORD");
        assertThat(user.getPassword(), equalTo("THE_PASSWORD"));
    }

    @Test
    void testDrop() {
        final User user = createUser("user");
        user.drop();
        verify(getWriterMock()).drop(same(user));
    }

    @Test
    void testDropTwiceFails() {
        final User user = createUser("user");
        user.drop();
        assertThrows(DatabaseObjectDeletedException.class, user::drop);
    }
}