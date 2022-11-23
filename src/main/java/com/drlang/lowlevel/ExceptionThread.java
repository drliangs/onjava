package com.drlang.lowlevel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExceptionThread implements Runnable{


    @Override
    public void run() {


        throw new RuntimeException();



    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            executorService.execute(new ExceptionThread());

        }catch (Exception e){
            System.out.println("Exception was handled!");
        }

        executorService.shutdown();
    }
}

