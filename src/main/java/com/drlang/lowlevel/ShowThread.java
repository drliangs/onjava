package com.drlang.lowlevel;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ShowThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Runtime.getRuntime().availableProcessors());
        ForkJoinPool exec = (ForkJoinPool) Executors.newWorkStealingPool();
        IntStream.range(0, 10).mapToObj(n -> new ShowThread()).forEach(exec::execute);
        exec.awaitTermination(1, TimeUnit.SECONDS);
    }
}
