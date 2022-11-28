package com.drlang.patterns.trash;

public class Paper extends Trash{
    public Paper(double weight) {
        super(weight);
    }

    @Override
    public double price() {
        return Price.PAPER;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
