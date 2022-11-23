package com.drlang.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuittableTask implements Runnable {
    final int id;
    private final AtomicBoolean RUNNING = new AtomicBoolean(true);

    public void quit() {
        RUNNING.set(false);
    }

    public QuittableTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (RUNNING.get()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(id + " ");
    }

    private static class Test{
        public static final int COUNT =150;
        public static void main(String[] args) throws InterruptedException {
            List<QuittableTask> collect = IntStream.range(1, COUNT).mapToObj(QuittableTask::new)
                    .toList();
            List<CompletableFuture<Void>> completableFutures = collect.stream().
                    map(CompletableFuture::runAsync).toList();
            collect.forEach(QuittableTask::quit);
            completableFutures.forEach(CompletableFuture::join);
        }
    }
}
