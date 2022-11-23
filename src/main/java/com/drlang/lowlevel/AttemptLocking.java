package com.drlang.lowlevel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed(){
        boolean captured = lock.tryLock();
        try {
            System.out.println("tryLock(): " + captured);
        }finally {
            if (captured){
                lock.unlock();
            }
        }
    }
    public void timed(){
        boolean captured;
        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
        }finally {
            if (captured){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AttemptLocking attemptLocking = new AttemptLocking();
        attemptLocking.untimed();
        attemptLocking.timed();
        CompletableFuture.runAsync(()->{
            attemptLocking.lock.lock();
            System.out.println("acquired");
        });
        Thread.sleep(100);
        attemptLocking.untimed();
        attemptLocking.timed();
    }


}
