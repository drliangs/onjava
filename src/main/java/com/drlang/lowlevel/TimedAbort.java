package com.drlang.lowlevel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class TimedAbort {
    private volatile boolean restart = true;

    public TimedAbort(double t, String msg) {
        CompletableFuture.runAsync(() -> {
            while (restart) {
                restart = false;
                try {
                    TimeUnit.MILLISECONDS.sleep((int) (1000 * t));
                } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                }
            }
            System.out.println(msg);
            System.exit(0);
        });
    }
    public TimedAbort(double t){
        this(t,"TimedAbort " + t);
    }
    public void restart(){
        restart = true;
    }

    public static void main(String[] args) throws InterruptedException {
        new TimedAbort(1);
        System.out.println("Napping for 4");
        Thread.sleep(4000);
    }
}
