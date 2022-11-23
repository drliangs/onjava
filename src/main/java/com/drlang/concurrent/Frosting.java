package com.drlang.concurrent;

import java.util.concurrent.CompletableFuture;

public class Frosting {
    private Frosting() {
    }

    static CompletableFuture<Frosting> make() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(new Frosting());
    }

    public static class FrostedCake {
        public FrostedCake(Baked baked, Frosting frosting) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        Baked.batch().forEach(boked -> boked.thenCombineAsync(Frosting.make(), FrostedCake::new).thenAcceptAsync(System.out::println));
    }
}
