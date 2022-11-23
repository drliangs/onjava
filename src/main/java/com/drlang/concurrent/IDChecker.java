package com.drlang.concurrent;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class IDChecker {
    public static final int SIZE = 100000;
    static class MakeObjects implements Supplier<List<Integer>>{
        private Supplier<HasId> gen;

        public MakeObjects(Supplier<HasId> gen) {
            this.gen = gen;
        }

        @Override
        public List<Integer> get() {
            return Stream.generate(gen)
                    .limit(SIZE)
                    .map(HasId::getID).toList();
        }
    }
    public static void test(Supplier<HasId> gen){
        CompletableFuture<List<Integer>> groupA = CompletableFuture.supplyAsync(new MakeObjects(gen));
        CompletableFuture<List<Integer>> groupB = CompletableFuture.supplyAsync(new MakeObjects(gen));
        groupA.thenAcceptBoth(groupB,(a,b)->{
            System.out.println(Sets.intersection(Sets.newHashSet(a),Sets.newHashSet(b)).size());
        }).join();
    }

    public static void main(String[] args) {
        test(StaticIDField::new);
    }
}
