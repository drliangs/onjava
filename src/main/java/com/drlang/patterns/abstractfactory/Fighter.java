package com.drlang.patterns.abstractfactory;

public class Fighter implements Player{

    @Override
    public void interactWith(Obstacle o) {
        System.out.println("Fighter now battles a ");
        o.action();
    }
}
