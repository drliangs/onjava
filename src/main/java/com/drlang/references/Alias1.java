package com.drlang.references;

public class Alias1 {
    private int i;
    public Alias1(int ii){
        this.i = ii;
    }

    public static void main(String[] args) {
        Alias1 x = new Alias1(7);
        Alias1 y = x;
        System.out.println("x:" + x.i);
        System.out.println("y:" + y.i);
        System.out.println("Inrementing x");
        x.i++;
        System.out.println("x:" + x.i);
        System.out.println("y:" + y.i);

    }

}
