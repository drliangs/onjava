package com.drlang.lowlevel;

import java.util.concurrent.CompletableFuture;

public class SerialNumberChecker implements Runnable {
    private CircularSet serials = new CircularSet(1000);
    private SerialNumbers producer;

    public SerialNumberChecker(SerialNumbers producer) {
        this.producer = producer;
    }

    @Override
    public void run() {
        while (true) {
            int serial = producer.nextSerialNumber();
            if (serials.contains(serial)) {
                System.out.println("Duplicate: " + serial);
                System.exit(0);
            }
            serials.add(serial);
        }
    }

    public static void test(SerialNumbers producer) {
        for (int i = 0; i < 10; i++) {
            CompletableFuture.runAsync(new SerialNumberChecker(producer));
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        test(new SerialNumbers());
    }
}
