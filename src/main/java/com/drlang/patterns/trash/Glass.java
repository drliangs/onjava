package com.drlang.patterns.trash;

public class Glass extends Trash{
    public Glass(double weight) {
        super(weight);
    }

    @Override
    public double price() {
        return Price.GLASS;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
