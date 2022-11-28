package com.drlang.patterns;

import java.util.stream.Stream;

public class ShapeFactory3 {
    public static void main(String[] args) {
        RandomShapes rs = new RandomShapes(Circle::new, Square::new, Triangle::new);
        Stream.generate(rs)
                .limit(6)
                .peek(Shape::draw)
                .peek(Shape::erase)
                .count();
    }
}
