package com.drlang.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class InterferingTask implements Runnable{
    final  int id;
    private  static Integer val  = 0 ;

    public InterferingTask(int id) {
        this.id = id;
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            val++;
        }
        System.out.println(id + " " + Thread.currentThread().getName() + "  " + val);

    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        IntStream.range(0,10)
                .mapToObj(InterferingTask::new)
                .forEach(executorService::execute);
        executorService.shutdown();
    }
}
