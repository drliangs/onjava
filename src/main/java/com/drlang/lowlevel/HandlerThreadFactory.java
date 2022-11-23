package com.drlang.lowlevel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class HandlerThreadFactory implements ThreadFactory {
   private static final MyUnCaughtExceptionHandler MY_UNCaughtExceptionHandler = new MyUnCaughtExceptionHandler();

    @Override
    public Thread newThread(final Runnable r) {
        System.out.println(this + " creating new thread");
        Thread thread = new Thread(r);
        System.out.println("created " + thread);
        thread.setUncaughtExceptionHandler(MY_UNCaughtExceptionHandler);
        System.out.println("eh = " + thread.getUncaughtExceptionHandler());
        return thread;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executorService.execute(new ExceptionThread2());
        executorService.shutdown();
    }
}
