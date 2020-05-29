package com.exasol.dbbuilder.dialects.exasol;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

class ExasolGlobalPrivilegeTest {
    @Test
    void testToString() {
        assertThat(ExasolGlobalPrivilege.CREATE_SESSION.renderedName(), equalTo("CREATE SESSION"));
    }
}