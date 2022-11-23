package com.drlang.concurrent;

import javax.crypto.Mac;
import java.util.concurrent.CompletableFuture;

public class CompletableApplyAsync {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(Machina::new)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work)
                .thenApplyAsync(Machina::work).join();
    }
}
