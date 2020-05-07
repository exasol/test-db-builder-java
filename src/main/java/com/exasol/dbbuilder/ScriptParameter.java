package com.exasol.dbbuilder;

import java.util.Objects;

import javax.annotation.processing.Generated;

/**
 * Parameters in database scripts.
 */
public class ScriptParameter {
    private final String name;
    private final boolean array;

    /**
     * Create a new {@link ScriptParameter}.
     *
     * @param name  name of the parameter
     * @param array {@code true} if the parameter contains an array
     */
    public ScriptParameter(final String name, final boolean array) {
        this.name = name;
        this.array = array;
    }

    /**
     * Get the name of the script parameter.
     *
     * @return name of the parameter
     */
    public String getName() {
        return this.name;
    }

    /**
     * Check whether the parameter contains an array.
     *
     * @return {@code true} if the parameter contains an array
     */
    public boolean isArray() {
        return this.array;
    }

    @Generated("org.eclipse.Eclipse")
    @Override
    public int hashCode() {
        return Objects.hash(this.array, this.name);
    }

    @Generated("org.eclipse.Eclipse")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScriptParameter)) {
            return false;
        }
        final ScriptParameter other = (ScriptParameter) obj;
        return (this.array == other.array) && Objects.equals(this.name, other.name);
    }
}