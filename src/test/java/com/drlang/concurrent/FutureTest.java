package com.drlang.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiFunction;

public class FutureTest {
    @Test
    void name() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread() + " start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (false) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
            }
        });
        System.out.println("main thread start,time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->" + cf.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());

    }

    @Test
    void test2() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (false) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
                return 1.2;
            }
        }, pool);
        System.out.println("main thread start,time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->" + cf.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }


    @Test
    public void test5() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        }, pool);
        //cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
        //thenApply这里实际创建了一个新的CompletableFuture实例
        CompletableFuture<String> cf2 = cf.thenApplyAsync((result) -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return "test:" + result;
        }, pool);
        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
//        System.out.println("run result->"+cf.get());
        System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    @Test
    void test6() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();

        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });
        //cf关联的异步任务的返回值作为方法入参，传入到thenApply的方法中
        CompletableFuture cf2 = cf.thenApplyAsync((result) -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return "test:" + result;
        }, pool).thenAcceptAsync((result) -> { //接收上一个任务的执行结果作为入参，但是没有返回值
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(result);
            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
        }).thenRunAsync(() -> { //无入参，也没有返回值
            System.out.println(Thread.currentThread() + " start job4,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println("thenRun do something");
            System.out.println(Thread.currentThread() + " exit job4,time->" + System.currentTimeMillis());
        }, pool);
        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("run result->////" + cf.get());
        System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
        //cf2 等待最后一个thenRun执行完成
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());

    }

    @Test
    public void test7() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job1 start,time->" + System.currentTimeMillis());
            while (true) {
                System.out.println("111");
            }
        }, pool);
        //cf执行异常时，将抛出的异常作为入参传递给回调方法
        CompletableFuture<Double> cf2 = cf.exceptionally((param) -> {
            System.out.println(Thread.currentThread() + " start/,time->" + System.currentTimeMillis());

            System.out.println("error stack trace->");
            param.printStackTrace();
            System.out.println(Thread.currentThread() + " exit,time->" + System.currentTimeMillis());
            return -1.1;
        });
        //cf正常执行时执行的逻辑，如果执行异常则不调用此逻辑

        CompletableFuture<Object> cf3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job2 start,time->" + System.currentTimeMillis());
            while (true) {
                System.out.println("222");
            }
        });
        System.out.println("main thread start,time->" + System.currentTimeMillis());
        //等待子任务执行完成,此处无论是job2和job3都可以实现job2退出，主线程才退出，如果是cf，则主线程不会等待job2执行完成自动退出了
        //cf2.get时，没有异常，但是依然有返回值，就是cf的返回值
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    @Test
    public void test10() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job1 start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (false) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + "job1 exit,time->" + System.currentTimeMillis());
                return 1.2;
            }
        });
        //cf执行完成后会将执行结果和执行过程中抛出的异常传入回调方法，如果是正常执行的则传入的异常为null
        CompletableFuture<Double> cf2 = cf.whenComplete((a, b) -> {
            System.out.println(Thread.currentThread() + "job2 start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (b != null) {
                System.out.println("error stack trace->");
                b.printStackTrace();
            } else {
                System.out.println("run succ,result->" + a);
            }
            System.out.println(Thread.currentThread() + "job2 exit,time->" + System.currentTimeMillis());
        });
        //等待子任务执行完成
        System.out.println("main thread start wait,time->" + System.currentTimeMillis());
        //如果cf是正常执行的，cf2.get的结果就是cf执行的结果
        //如果cf是执行异常，则cf2.get会抛出异常
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    @Test
    public void test110() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + "job1 start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (false) {
                throw new RuntimeException("test");
            } else {
                System.out.println(Thread.currentThread() + "job1 exit,time->" + System.currentTimeMillis());
                return 1.2;
            }
        });
        //cf执行完成后会将执行结果和执行过程中抛出的异常传入回调方法，如果是正常执行的则传入的异常为null
        CompletableFuture<String> cf2 = cf.handle((a, b) -> {
            System.out.println(Thread.currentThread() + "job2 start,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            if (b != null) {
                System.out.println("error stack trace->");
                b.printStackTrace();
            } else {
                System.out.println("run succ,result->" + a);
            }
            System.out.println(Thread.currentThread() + "job2 exit,time->" + System.currentTimeMillis());
            if (b != null) {
                return "run error";
            } else {
                return "run succ";
            }
        });
        //等待子任务执行完成
        System.out.println("main thread start wait,time->" + System.currentTimeMillis());
        //get的结果是cf2的返回值，跟cf没关系了
        System.out.println("run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }


    @Test
    public void test17() throws Exception {
        ForkJoinPool pool = new ForkJoinPool();
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return 3.2;
        });
        //cf和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,且有返回值
        CompletableFuture<Double> cf3 = cf.thenCombine(cf2, (a, b) -> {
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
            System.out.println("job3 param a->" + a + ",b->" + b);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
            return a + b;
        });

        //cf和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,无返回值
        CompletableFuture cf4 = cf.thenAcceptBoth(cf2, (a, b) -> {
            System.out.println(Thread.currentThread() + " start job4,time->" + System.currentTimeMillis());
            System.out.println(Thread.currentThread() + "job4 param a->" + a + ",b->" + b);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job4,time->" + System.currentTimeMillis());
        });

        //cf4和cf3都执行完成后，执行cf5，无入参，无返回值
        CompletableFuture cf5 = cf4.runAfterBoth(cf3, () -> {
            System.out.println(Thread.currentThread() + " start job5,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("cf5 do something");
            System.out.println(Thread.currentThread() + " exit job5,time->" + System.currentTimeMillis());
        });

        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("cf run result->" + cf.get());
        System.out.println("main thread start cf5.get(),time->" + System.currentTimeMillis());
        System.out.println("cf5 run result->" + cf5.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    @Test
    public void test8() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });
        CompletableFuture<Double> cf2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return 3.2;
        });
        //cf和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,且有返回值
        CompletableFuture<Double> cf3 = cf.applyToEither(cf2, (result) -> {
            System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
            System.out.println("job3 param result->" + result);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
            return result;
        });

        //cf和cf2的异步任务都执行完成后，会将其执行结果作为方法入参传递给cf3,无返回值
        CompletableFuture cf4 = cf.acceptEither(cf2, (result) -> {
            System.out.println(Thread.currentThread() + " start job4,time->" + System.currentTimeMillis());
            System.out.println("job4 param result->" + result);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job4,time->" + System.currentTimeMillis());
        });

        //cf4和cf3都执行完成后，执行cf5，无入参，无返回值
        CompletableFuture cf5 = cf4.runAfterEither(cf3, () -> {
            System.out.println(Thread.currentThread() + " start job5,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.println("cf5 do something");
            System.out.println(Thread.currentThread() + " exit job5,time->" + System.currentTimeMillis());
        });

        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("cf run result->" + cf.get());
        System.out.println("main thread start cf5.get(),time->" + System.currentTimeMillis());
        System.out.println("cf5 run result->" + cf5.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    @Test
    public void test9() throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread() + " start job1,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job1,time->" + System.currentTimeMillis());
            return 1.2;
        });
        CompletableFuture<String> cf2 = cf.thenCompose((param) -> {
            System.out.println(Thread.currentThread() + " start job2,time->" + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread() + " exit job2,time->" + System.currentTimeMillis());
            return CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread() + " start job3,time->" + System.currentTimeMillis());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread() + " exit job3,time->" + System.currentTimeMillis());
                return "job3 test";
            });
        });
        System.out.println("main thread start cf.get(),time->" + System.currentTimeMillis());
        //等待子任务执行完成
        System.out.println("cf run result->" + cf.get());
        System.out.println("main thread start cf2.get(),time->" + System.currentTimeMillis());
        System.out.println("cf2 run result->" + cf2.get());
        System.out.println("main thread exit,time->" + System.currentTimeMillis());
    }

    @Test
    void ttt() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "resultA")
                .thenApply(resultA -> resultA + " resultB")
                // 任务 C 抛出异常
                .thenApply(resultB -> {
                    return "qwqeqweq";
                })
                // 处理任务 C 的返回值或异常
                .handle(new BiFunction<Object, Throwable, Object>() {
                    @Override
                    public Object apply(Object o, Throwable throwable) {
                        System.out.println(o);
                        System.out.println(throwable);
                        if (throwable != null) {
                            return "zz";
                        }
                        return o;
                    }
                })
                .thenApply(resultC -> resultC + " resultD");

        System.out.println(future.join());
    }
}
