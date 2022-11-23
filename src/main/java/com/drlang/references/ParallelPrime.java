package com.drlang.references;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.stream.LongStream.iterate;
import static java.util.stream.LongStream.rangeClosed;

public class ParallelPrime {
    static final int COUNT = 1000000;

    public static boolean isPrime(long n) {
        return rangeClosed(2, (long) Math.sqrt(n)).noneMatch(i -> n % i == 0);
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 50; j++) {
            Timer timer = new Timer();
            List<String> primes = iterate(2, i -> i + 1).
                    parallel().filter(ParallelPrime::isPrime)
                    .limit(COUNT).mapToObj(Long::toString).toList();
            System.out.println(timer);
//        System.out.println(primes);
            try {
                Files.write(Paths.get("src/primes.txt"), primes, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
