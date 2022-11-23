package com.drlang.concurrent;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreamPuzzle3 {
    public static void main(String[] args) {
      var ar =  IntStream.range(0,30).peek(value -> System.out.println(value + ";" + Thread.currentThread().getName()))
                .limit(10)
                .parallel()
                .boxed().collect(Collectors.toList());
        System.out.println(ar);
    }
}
