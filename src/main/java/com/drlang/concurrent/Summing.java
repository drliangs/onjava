package com.drlang.concurrent;

import java.util.Timer;
import java.util.function.LongSupplier;
import java.util.stream.LongStream;

public class Summing {
    public static void main(String[] args) {
        System.out.println(CHECK);
        timeTest("Sum Stream", CHECK, () -> LongStream.rangeClosed(0, SZ).sum());
        timeTest("Sum Stream Parallel", CHECK, () -> LongStream.rangeClosed(0, SZ).parallel().sum());
        timeTest("Sum Iterated", CHECK, () -> LongStream.iterate(0, i -> i + 1).limit(SZ + 1).sum());

    }

    public static final int SZ = 100000000;

    static void timeTest(String id, long checkValue, LongSupplier operation) {
        System.out.println(id + ";");
        Timer timer = new Timer();
        long result = operation.getAsLong();
        if (result == checkValue) {
            System.out.println(timer.purge() + "ms");
        } else {
            System.out.format("result:%d%ncheckValue:%d%n", result, checkValue);
        }
    }

    public static final long CHECK = (long) SZ * ((long) SZ + 1) / 2;
}
