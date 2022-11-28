package com.drlang.patterns.trash;

public class Aluminum extends Trash{
    public Aluminum(double weight) {
        super(weight);
    }

    @Override
    public double price() {
        return Price.ALUMINUM;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
