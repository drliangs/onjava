package com.drlang.lowlevel;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator generator, int id) {
        this.generator = generator;
        this.id = id;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int var = generator.next();
            System.out.println(var);
            if (var % 2 != 0) {
                System.out.println(var + " not event!");
                generator.cancel();
            }
        }
    }

    public static void test(final IntGenerator gp, final int count) {
        IntStream.range(0, count)
                .mapToObj(i -> new EvenChecker(gp, count))
                .map(CompletableFuture::runAsync)
                .toList().forEach(CompletableFuture::join);
    }

    public static void test(IntGenerator gp) {
        new TimedAbort(10,"No odd numbers discovered");
        test(gp, 10);
    }
}
