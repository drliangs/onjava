package com.drlang.lowlevel;

public class MyUnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
        System.out.println(t);
    }
}
