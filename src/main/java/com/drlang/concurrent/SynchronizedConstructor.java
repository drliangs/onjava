package com.drlang.concurrent;

public class SynchronizedConstructor {
    public static void main(String[] args) {
        Unsafe unsafe = new Unsafe();
        IDChecker.test(()->new SyncConstructor(unsafe));
    }
}
