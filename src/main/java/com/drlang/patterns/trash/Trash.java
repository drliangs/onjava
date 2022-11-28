package com.drlang.patterns.trash;

public abstract class Trash {
    public final double weight;

    public Trash(double weight) {
        this.weight = weight;
    }

    public abstract double price();

    @Override
    public String toString() {
        return String.format("%s weight: %.2f * price : %.2f = %.2f", getClass().getSimpleName(), weight, price(), weight * price());
    }
    public abstract void accept(Visitor v);
}
