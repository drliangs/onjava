package com.drlang.iostreams;

import com.drlang.lowlevel.TimedAbort;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Echo {
    public static void main(String[] args) {
        TimedAbort timedAbort = new TimedAbort(2);
        new BufferedReader(new InputStreamReader(System.in))
                .lines().peek(lb -> timedAbort.restart())
                .forEach(System.out::printf);
    }
}
