package com.drlang.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LambdasAndMethodReferences {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> System.out.println("Lambda1"));
        executorService.submit(new NotRunnable()::go);
        Future<Integer> lambda2 = executorService.submit(() -> {
            System.out.println("Lambda2");
            return 1;
        });
        Future<Integer> submit = executorService.submit(new NotCallable()::get);

    }

    private static class NotRunnable {
        public void go() {
            System.out.println("NotRunnable");
        }
    }

    private static class NotCallable {
        public Integer get() {
            System.out.println("NotCallable");
            return 1;
        }
    }
}
