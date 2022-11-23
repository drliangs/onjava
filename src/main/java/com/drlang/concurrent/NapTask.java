package com.drlang.concurrent;

import java.util.concurrent.TimeUnit;

public class NapTask implements Runnable {
    private final int id;

    public NapTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        new Nap(0.1);
        System.out.println(this + "  " + Thread.currentThread().getName());
    }

    @Override
    public String toString() {
        return "NapTask[" + id + "]";
    }

    static public class Nap {
        public Nap(double t) {
            try {
                TimeUnit.MILLISECONDS.sleep((long) (1000 * t));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public Nap(double t, String msg) {
            this(t);
            System.out.println(msg);
        }
    }
}
