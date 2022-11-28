package com.drlang.patterns;

import java.util.stream.Stream;

public class FactoryTest {
    public static void test(FactoryMethod factoryMethod){
        long count = Stream.of("Circle", "Square", "Triangle", "Square", "Circle", "Circle", "Triangle").map(factoryMethod::create)
                .peek(Shape::draw).peek(Shape::erase).count();
    }
}
