package com.drlang.concurrent;

import java.util.concurrent.CompletableFuture;

public class CompletableException {

    public static CompletableFuture<Breakable> test(String id, int failCount) {
        return CompletableFuture.completedFuture(new Breakable(id, failCount)).
                thenApplyAsync(Breakable::work).thenApplyAsync(Breakable::work).
                thenApplyAsync(Breakable::work).thenApplyAsync(Breakable::work);

    }
}
