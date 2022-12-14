package com.drlang.concurrent;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreamPuzzle2 {
    public static final Deque<String> trace = new ConcurrentLinkedDeque<>();
    static class IntGenerator implements Supplier<Integer>{
        private AtomicInteger current = new AtomicInteger();
        @Override
        public  Integer get() {
            trace.add(current.get() + ";" + Thread.currentThread().getName());
            return current.getAndIncrement();
        }
    }

    public static void main(String[] args) throws Exception{
        List<Integer> collect = Stream.generate(new IntGenerator())
                .limit(1000000000)
                .parallel().collect(Collectors.toList());
        System.out.println(collect);
        Files.write(Paths.get("PSP2.txt"),trace, StandardOpenOption.CREATE);
    }
}
