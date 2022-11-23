package com.drlang.concurrent;

public class Unsafe implements SharedArg{
    private int  i=0;
    @Override
    public int get() {
        return i++;
    }
}
