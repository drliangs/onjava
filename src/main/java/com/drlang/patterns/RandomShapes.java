package com.drlang.patterns;

import java.util.Random;
import java.util.function.Supplier;

public class RandomShapes implements Supplier<Shape> {
    private final Supplier<Shape>[] factories;
    private Random rand = new Random(42);

    public RandomShapes(Supplier<Shape>... factories) {
        this.factories = factories;
    }


    @Override
    public Shape get() {
        return factories[rand.nextInt(factories.length)].get();
    }
}
