package com.drlang.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

public class CompletablePizza {
    static final int QUANTITY =5;
    public static CompletableFuture<Pizza> makeCF(Pizza pizza){
        return CompletableFuture.completedFuture(pizza)
                .thenApplyAsync(Pizza::roll)
                .thenApplyAsync(Pizza::sauce)
                .thenApplyAsync(Pizza::cheese)
                .thenApplyAsync(Pizza::toppings)
                .thenApplyAsync(Pizza::bake)
                .thenApplyAsync(Pizza::slice)
                .thenApplyAsync(Pizza::box);
    }
    public static void show(CompletableFuture<Pizza> cf){
        try {
            System.out.println(cf.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        IntStream.range(0,QUANTITY)
                .mapToObj(Pizza::new)
                .map(CompletablePizza::makeCF)
                .toList().forEach(CompletablePizza::show);

    }
}
