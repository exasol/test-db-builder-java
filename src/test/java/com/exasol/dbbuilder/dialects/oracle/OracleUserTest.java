package com.exasol.dbbuilder.dialects.oracle;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.dbbuilder.dialects.*;
import com.exasol.dbbuilder.dialects.mysql.MySqlObjectPrivilege;

@ExtendWith(MockitoExtension.class)
class OracleUserTest extends AbstractUserTest {
    @Mock
    private OracleImmediateDatabaseObjectWriter writerMock;

    @Override
    protected User createUser(final String name) {
        return new OracleUser(this.writerMock, OracleIdentifier.of(name));
    }

    @Override
    protected User createUser(final String name, final String password) {
        return new OracleUser(this.writerMock, OracleIdentifier.of(name), password);
    }

    @Override
    protected DatabaseObjectWriter getWriterMock() {
        return writerMock;
    }

    @Test
    void testGetFullyQualifiedName() {
        assertThat(new OracleUser(this.writerMock, OracleIdentifier.of("JOHNDOE")).getFullyQualifiedName(),
                equalTo("`JOHNDOE`"));
    }

    @Test
    void testGetObjectPrivileges(@Mock final DatabaseObject objectMock) {
        final ObjectPrivilege[] expectedObjectPrivileges = { MySqlObjectPrivilege.INSERT, MySqlObjectPrivilege.DELETE };
        final User user = new OracleUser(this.writerMock, OracleIdentifier.of("OBJUSER")) //
                .grant(objectMock, expectedObjectPrivileges);
        assertThat(user.getObjectPrivileges(), hasEntry(objectMock, expectedObjectPrivileges));
    }
}