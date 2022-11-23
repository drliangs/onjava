package com.drlang.lowlevel;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DualSynch {

    ConcurrentLinkedQueue<String> trace = new ConcurrentLinkedQueue<>();

    private final Object syncObject = new Object();

    public synchronized void f(boolean nap) {
        for (int i = 0; i < 5; i++) {
            trace.add(String.format("f() " + i));
            if (nap) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void g(boolean nap) {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                trace.add(String.format("g()  " + i));
                if (nap) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static  class SyncOnObject {
        static void test(boolean fnap, boolean gNap) {
            DualSynch ds = new DualSynch();
            Arrays.stream(new Runnable[]{
                    ()-> ds.f(fnap),()->ds.g(gNap)
            }).map(CompletableFuture::runAsync).
                    forEach(CompletableFuture::join);
            ds.trace.forEach(System.out::println);
        }

        public static void main(String[] args) {
            test(true,false);
            System.out.println("************");
            test(false,true);
        }
    }




}
