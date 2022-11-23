package com.drlang.concurrent;

public class SyncFactory implements HasId {
    private final int id;

    public SyncFactory(SharedArg id) {
        this.id = id.get();
    }

    @Override
    public int getID() {
        return id;
    }

    public static synchronized SyncFactory factory(SharedArg sa) {
        return new SyncFactory(sa);
    }

    public static void main(String[] args) {
        Unsafe unsafe = new Unsafe();
        IDChecker.test(() -> SyncFactory.factory(unsafe));
    }
}
