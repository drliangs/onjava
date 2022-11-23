package com.drlang.concurrent;

public class CatchCompletableExceptions {
    static void handleException(int failCount) {
        Breakable.CompletableException.test("exceptionally", failCount)
                .exceptionally(ex -> {
                    if (ex == null) {
                        System.out.println("I don't get it yet");
                    }
                    throw new RuntimeException("zz");
                }).thenAccept(str -> System.out.println("result:" + str));
        Breakable.CompletableException.test("handle", failCount)
                .handle((result, fail) -> {
                    if (fail != null) {
                        return "Failure recovery object";
                    }
                    return result + " is good";
                }).thenAccept(str -> {
                    System.out.println("result" + str);
                });
        Breakable.CompletableException.test("whenComplete",failCount)
                .whenComplete(((breakable, throwable) -> {
                    if (throwable!=null){
                        System.out.println("It failed");
                    }else {
                        System.out.println(breakable +  " Ok");
                    }
                })).thenAccept(r-> System.out.println("result: " + r));

    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("********Failure Mode************");

        handleException(2);
        System.out.println("******** Success Mode **********");
        handleException(0);
        Thread.sleep(11111111111L);
    }
}
