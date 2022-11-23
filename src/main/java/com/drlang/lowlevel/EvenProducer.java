package com.drlang.lowlevel;

public class EvenProducer extends IntGenerator{
    private volatile int currentEvenValue = 0;
    @Override
    public int next() {
        int cur = currentEvenValue;
        ++cur;
        ++cur;
        currentEvenValue = cur;
        return cur;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenProducer());
    }
}
