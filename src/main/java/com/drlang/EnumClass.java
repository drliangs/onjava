package com.drlang;

import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class EnumClass {

    enum Shrubbery {
        GROUND, CRAWLING, HANGING
    }

    static void colons(int i) {
        switch (i) {
            case 1 -> System.out.println("one");
            case 2 -> System.out.println("two");
            case 3 -> System.out.println("three");
            default -> System.out.println("default");
        }
    }

    public static void main(String[] args) {
        petCare();
    }

    static void old(String s) {
        if (s == null) {
            System.out.println("null");
            return;
        }
        switch (s) {
            case "XX" -> System.out.println("XXX");
            default -> System.out.println("default");
        }
    }

    static void checkNull(String s) {
        switch (s) {
            case "XX" -> System.out.println("XX");
            case null -> System.out.println("null");
            default -> System.out.println("default");
        }
    }

    static void defaultOnly(String s
    ) {
        switch (s) {
            case "XXX":
                System.out.println("XXX");
                break;
            case null:
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }

    static void defalutOnly(String s) {
        switch (s) {
            case "XX" -> System.out.println("xxx");
            default -> System.out.println("default");
        }
    }

    static void combinNullAndCase(String s) {
        switch (s) {
            case "XX", null -> System.out.println("xxx");
            default -> System.out.println("defalut");
        }
    }

    static void combineNullAndDefault(String s) {
        switch (s) {


            case "XX" -> System.out.println("xxx");
            case null, default -> System.out.println("both");
        }
    }

    static void test(Consumer<String> cs) {
        cs.accept("xxx");
        cs.accept("YY");

    }

    static int colon(String s) {
        return switch (s) {
            case "i":
                yield 1;
            case "j":
                yield 2;
            default:
                yield 0;
        };
    }


    public static class EnumSwitch {
        enum Signal {
            GREEN, YELLOW, RED
        }

        Signal signal = Signal.RED;

    }

    static void dumb(Object x) {
        if (x instanceof String) {
            String s = (String) x;
            if (s.length() > 0) {
                System.out.format("%d %s%n", s.length(), s.toLowerCase(Locale.ROOT));
            }
        }
    }

    static void smart(Object x) {
        if (x instanceof String s && s.length() > 0) {
            System.out.println("x" + s);
        }
    }

    static void wrong(Object x) {

    }


    static void f(Object o) {
        if (!(o instanceof String s)) {
            System.out.println("Not a String");
            throw new RuntimeException();
        }
        System.out.println(s.toLowerCase(Locale.ROOT));
    }

    interface LifeForm {
        String move();

        String react();
    }

    static class Worm implements LifeForm {

        @Override
        public String move() {
            return "Worm::move()";
        }

        @Override
        public String react() {
            return "Worm::react()";
        }
    }

    static class Giraffe implements LifeForm {

        @Override
        public String move() {
            return "Giraffe::move()";
        }

        @Override
        public String react() {
            return "Giraffe:react()";
        }
    }

    public static class NormalLisKov {
        public static void main(String[] args) {
            Stream.of(new Worm(), new Giraffe()).forEach(lifeForm -> {
                System.out.println(lifeForm.move() + "  " + lifeForm.react());
            });
        }
    }

    static public class Pet {
        void feed() {
        }

        ;
    }

    static class Dog extends Pet {
        void walk() {
        }

        ;
    }

    static class Fish extends Pet {
        void changeWater() {
        }
    }

    static public class PetPatternMatch {
        void careFor(Pet pet) {
            switch (pet) {
                case Dog dog -> dog.walk();
                case Fish fish -> fish.changeWater();
                case Pet sp -> sp.feed();
            }
        }
    }

    static void petCare() {
        PetPatternMatch petPatternMatch = new PetPatternMatch();
        List.of(new Dog(), new Fish()).forEach(petPatternMatch::careFor);
    }


}
