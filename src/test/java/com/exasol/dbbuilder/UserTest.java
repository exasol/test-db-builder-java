package com.exasol.dbbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.objectwriter.DatabaseObjectWriter;

@ExtendWith(MockitoExtension.class)
class UserTest {
    @Mock
    private DatabaseObjectWriter writerMock;

    @Test
    void getType() {
        assertThat(new User(this.writerMock, "A").getType(), equalTo("user"));
    }

    @Test
    void testName() {
        assertThat(new User(this.writerMock, "JANEDOE").getName(), equalTo("JANEDOE"));
    }

    @Test
    void testGetFullyQualifiedName() {
        assertThat(new User(this.writerMock, "JOHNDOE").getFullyQualifiedName(), equalTo("\"JOHNDOE\""));
    }

    @Test
    void testHasParent() {
        assertThat(new User(this.writerMock, "JOHNDOE").hasParent(), equalTo(false));
    }

    @Test
    void testGetParentThrowsException() {
        assertThrows(DatabaseObjectException.class, () -> new User(this.writerMock, "JOHNDOE").getParent());
    }

    @Test
    void testGetDefaultPassword() {
        assertThat(new User(this.writerMock, "SMITH").getPassword(), equalTo("SMITHPWD"));
    }

    @Test
    void testGetObjectPrivileges(@Mock final DatabaseObject objectMock) {
        final ObjectPrivilege[] expectedObjectPrivileges = { ObjectPrivilege.INSERT, ObjectPrivilege.DELETE };
        final User user = new User(this.writerMock, "OBJUSER") //
                .grant(objectMock, expectedObjectPrivileges);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, expectedObjectPrivileges));
    }

    @Test
    void testGetSystemPrivileges() {
        final User user = new User(this.writerMock, "SYTEMUSER") //
                .grant(SystemPrivilege.CREATE_SESSION);
        assertThat(user.getSystemPrivileges(), contains(SystemPrivilege.CREATE_SESSION));
    }

    @Test
    void testGrantAllAccess(@Mock final DatabaseObject objectMock) {
        final User user = new User(this.writerMock, "OBJSUPERUSER").grantAllAccess(objectMock);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, ObjectPrivilege.values()));
    }
}