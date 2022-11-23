package com.drlang.references;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Int {
    private int i;

    Int(int ii) {
        this.i = ii;
    }

    @Override
    public String toString() {
        return Integer.toString(i);
    }

    public void increment() {
        i++;
    }
}

public class CloneArrayList {
    public static void main(String[] args) {
        ArrayList<Int> v = IntStream.range(0, 10).
                mapToObj(Int::new).
                collect(Collectors.toCollection(ArrayList::new));
        System.out.println("V: " + v);
        @SuppressWarnings("unchecked")
        ArrayList<Int> v2 = (ArrayList<Int>) v.clone();
        v2.forEach(Int::increment);
        System.out.println("V : " + v2);
        System.out.println(v);
        System.out.println(v2);
        System.out.println(System.identityHashCode(v));
        System.out.println(System.identityHashCode(v2));
        Integer x =1;

    }

}
