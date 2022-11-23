package com.drlang.lowlevel;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEventProducer extends IntGenerator{
    private AtomicInteger currentEventValue = new AtomicInteger(0);

    @Override
    public int next() {
        return currentEventValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEventProducer());
    }
}
