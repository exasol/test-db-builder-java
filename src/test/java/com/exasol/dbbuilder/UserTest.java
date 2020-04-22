package com.exasol.dbbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

@ExtendWith(MockitoExtension.class)
public class UserTest {
    @Mock
    private DatabaseObjectWriter writerMock;

    @Test
    public void getType() {
        assertThat(new User(this.writerMock, "A").getType(), equalTo("user"));
    }

    @Test
    public void testName() {
        final User user = new User(this.writerMock, "JANEDOE");
        assertThat(user.getFullyQualifiedName(), equalTo("JANEDOE"));
    }

    @Test
    public void testGetFullyQualifiedName() {
        final User user = new User(this.writerMock, "JOHNDOE");
        assertThat(user.getFullyQualifiedName(), equalTo("JOHNDOE"));
    }

    @Test
    public void testGetDefaultPassword() {
        final User user = new User(this.writerMock, "SMITH");
        assertThat(user.getPassword(), equalTo("SMITHPWD"));
    }

    @Test
    public void testGetObjectPrivileges(@Mock final DatabaseObject objectMock) {
        final ObjectPrivilege[] expectedObjectPrivileges = { ObjectPrivilege.INSERT, ObjectPrivilege.DELETE };
        final User user = new User(this.writerMock, "OBJUSER") //
                .grantAccess(objectMock, expectedObjectPrivileges);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, expectedObjectPrivileges));
    }

    @Test
    public void testGetSystemPrivileges() {
        final User user = new User(this.writerMock, "SYTEMUSER") //
                .grantSystemPrivilege(SystemPrivilege.SESSION);
        assertThat(user.getSystemPrivileges(), contains(SystemPrivilege.SESSION));
    }

    @Test
    public void testGrantAllAccess(@Mock final DatabaseObject objectMock) {
        final User user = new User(this.writerMock, "OBJSUPERUSER").grantAllAccess(objectMock);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, ObjectPrivilege.values()));
    }
}