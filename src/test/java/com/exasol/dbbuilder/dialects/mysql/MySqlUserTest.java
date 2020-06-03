package com.exasol.dbbuilder.dialects.mysql;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.*;

@ExtendWith(MockitoExtension.class)
class MySqlUserTest {
    private final QuoteApplier quoteApplier = new MySqlQuoteApplier();
    @Mock
    private MySqlImmediateDatabaseObjectWriter writerMock;

    @Test
    void getType() {
        assertThat(new MySqlUser(this.writerMock, this.quoteApplier, "A").getType(), equalTo("user"));
    }

    @Test
    void testName() {
        assertThat(new MySqlUser(this.writerMock, this.quoteApplier, "JANEDOE").getName(), equalTo("JANEDOE"));
    }

    @Test
    void testGetFullyQualifiedName() {
        assertThat(new MySqlUser(this.writerMock, this.quoteApplier, "JOHNDOE").getFullyQualifiedName(),
                equalTo("`JOHNDOE`"));
    }

    @Test
    void testHasParent() {
        assertThat(new MySqlUser(this.writerMock, this.quoteApplier, "JOHNDOE").hasParent(), equalTo(false));
    }

    @Test
    void testGetParentThrowsException() {
        assertThrows(DatabaseObjectException.class,
                () -> new MySqlUser(this.writerMock, this.quoteApplier, "JOHNDOE").getParent());
    }

    @Test
    void testGetDefaultPassword() {
        assertThat(new MySqlUser(this.writerMock, this.quoteApplier, "SMITH").getPassword(), equalTo("SMITHPWD"));
    }

    @Test
    void testGetObjectPrivileges(@Mock final DatabaseObject objectMock) {
        final ObjectPrivilege[] expectedObjectPrivileges = { MySqlObjectPrivilege.INSERT, MySqlObjectPrivilege.DELETE };
        final User user = new MySqlUser(this.writerMock, this.quoteApplier, "OBJUSER") //
                .grant(objectMock, expectedObjectPrivileges);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, expectedObjectPrivileges));
    }

    @Test
    void testGetSystemPrivileges() {
        final User user = new MySqlUser(this.writerMock, this.quoteApplier, "SYTEMUSER") //
                .grant(MySqlGlobalPrivilege.CREATE_ROLE);
        assertThat(user.getGlobalPrivileges(), contains(MySqlGlobalPrivilege.CREATE_ROLE));
    }

    @Test
    void testGrantAllAccess(@Mock final DatabaseObject objectMock) {
        final User user = new MySqlUser(this.writerMock, this.quoteApplier, "OBJSUPERUSER").grantAllAccess(objectMock);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, MySqlObjectPrivilege.values()));
    }

    @Test
    void testCreateUserWithPassword() {
        final User user = new MySqlUser(this.writerMock, this.quoteApplier, "USER_WITH_PASSWORD", "THE_PASSWORD");
        assertThat(user.getPassword(), equalTo("THE_PASSWORD"));
    }
}