package com.drlang.concurrent;

public class SyncConstructor implements HasId {

    private final int id;
    private static Object constructorLock = new Object();

    public SyncConstructor(SharedArg id) {
        synchronized (constructorLock) {
            this.id = id.get();
        }
    }

    @Override
    public int getID() {
        return id;
    }

}
