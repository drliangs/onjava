package com.drlang.concurrent;

import java.util.concurrent.CompletableFuture;

public class Breakable {
    String id;
    private int failCount;

    public Breakable(String id, int failCount) {
        this.id = id;
        this.failCount = failCount;
    }

    @Override
    public String toString() {
        return "Breakable_" + id + " [" + failCount + "]";
    }

    public static Breakable work(Breakable b) {
        if (--b.failCount == 0) {
            System.out.println("Throwing Exception for " + b.id);
            throw new RuntimeException("Breakable_" + b.id + "  failed");
        }
        System.out.println(b);
        return b;
    }

    public static class CompletableException {
        static CompletableFuture<Breakable> test(String id, int failCount) {
            return CompletableFuture.completedFuture(new Breakable(id, failCount)).
                    thenApplyAsync(Breakable::work)
                    .thenApplyAsync(Breakable::work)
                    .thenApplyAsync(Breakable::work)
                    .thenApplyAsync(Breakable::work);

        }

        public static void main(String[] args) {
//            test("A",1).join();
//            test("B",2).join();
//            test("C",3).join();
//            test("D",4).join();
//            test("E",5).join();

//            try {
//                test("F",2).get();
//            }catch (Exception e){
//                System.out.println(e.getMessage());
//            };
//            CompletableFuture<Breakable> g = test("G", 2);
//            g.join();
//            System.out.println(g.isCompletedExceptionally());
            CompletableFuture<Breakable> g1 = test("G", 6);
            g1.join();
            System.out.println(g1.isDone());

            CompletableFuture<Integer> cfi = new CompletableFuture<>();
            System.out.println("done?" + cfi.isDone());
            cfi.completeExceptionally(new RuntimeException("forced"));
            try {
                cfi.get();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }









        }
    }

}
