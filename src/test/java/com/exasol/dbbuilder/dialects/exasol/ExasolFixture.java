package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.containers.ExasolContainer;

class ExasolFixture {
    @SuppressWarnings("resource") // Must be closed by caller
    static ExasolContainer<? extends ExasolContainer<?>> create() {
        return new ExasolContainer<>("2025.2.1").withReuse(true);
    }
}
