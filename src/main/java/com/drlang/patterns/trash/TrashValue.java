package com.drlang.patterns.trash;

import java.util.List;

public class TrashValue {
    private static double total;

    public static void sum(List<? extends Trash> bin, String type) {
        total = 0.0;
        for (Trash trash : bin) {
            System.out.println(trash);
            total += trash.weight * trash.price();
        }
        System.out.printf("Total %s value = %.2f%n", type, total);
    }
}
