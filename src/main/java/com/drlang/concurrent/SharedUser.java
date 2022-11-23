package com.drlang.concurrent;

import java.util.function.Supplier;

public class SharedUser implements HasId{
    private final int id ;

    public SharedUser(SharedArg id) {
        this.id = id.get();
    }

    @Override
    public int getID() {
        return id;
    }
}
