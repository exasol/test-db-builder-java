package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.*;

// [utest->dsn~creating-database-users~1]
@ExtendWith(MockitoExtension.class)
class ExasolUserTest extends AbstractUserTest {
    private final QuoteApplier quoteApplier = new ExasolQuoteApplier();
    @Mock
    private ExasolImmediateDatabaseObjectWriter writerMock;

    @Override
    protected User createUser(String name) {
        return new ExasolUser(this.writerMock, this.quoteApplier, name);
    }

    @Override
    protected User createUser(String name, String password) {
        return new ExasolUser(this.writerMock, this.quoteApplier, name, password);
    }

    @Test
    void testGetFullyQualifiedName() {
        assertThat(new ExasolUser(this.writerMock, this.quoteApplier, "JOHNDOE").getFullyQualifiedName(),
                equalTo("\"JOHNDOE\""));
    }

    @Test
    void testGetObjectPrivileges(@Mock final DatabaseObject objectMock) {
        final ObjectPrivilege[] expectedObjectPrivileges = { ExasolObjectPrivilege.INSERT,
                ExasolObjectPrivilege.DELETE };
        final User user = new ExasolUser(this.writerMock, this.quoteApplier, "OBJUSER") //
                .grant(objectMock, expectedObjectPrivileges);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, expectedObjectPrivileges));
    }

    @Test
    // [utest->dsn~granting-system-privileges-to-database-users~1]
    void testGetSystemPrivileges() {
        final User user = new ExasolUser(this.writerMock, this.quoteApplier, "SYTEMUSER") //
                .grant(ExasolGlobalPrivilege.CREATE_SESSION);
        assertThat(user.getGlobalPrivileges(), contains(ExasolGlobalPrivilege.CREATE_SESSION));
    }

    @Test
    void testGrantAllAccess(@Mock final DatabaseObject objectMock) {
        final User user = new ExasolUser(this.writerMock, this.quoteApplier, "OBJSUPERUSER").grantAllAccess(objectMock);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, ExasolObjectPrivilege.values()));
    }
}