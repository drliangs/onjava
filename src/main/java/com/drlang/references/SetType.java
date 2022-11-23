package com.drlang.references;

import java.util.Objects;

public class SetType {
    protected int i;

    public SetType(int n) {
        this.i = n;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof SetType && Objects.equals(i, ((SetType) o).i);
    }

    @Override
    public String toString() {
        return Integer.toString(i);
    }
}

