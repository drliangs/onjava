package com.drlang.concurrent;

import java.util.stream.IntStream;

public class CountingStream {
    public static void main(String[] args) {
        System.out.println(IntStream.range(0, 10).parallel().mapToObj(CountingTask::new).
                map(CachedThreadPool3::exCall).reduce(0, (a, b) -> a != null ? Integer.sum(a, b) : 0));
    }
}
