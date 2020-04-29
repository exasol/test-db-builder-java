package com.exasol.dbbuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

class SystemPrivilegeTest {
    @Test
    void testToString() {
        assertThat(SystemPrivilege.CREATE_SESSION.toString(), equalTo("CREATE SESSION"));
    }
}