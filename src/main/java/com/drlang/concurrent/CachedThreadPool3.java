package com.drlang.concurrent;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CachedThreadPool3 {
    public static <T> T  extractResult(Future<T> f) {
        try {
            return f.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    public static <T> T exCall(Callable<T> f){

        try {
            return f.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<CountingTask> collect = IntStream.range(0, 10).
                mapToObj(CountingTask::new).toList();
        List<Future<Integer>> futures = executorService.invokeAll(collect);
        Integer reduce = futures.stream().
                map(CachedThreadPool3::extractResult).
                reduce(0, Integer::sum);
        System.out.println("Sum" + reduce);
        executorService.shutdown();
    }
}
