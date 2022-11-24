package com.drlang.patterns;

public class Spin implements State1{
    @Override
    public void run() {
        System.out.println("Spinning");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
