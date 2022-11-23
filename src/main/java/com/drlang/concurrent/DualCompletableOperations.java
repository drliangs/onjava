package com.drlang.concurrent;

import java.util.concurrent.CompletableFuture;

import static com.drlang.concurrent.CompletableUtilities.*;

public class DualCompletableOperations {
    static CompletableFuture<Workable> cfA, cfB;

    static void init() {
        cfA = Workable.make("A", 0.15);
        cfB = Workable.make("B", 0.10);
    }

    static void join() {
        cfA.join();
        cfB.join();
        System.out.println("************************");
    }

    public static void main(String[] args) {

        init();
        voidr(cfA.runAfterEitherAsync(cfB, () -> System.out.println("runAfterEither")));
        join();

        init();
        voidr(cfA.runAfterBothAsync(cfB, () -> System.out.println("runAfterBoth")));
        join();

        init();
        showr(cfA.applyToEitherAsync(cfB, workable -> {
            System.out.println("applyToEither:" + workable);
            return workable;
        }));
        join();


        init();
        voidr(cfA.acceptEitherAsync(cfB, workable -> {
            System.out.println("acceptEither:" + workable);
        }));
        join();


        init();
        voidr(cfA.thenAcceptBothAsync(cfB, (workable, workable2) -> {
            System.out.println("thenAcceptBoth:" + workable + ", " + workable2);
        }));
        join();


        init();
        showr(cfA.thenCombineAsync(cfB, (w1, w2) -> {
            System.out.println("thenCombine:" + w1 + ",  " + w2);
            return w1;
        }));
        join();


        init();
        CompletableFuture<Workable> cfC = Workable.make("C", 0.08);
        CompletableFuture<Workable> cfD = Workable.make("D", 0.09);
        CompletableFuture.anyOf(cfA, cfB, cfC, cfD).thenRunAsync(()->{
            System.out.println("anyOf");

        });
        join();

        cfC = Workable.make("C",0.08);
        cfD = Workable.make("D",0.09);
        CompletableFuture.allOf(cfA,cfB,cfC,cfD).thenRunAsync(()->{
            System.out.println("allOf");
        });
        join();
    }
}
