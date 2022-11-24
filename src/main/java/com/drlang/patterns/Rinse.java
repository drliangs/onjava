package com.drlang.patterns;

public class Rinse implements State1{

    @Override
    public void run() {
        System.out.println("Rinsing");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
