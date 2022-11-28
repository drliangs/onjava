package com.drlang.patterns.trash;

import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.function.Function;
import java.util.stream.Stream;

class SimpleFactory{
    static final List<Function<Double,Trash>> constructors = Arrays.asList(Aluminum::new,Paper::new,Glass::new);
    static final int SIZE=  constructors.size();
    private static final SplittableRandom random = new SplittableRandom(42);
    public static Trash random(){
        return constructors.get(random.nextInt(SIZE))
                .apply(random.nextDouble());
    }
}

public class RecycleA {
    public static void main(String[] args) {
        List<Trash> bin = Stream.generate(SimpleFactory::random)
                .limit(10)
                .toList();
        new Bins(bin).show();
    }
}
