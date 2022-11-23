package com.drlang.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountedCompleter;

public class Batter {
    static class Eggs {
    }

    static class Milk {
    }

    static class Sugar {
    }

    static class Flour {
    }

    static <T> T perPare(T ingredinet) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ingredinet;
    }

    static <T> CompletableFuture<T> prep(T ingredinet) {
        return CompletableFuture.completedFuture(ingredinet)
                .thenApplyAsync(Batter::perPare);

    }

    public static CompletableFuture<Batter> mix() {

        CompletableFuture.allOf(prep(new Eggs()), prep(new Milk())
                , prep(new Flour())
                , prep(new Sugar())
        ).join();
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(new Batter());
    }

}
