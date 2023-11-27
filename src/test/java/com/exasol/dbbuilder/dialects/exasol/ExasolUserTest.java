package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.db.ExasolIdentifier;
import com.exasol.dbbuilder.dialects.*;

// [utest->dsn~creating-database-users~1]
@ExtendWith(MockitoExtension.class)
class ExasolUserTest extends AbstractUserTest {
    private static final String PASSWORD = "pwd";
    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;

    @Override
    protected User createUser(final String name, final String password) {
        return new ExasolUser(this.writerMock, ExasolIdentifier.of(name), password);
    }

    @Override
    protected DatabaseObjectWriter getWriterMock() {
        return writerMock;
    }

    @Test
    void testGetFullyQualifiedName() {
        assertThat(testee("JOHNDOE").getFullyQualifiedName(), equalTo("\"JOHNDOE\""));
    }

    @Test
    void testGetObjectPrivileges(@Mock final DatabaseObject objectMock) {
        final ObjectPrivilege[] expectedObjectPrivileges = { ExasolObjectPrivilege.INSERT,
                ExasolObjectPrivilege.DELETE };
        final User user = testee("OBJUSER").grant(objectMock, expectedObjectPrivileges);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, expectedObjectPrivileges));
    }

    @Test
    // [utest->dsn~granting-system-privileges-to-database-users~1]
    void testGetSystemPrivileges() {
        final User user = testee("SYTEMUSER").grant(ExasolGlobalPrivilege.CREATE_SESSION);
        assertThat(user.getGlobalPrivileges(), contains(ExasolGlobalPrivilege.CREATE_SESSION));
    }

    @Test
    void testGrantAllAccess(@Mock final DatabaseObject objectMock) {
        final User user = testee("OBJSUPERUSER").grantAllAccess(objectMock);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, ExasolObjectPrivilege.values()));
    }

    private ExasolUser testee(final String name) {
        return new ExasolUser(this.writerMock, ExasolIdentifier.of(name), PASSWORD);
    }
}
