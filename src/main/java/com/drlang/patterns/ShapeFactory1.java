package com.drlang.patterns;

public class ShapeFactory1 implements FactoryMethod {
    @Override
    public Shape create(String type) {
        return switch (type) {
            case "Circle" -> new Circle();
            case "Square" -> new Square();
            case "Triangle" -> new Triangle();
            default -> throw new BadShapeException(type);
        };
    }

    public static void main(String[] args) {
        FactoryTest.test(new ShapeFactory1());
    }
}
