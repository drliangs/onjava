package com.drlang.patterns;

import java.util.Arrays;
import java.util.Iterator;

public class Washer extends StateMachine {
    protected int i = 0;
    private Iterator<State1> iterator = Arrays.asList(new Wash(), new Spin(), new Rinse(), new Spin()).iterator();

    public Washer() {
        runAll();
    }

    @Override
    protected boolean changeState() {
        if (!iterator.hasNext()) return false;
        currentState = iterator.next();
        return true;
    }

    public static void main(String[] args) {
        new Washer();
    }
}
