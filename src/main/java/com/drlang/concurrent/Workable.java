package com.drlang.concurrent;

import java.util.concurrent.CompletableFuture;

public class Workable {
    String id;
    final double duration;

    public Workable(String id, double duration) {
        this.id = id;
        this.duration = duration;

    }

    public String toString() {
        return "Workable[" + id + "]";
    }

    public static Workable work(Workable tt) {
        try {
            Thread.sleep((long) tt.duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tt.id = tt.id + "W";
        System.out.println(tt);
        return tt;
    }

    public static CompletableFuture<Workable> make(String id, double duration) {
        return CompletableFuture.completedFuture(new Workable(id, duration))
                .thenApplyAsync(Workable::work);
    }


}
