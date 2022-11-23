package com.drlang.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class StaticIDField implements HasId {
    private static AtomicInteger counter = new AtomicInteger();
    private int id  = counter.getAndIncrement();
    @Override
    public int getID() {
        return id;
    }

}
