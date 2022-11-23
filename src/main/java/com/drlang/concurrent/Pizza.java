package com.drlang.concurrent;

import java.util.stream.IntStream;

public class Pizza {
    public enum Step {
        DOUGH(4), ROLLED(1), SAUCED(1), CHEESED(2),
        TOPPED(5), BAKED(2), SLICED(1), BOXED(0);
        int effort;

        Step(int effort) {
            this.effort = effort;
        }

        Step forward() {
            if (equals(BOXED)) return BOXED;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return values()[ordinal() + 1];
        }
    }

    private Step step = Step.DOUGH;
    private final int id;

    public Pizza(int id) {
        this.id = id;
    }

    public Pizza next() {
        step = step.forward();
        System.out.println("Pizza" + id + ": " + step);
        return this;
    }

    public Pizza next(Step previousStep) {
        if (!step.equals(previousStep)) {
            throw new IllegalStateException("Expected" + previousStep + " but found " + step);
        }
        return next();
    }

    public Pizza roll() {
        return next(Step.DOUGH);
    }

    public Pizza sauce() {
        return next(Step.ROLLED);
    }

    public Pizza cheese() {
        return next(Step.SAUCED);
    }

    public Pizza toppings() {
        return next(Step.CHEESED);
    }

    public Pizza bake() {
        return next(Step.TOPPED);
    }

    public Pizza slice() {
        return next(Step.BAKED);
    }

    public Pizza box() {
        return next(Step.SLICED);
    }

    public boolean complete() {
        return step.equals(Step.BOXED);
    }

    @Override
    public String toString() {
        return "Pizza" + id + ":  " + (step.equals(Step.BOXED) ? "complete" : step);
    }


    public static  class PizzaStreams{
        static final int QUANTITY = 5;

        public static void main(String[] args) {
            IntStream.range(0,QUANTITY)
                    .mapToObj(Pizza::new)
                    .parallel().forEach(za->{
                        while (!za.complete()){
                            za.next();
                        }
                    });

        }
    }

}
