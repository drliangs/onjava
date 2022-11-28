package com.drlang.patterns.trash;

public class Cradboard extends Trash{
    public Cradboard(double weight) {
        super(weight);
    }

    @Override
    public double price() {
        return Price.CARDBOARD;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
