package com.drlang.patterns;

public abstract class StateMachine {
    protected State1 currentState;

    protected abstract boolean changeState();

    protected final void runAll() {
        while (changeState()) {
            currentState.run();
        }
    }

}
