package com.drlang.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Safe implements SharedArg{
    private static final AtomicInteger COUNTER = new AtomicInteger();

    @Override
    public int get() {
        return COUNTER.getAndIncrement();
    }
}
