package com.drlang.patterns.abstractfactory;

public class Weapon implements Obstacle{


    @Override
    public void action() {
        System.out.println("Weapon");
    }
}
