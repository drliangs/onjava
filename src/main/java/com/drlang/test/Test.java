package com.drlang.test;

import java.util.List;

public class Test {
    sealed interface Pet {
        void fee();
    }

    final class Dog implements Pet {

        @Override
        public void fee() {

        }

        void walk() {

        }
    }

    final class Fish implements Pet {

        @Override
        public void fee() {

        }

        void changeWater() {
        }

    }

    public class PetternMatch2 {
        static void careFor(Pet p) {
            switch (p) {
                case Dog dog -> dog.walk();
                case Fish fish -> fish.changeWater();
            }
        }
    }

    void petCare() {
        List.of(new Dog(), new Fish()).forEach(PetternMatch2::careFor);
    }

    sealed interface Shape permits Circle, Rectangele {
        double area();
    }

    record Circle(double radius) implements Shape {
        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }

    record Rectangele(double side1, double side2) implements Shape {

        @Override
        public double area() {
            return side1 * side2;
        }
    }

    public class Shapes {
        static void classify(Shape s) {
            String s1 = switch (s) {
                case Circle circle && circle.area() < 100.0 -> "Small Circle:" + circle;
                case Circle circle -> "Large Circle : " + circle;
                case Rectangele rectangele && rectangele.side1() == rectangele.side2() -> "Square: " + rectangele;
                case Rectangele rectangele -> "Rectangle:" + rectangele;
            };
        }
    }

    static record Level(int percent) {
        Level {
            if (percent < 0 || percent > 100) {
                throw new IndexOutOfBoundsException(percent + " percent");
            }
        }
    }

    static enum Type {TOXIC, FLAMMABLE, NEUTRAL}


    static record Tank(Type type, Level level) {

    }

    public class Tanks {
        static String check(Tank tank) {
            return switch (tank) {
                case Tank tank1 && tank.type == Type.TOXIC -> "Toxic:" + tank1;
                case Tank t && (t.type() == Type.TOXIC && t.level().percent() < 50) -> " Toxic,low" + t;
                case Tank t && t.type() == Type.FLAMMABLE -> "Flammable: " + t;
                case Tank t -> "Other Thank" + t;
            };
        }
    }

    sealed interface Base {

    }

    record Derived() implements Base {

    }

    public class Dominance {
        static String test(Base base) {
            return switch (base) {
                case Derived derived -> "Derivef";
                case Base b -> "B";
            };
        }
    }

    record Person(String name, int age) {
    }


    public class People {
        static String categorize(Person person) {
            return switch (person) {
                case Person person1 && person.age() > 40 -> person1 + " is middle aged";
                case Person person1 && (person1.name().contains("D") || person1.age() == 14) -> person1 + " D or 14";
                case Person p && !(p.age() >= 100) -> p + "is not a centerarian";
                case Person p -> p + " everone else";
            };
        }
    }

    sealed interface Transport {
    }

    record Bicycle(String id) implements Transport {
    }

    record Glider(int size) implements Transport {
    }


    record Surfboard(double weight) implements Transport {
    }

//    record Skis(int length) implements Transport{}

    public class SealedPatternMath{
        static String exhaustive(Transport transport){
            return switch (transport){
                case Bicycle b->"Bicycle"  + b.id();
                case Glider glider -> "Glider" + glider.size();
                case Surfboard surfboard -> "Surfboard" + surfboard.weight();
            };
        }
    }

}
