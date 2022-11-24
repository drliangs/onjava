package com.drlang.patterns;

public class Wash implements State1{
    @Override
    public void run() {
        System.out.println("Washing");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
