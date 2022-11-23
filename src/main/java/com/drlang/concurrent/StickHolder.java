package com.drlang.concurrent;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;

public class StickHolder {
    private static class Chopstick {
    }

    private Chopstick stick = new Chopstick();
    private BlockingQueue<Chopstick> holder = new ArrayBlockingQueue<>(1);

    public StickHolder() {
        putDown();
    }

    public void pickUp() {
        try {
            holder.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void putDown() {
        try {
            holder.put(stick);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class Philosopher implements Runnable {
        private final int seat;
        private final StickHolder left, right;

        public Philosopher(int seat, StickHolder left, StickHolder right) {
            this.seat = seat;
            this.left = left;
            this.right = right;
        }

        public String toString() {
            return "P" + seat;
        }

        @Override
        public void run() {
            while (true) {
                right.pickUp();
                left.pickUp();
                System.out.println(this + " eating");
                right.putDown();
                left.putDown();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        DingingPhilosophers dingingPhilosophers = new DingingPhilosophers(5);
        Thread.sleep(3000);
    }
}

class DingingPhilosophers {
    private StickHolder[] sticks;
    private StickHolder.Philosopher[] philosophers;

    public DingingPhilosophers(int n) {
        sticks = new StickHolder[n];
        Arrays.setAll(sticks, i -> new StickHolder());
        philosophers = new StickHolder.Philosopher[n];
        Arrays.setAll(philosophers, i -> new StickHolder.Philosopher(i, sticks[i], sticks[(i + 1) % n]));
        Arrays.stream(philosophers)
                .forEach(CompletableFuture::runAsync);
    }

}

