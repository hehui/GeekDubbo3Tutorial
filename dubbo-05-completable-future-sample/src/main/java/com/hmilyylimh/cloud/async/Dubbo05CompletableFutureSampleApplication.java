package com.hmilyylimh.cloud.async;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <h1><a href="https://time.geekbang.org/column/intro/100312101">极客专栏地址：https://time.geekbang.org/column/intro/100312101</a></h1><br/><h1><a href="https://gitee.com/ylimhhmily/GeekDubbo3Tutorial">案例代码地址：https://gitee.com/ylimhhmily/GeekDubbo3Tutorial</a></h1>
 *
 * @author hmilyylimh
 * ^_^
 * @version 0.0.1
 * ^_^
 * @date 2022-09-12
 */
public class Dubbo05CompletableFutureSampleApplication {

    public static void main(String[] args) throws Throwable {
        main_newThread();
        main_runAsync();
        main_supplyAsync();
        main_supplyAsync_thenRun();
        main_supplyAsync_thenAccept();
        main_supplyAsync_thenApply();

        main_mixed_future();

        for (int i = 0; i < 100; i++) {
            main_mixed_future2();
        }
    }

    private static void main_newThread() throws Throwable {
        String functionName = "【主线程通过 'new Thread' 方式吩咐子线程干活，直到等子线程干活完成】";

        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            sleepInner(2000);
            System.out.println(now() + "," + functionName + " 子线程干一件事完成, 线程名：" + currentThreadInfo());
            future.complete("【子线程干一件事耗时 2000ms】");
        }).start();
        System.out.println(now() + "," + functionName + " 开始执行...");

        String result = future.get();
        System.out.println(now() + "," + functionName + " 等到子线程执行完成, 子线程返回的结果为: " + result);
        System.out.println("-------------------------------");
    }

    private static void main_runAsync() throws Throwable {
        String functionName = "【主线程通过 'runAsync' 方式吩咐子线程干活，直到等子线程干活完成】";

        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                sleepInner(2000);
                System.out.println(now() + "," + functionName + " 子线程干一件事完成, 线程名：" + currentThreadInfo());
            }
        });
        System.out.println(now() + "," + functionName + " 开始执行...");

        Void result = future.get();
        System.out.println(now() + "," + functionName + " 等到子线程执行完成, 看看Void类型结果为: " + result);
        System.out.println("-------------------------------");
    }

    private static void main_supplyAsync() throws Throwable {
        String functionName = "【主线程通过 'supplyAsync' 方式吩咐子线程干活，直到等子线程干活完成】";

        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                sleepInner(2000);
                System.out.println(now() + "," + functionName + " 子线程干一件事完成, 线程名：" + currentThreadInfo());
                return "【子线程干一件事耗时 2000ms】";
            }
        });
        System.out.println(now() + "," + functionName + " 开始执行...");

        String result = future.get();
        System.out.println(now() + "," + functionName + " 等到子线程执行完成, 子线程返回的结果为: " + result);
        System.out.println("-------------------------------");
    }

    private static void main_supplyAsync_thenRun() throws Throwable {
        String functionName = "【主线程通过 'supplyAsync+thenRun' 方式吩咐子线程干两件事，直到等子线程把两件事都完成】";

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                sleepInner(2000);
                System.out.println(now() + "," + functionName + " 子线程干第一件事完成, 线程名：" + currentThreadInfo());
                return "【子线程干第一件事耗时 2000ms】";
            }
        }).thenRun(new Runnable() {
            @Override
            public void run() {
                sleepInner(2000);
                System.out.println(now() + "," + functionName + " 子线程干第二件事完成, 线程名：" + currentThreadInfo());
            }
        });
        System.out.println(now() + "," + functionName + " 开始执行...");

        Void result = future.get();
        System.out.println(now() + "," + functionName + " 等到子线程执行完成, 看看Void类型结果为: " + result);
        System.out.println("-------------------------------");
    }

    private static void main_supplyAsync_thenAccept() throws Throwable {
        String functionName = "【主线程通过 'supplyAsync+thenAccept' 方式吩咐子线程干两件事，直到等子线程把两件事都完成】";

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                sleepInner(2000);
                System.out.println(now() + "," + functionName + " 子线程干第一件事完成, 线程名：" + currentThreadInfo());
                return "【子线程干第一件事耗时 2000ms】";
            }
        }).thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                sleepInner(2000);
                System.out.println(now() + "," + functionName + " 子线程干第二件事完成, 线程名：" + currentThreadInfo());
            }
        });
        System.out.println(now() + "," + functionName + " 开始执行...");

        Void result = future.get();
        System.out.println(now() + "," + functionName + " 等到子线程执行完成, 看看Void类型结果为: " + result);
        System.out.println("-------------------------------");
    }

    private static void main_supplyAsync_thenApply() throws Throwable {
        String functionName = "【主线程通过 'supplyAsync+thenApply' 方式吩咐子线程干两件事，直到等子线程把两件事都完成】";
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                sleepInner(2000);
                System.out.println(now() + "," + functionName + " 子线程干第一件事完成, 线程名：" + currentThreadInfo());
                return "【子线程干第一件事耗时 2000ms】";
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                sleepInner(2000);
                String result = s + ", 【子线程干第二件事也耗时 2000ms】.";
                System.out.println(now() + "," + functionName + " 子线程干第二件事完成, 线程名：" + currentThreadInfo());
                return result;
            }
        });
        System.out.println(now() + "," + functionName + " 开始执行...");

        String result = future.get();
        System.out.println(now() + "," + functionName + " 等到子线程执行完成, 子线程返回的结果为: " + result);
        System.out.println("-------------------------------");
    }

    private static void main_mixed_future() {
        // 任务1
        CompletableFuture<Integer> completableFuture1 = CompletableFuture
                .supplyAsync(() -> 1)
                .thenApplyAsync(cf -> {
                    CompletableFuture<Integer> completableFuture = CompletableFuture
                            .supplyAsync(() -> 2)
                            .thenCombine(CompletableFuture.supplyAsync(() -> 3), (cf1, cf2) -> cf1 + cf2 + cf);
                    return completableFuture.join();
                })
                .thenApplyAsync(cf -> CompletableFuture.supplyAsync(() -> 3 + cf).join());
        // 任务2
        CompletableFuture<Integer> completableFuture2 = CompletableFuture
                .supplyAsync(() -> 1)
                .thenApplyAsync(cf -> {
                    CompletableFuture<Integer> completableFuture = CompletableFuture
                            .anyOf(CompletableFuture.supplyAsync(() -> 2), CompletableFuture.supplyAsync(() -> 3))
                            .thenApply(cf2 -> (Integer) cf2 + cf);
                    return completableFuture.join();
                })
                .thenApplyAsync(cf -> CompletableFuture.supplyAsync(() -> 3 + cf).join());
        // 等待1 2 完成
        CompletableFuture<Integer> sumCF = completableFuture1.thenCombine(completableFuture2, Integer::sum);
        try {
            System.out.println(sumCF.get(1, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println(0);
            e.printStackTrace();
        }
    }
    private static void main_mixed_future2() throws Exception {
        ///////////////////////////////////////////////////
        // 任务一执行流程
        ///////////////////////////////////////////////////
        // 执行 taskA1
        CompletableFuture<Integer> taskA1 = CompletableFuture.supplyAsync(() -> 1);

        // taskA1 执行完后，再并发执行 taskB1、taskC1
        CompletableFuture<Integer> taskB1 = taskA1.thenApplyAsync(integer -> 2);
        CompletableFuture<Integer> taskC1 = taskA1.thenApplyAsync(integer -> 3);

        // 任务一的结果
        CompletableFuture<Integer> result1 =
                // 等到 taskB2、taskC2 都执行完并合并结果后
                taskB1.thenCombine(taskC1, Integer::sum)
                // 再合并 taskA1 的结果后
                .thenCombine(taskA1, Integer::sum)
                // 再异步执行 taskD1
                .thenApplyAsync(integer -> integer + 4);

        ///////////////////////////////////////////////////
        // 任务二执行流程
        ///////////////////////////////////////////////////
        // 执行 taskA2
        CompletableFuture<Integer> taskA2 = CompletableFuture.supplyAsync(() -> 1);

        // taskA2 执行完后，再并发执行 taskB2、taskC2
        CompletableFuture<Integer> taskB2 = taskA2.thenApplyAsync(integer -> 2);
        CompletableFuture<Integer> taskC2 = taskA2.thenApplyAsync(integer -> 3);

        // 任务二的结果
        CompletableFuture<Integer> result2 =
                // 等到 taskB2、taskC2 任意其中一个有结果后
                taskB2.applyToEither(taskC2, Function.identity())
                // 再合并 taskA2 的结果后
                .thenCombine(taskA2, Integer::sum)
                // 再异步执行 taskD2
                .thenApplyAsync(integer -> integer + 4);

        ///////////////////////////////////////////////////
        // 任务一 + 任务二，合并结果
        ///////////////////////////////////////////////////
        CompletableFuture<Integer> result = result1.thenCombine(result2, Integer::sum);
        try {
            System.out.println(result.get(5, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println(0);
            e.printStackTrace();
        }
    }
    private static void main_mixed_future3() {
        // taskA、taskB 并行执行，并行，返回执行快的任务的结果
        CompletableFuture<Integer> taskA = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Integer> taskB = CompletableFuture.supplyAsync(() -> 2);

        CompletableFuture<Integer> taskC = taskA.applyToEither(taskB, new Function<Integer,
                Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer;
            }
        });

        System.out.println(taskC.join());
    }
    private static void main_mixed_future4() {
        // taskA、taskB 并行执行，最后汇总累加为 3
        CompletableFuture<Integer> taskA = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Integer> taskB = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> taskC = taskA.thenCombine(taskB, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
        System.out.println(taskC.join());
    }

    private static void sleepInner(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String currentThreadInfo() {
        return Thread.currentThread().getId() + ":" + Thread.currentThread().getName();
    }

    private static String now() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}