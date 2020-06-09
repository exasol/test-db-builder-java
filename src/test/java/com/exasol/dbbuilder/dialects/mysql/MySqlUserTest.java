package com.exasol.dbbuilder.dialects.mysql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.*;

@ExtendWith(MockitoExtension.class)
class MySqlUserTest extends AbstractUserTest {
    @Mock
    private MySqlImmediateDatabaseObjectWriter writerMock;

    @Override
    protected User createUser(final String name) {
        return new MySqlUser(this.writerMock, name);
    }

    @Override
    protected User createUser(final String name, final String password) {
        return new MySqlUser(this.writerMock, name, password);
    }

    @Test
    void testGetFullyQualifiedName() {
        assertThat(new MySqlUser(this.writerMock, "JOHNDOE").getFullyQualifiedName(), equalTo("`JOHNDOE`"));
    }

    @Test
    void testGetObjectPrivileges(@Mock final DatabaseObject objectMock) {
        final ObjectPrivilege[] expectedObjectPrivileges = { MySqlObjectPrivilege.INSERT, MySqlObjectPrivilege.DELETE };
        final User user = new MySqlUser(this.writerMock, "OBJUSER") //
                .grant(objectMock, expectedObjectPrivileges);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, expectedObjectPrivileges));
    }

    @Test
    void testGetSystemPrivileges() {
        final User user = new MySqlUser(this.writerMock, "SYTEMUSER") //
                .grant(MySqlGlobalPrivilege.CREATE_ROLE);
        assertThat(user.getGlobalPrivileges(), contains(MySqlGlobalPrivilege.CREATE_ROLE));
    }

    @Test
    void testGrantAllAccess(@Mock final DatabaseObject objectMock) {
        final User user = new MySqlUser(this.writerMock, "OBJSUPERUSER").grantAllAccess(objectMock);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, MySqlObjectPrivilege.values()));
    }
}