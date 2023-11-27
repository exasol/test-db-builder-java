package com.exasol.dbbuilder.dialects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.exasol.dbbuilder.dialects.exasol.ExasolGlobalPrivilege;
import com.exasol.dbbuilder.dialects.exasol.ExasolObjectPrivilege;

@SuppressWarnings("java:S5786") // this class should be public as implementation classes are in different packages
public abstract class AbstractUserTest {
    private static final String PASSWORD = "pwd";

    protected abstract User createUser(String name, String password);

    protected abstract DatabaseObjectWriter getWriterMock();

    @Test
    void constructorDoesNotCallWriter() {
        createUser("name");
        verify(getWriterMock(), never()).write(any(User.class));
    }

    private User createUser(final String name) {
        return createUser(name, PASSWORD);
    }

    @Test
    void constructorWithPasswordDoesNotCallWriter() {
        createUser("name", "password");
        verify(getWriterMock(), never()).write(any(User.class));
    }

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
        assertThat(createUser("SMITH").getPassword(), equalTo(PASSWORD));
    }

    @Test
    void testCreateUserWithPassword() {
        final User user = createUser("USER_WITH_PASSWORD", "THE_PASSWORD");
        assertThat(user.getPassword(), equalTo("THE_PASSWORD"));
    }

    @Test
    void testGetObjectPrivilegesReturnsUnmodifiableMap() {
        final User user = createUser("user");
        final Map<DatabaseObject, ObjectPrivilege[]> privileges = user.getObjectPrivileges();
        final ObjectPrivilege[] priv = new ObjectPrivilege[0];
        assertThrows(UnsupportedOperationException.class, () -> privileges.put(user, priv));
    }

    @Test
    void testGetGlobalPrivilegesReturnsUnmodifiableMap() {
        final User user = createUser("user");
        final Set<GlobalPrivilege> privileges = user.getGlobalPrivileges();
        assertThrows(UnsupportedOperationException.class,
                () -> privileges.add(ExasolGlobalPrivilege.ACCESS_ANY_CONNECTION));
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

    @Test
    void testGrantSystemPrivilegeFailsForDeletedUser() {
        final User user = createUser("user");
        user.drop();
        assertThrows(DatabaseObjectDeletedException.class,
                () -> user.grant(ExasolGlobalPrivilege.ACCESS_ANY_CONNECTION));
        assertThat(user.getGlobalPrivileges(), empty());
    }

    @Test
    void testGrantObjectPrivilegeFailsForDeletedUser() {
        final User user = createUser("user");
        user.drop();
        assertThrows(DatabaseObjectDeletedException.class, () -> user.grant(user, ExasolObjectPrivilege.ALTER));
        assertThat(user.getObjectPrivileges(), anEmptyMap());
    }

    @Test
    void testGrantAllAccessFailsForDeletedUser() {
        assumeGrantAllAccessSupported();
        final User user = createUser("user");
        user.drop();
        assertThrows(DatabaseObjectDeletedException.class, () -> user.grantAllAccess(user));
        assertThat(user.getObjectPrivileges(), anEmptyMap());
    }

    private void assumeGrantAllAccessSupported() {
        assumeTrue(supportsGrantAllAccess());
    }

    private boolean supportsGrantAllAccess() {
        final User user = createUser("user");
        try {
            user.grantAllAccess(user);
            return true;
        } catch (final UnsupportedOperationException exception) {
            return false;
        }
    }
}
