package com.dj.javaCode.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
}
