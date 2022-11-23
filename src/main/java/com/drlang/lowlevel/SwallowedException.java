package com.drlang.lowlevel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SwallowedException {
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(()->{
            throw new RuntimeException();
        });
        executorService.shutdown();
    }
}
