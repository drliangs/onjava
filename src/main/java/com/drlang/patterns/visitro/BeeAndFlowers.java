package com.drlang.patterns.visitro;


import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.concurrent.Flow;
import java.util.function.Supplier;
import java.util.stream.Stream;


interface Flower {
    void accept(Visitor v);
}

class Chrysanthemum implements Flower {
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}

class Ranunculus implements Flower {

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}

class Gladiolus implements Flower {
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}

class StringVal implements Visitor {
    private String s;

    @Override
    public String toString() {
        return s;
    }

    @Override
    public void visit(Gladiolus g) {
        s = "Gladiolus";
    }

    @Override
    public void visit(Ranunculus r) {
        s = "Ranunculus";
    }

    @Override
    public void visit(Chrysanthemum c) {
        s = "Chrysanthemum";
    }
}

class Bee implements Visitor {

    @Override
    public void visit(Gladiolus g) {
        System.out.println("Bee and Gladiolus");
    }

    @Override
    public void visit(Ranunculus r) {
        System.out.println("Bee and Ranunculus");
    }

    @Override
    public void visit(Chrysanthemum c) {
        System.out.println("Bee and Chrysanthemum");
    }
}

class FlowerFactory {
    static final List<Supplier<Flower>> flowers = Arrays.asList(Gladiolus::new, Ranunculus::new, Chrysanthemum::new);
    static final int SZ = flowers.size();
    private final static SplittableRandom rand = new SplittableRandom(47);

    public static Flower newFlower() {
        return flowers.get(rand.nextInt(SZ)).get();
    }
}

public class BeeAndFlowers {
    public static void main(String[] args) {
        List<Flower> flowers = Stream.generate(FlowerFactory::newFlower)
                .limit(10)
                .toList();
        StringVal sval = new StringVal();
        flowers.forEach(f -> {
            f.accept(sval);
            System.out.println(sval);
        });
        Bee bee = new Bee();
        flowers.forEach(f->f.accept(bee));
    }

}
