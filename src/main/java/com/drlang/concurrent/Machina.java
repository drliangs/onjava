package com.drlang.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Machina {
    public Machina() {
        this(2);
    }

    public enum State {
        START, ONE, TWO, THREE, END;

        public State step() {
            if (equals(END)) return END;
            return values()[ordinal() + 1];
        }
    }

    private State state = State.START;
    private final int id;

    public Machina(int id) {
        this.id = id;
    }

    public static Machina work(Machina m)  {
        if (!m.state.equals(State.END)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            m.state = m.state.step();

        }
        System.out.println(m);
        return m;
    }

    @Override
    public String toString() {
        return "Machina  " + id + ": " + (state.equals(State.END) ? "complete" : state);
    }

    public static void main(String[] args) {
        CompletableFuture.completedFuture(new Machina(0)).
                thenApply(Machina::work).
                thenApply(Machina::work).thenApply(Machina::work).thenApply(Machina::work);
    }
}
