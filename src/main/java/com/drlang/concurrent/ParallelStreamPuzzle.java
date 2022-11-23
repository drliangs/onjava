package com.drlang.concurrent;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreamPuzzle {
    static class IntGenerator implements Supplier<Integer> {
        private int current = 0;

        @Override
        public Integer get() {
            return current++;
        }
    }

    public static void main(String[] args) {
        List<Integer> collect = Stream.generate(new IntGenerator())
                .limit(100000)
                .parallel()
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
