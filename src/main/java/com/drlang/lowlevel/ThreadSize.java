package com.drlang.lowlevel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSize extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        int count =0;
        try {
            while (true){
                exec.execute(new ThreadSize());
                count++;
            }
        }catch (Error e){
            System.out.println(e.getClass().getSimpleName() + ":  "  +count);
        }finally {
            exec.shutdown();
        }
    }
}
