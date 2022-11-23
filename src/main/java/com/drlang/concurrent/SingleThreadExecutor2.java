package com.drlang.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class SingleThreadExecutor2 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        IntStream.range(0,10).mapToObj(NapTask::new).forEach(executor::execute);
        executor.shutdownNow();
        try {
            executor.submit(()-> System.out.println("z"));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
