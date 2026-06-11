package com.exasol.dbbuilder.dialects.exasol;

import com.exasol.containers.ExasolContainer;

class ExasolFixture {
    @SuppressWarnings("resource") // Must be closed by caller
    static ExasolContainer<? extends ExasolContainer<?>> create() {
        return new ExasolContainer<>("2026.1.0").withReuse(true);
    }
}
