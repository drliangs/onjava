package com.drlang.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        IntStream.range(0, 10)
                .mapToObj(NapTask::new)
                .forEach(executorService::execute);
        System.out.println("All tasks sumbitted");
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            System.out.println(Thread.currentThread().getName() + " awaiting termination");
            NapTask.Nap nap = new NapTask.Nap(0.1);

        }
    }
}
