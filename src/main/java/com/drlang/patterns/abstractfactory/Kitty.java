package com.drlang.patterns.abstractfactory;

public class Kitty implements Player{
    @Override
    public void interactWith(Obstacle o) {
        System.out.println("Kitty has encountered a ");
        o.action();
    }
}
