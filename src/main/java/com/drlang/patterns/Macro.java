package com.drlang.patterns;

import java.util.ArrayList;
import java.util.Arrays;

public class Macro {
    public static void main(String[] args) {
        ArrayList<Runnable> macro = new ArrayList<>(Arrays.<Runnable>asList(() -> System.out.println("Hello"), () -> System.out.println("World!")));
        macro.forEach(Runnable::run);
        macro.add(()-> System.out.println("I'm the command pattern!"));
        macro.forEach(Runnable::run);
    }
}
