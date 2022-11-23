package com.drlang.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class CachedThreadPool{
    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        IntStream.range(0,10)
                .mapToObj(NapTask::new)
                .forEach(pool::execute);
        pool.shutdown();
    }
}
