package com.drlang.lowlevel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public abstract class Guarder {
    AtomicLong callCount = new AtomicLong();

    public abstract void method() throws Exception;

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":  " + callCount.get();
    }

    public static class SynchronizedMethod extends Guarder {

        @Override
        public void method() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            callCount.incrementAndGet();
        }
    }

    public static class CriticalSection extends Guarder {

        @Override
        public void method() throws InterruptedException {
            Thread.sleep(100);
            synchronized (this) {
                callCount.incrementAndGet();
            }
        }

        @Override
        public String toString() {
            return super.toString() + "z";
        }
    }

    public static class Caller implements Runnable {
        private final Guarder g;
        private final AtomicLong successfulCalls = new AtomicLong();
        private final AtomicBoolean stop = new AtomicBoolean(false);

        public Caller(Guarder g) {
            this.g = g;
        }

        @Override
        public void run() {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    stop.set(true);
                }
            }, 2500);
            while (!stop.get()) {
                try {
                    g.method();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                successfulCalls.getAndIncrement();
            }
            System.out.println("-> " + successfulCalls.get());
        }
    }
    public static class SynchronizedComparison{
        public  static void test(Guarder g){
            Stream.of(new Caller(g),new Caller(g),new Caller(g),new Caller(g))
                    .map(CompletableFuture::runAsync)
                    .forEach(CompletableFuture::join);
            System.out.println(g);
        }

        public static void main(String[] args) {
            test(new CriticalSection());
            test(new SynchronizedMethod());
        }
    }


}
