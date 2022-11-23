package com.drlang.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class Baked {
    static class Pan{}
    static Pan pan(Batter batter)
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Pan();
    }
    static Baked heat(Pan p){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Baked();
    }
    static CompletableFuture<Baked> baked(CompletableFuture<Batter> cfb){
        return cfb.thenApplyAsync(Baked::pan)
                .thenApplyAsync(Baked::heat);
    }
    public static Stream<CompletableFuture<Baked>> batch(){
        CompletableFuture<Batter> mix = Batter.mix();
        return Stream.of(baked(mix),baked(mix),baked(mix),baked(mix));
    }
}
