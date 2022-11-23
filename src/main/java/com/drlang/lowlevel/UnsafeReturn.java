package com.drlang.lowlevel;

public class UnsafeReturn extends IntTestable {
    private int i = 0;


    @Override
    public synchronized void evenIncrement() {
        i++;
        i++;
    }

    @Override
    public synchronized int getAsInt() {
        return i;
    }

    public static void main(String[] args) {
        Atomicity.test(new UnsafeReturn());
    }
}
