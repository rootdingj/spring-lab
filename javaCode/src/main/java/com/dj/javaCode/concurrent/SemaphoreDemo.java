package com.dj.javaCode.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    // 线程数(线程池大小)
    private static final int THREAD_COUNT = 30;

    // 线程池
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    // 信号量(许可证数量为10，最大并发数是10)
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(() -> {
                try {
                    // 获取一个许可证
                    semaphore.acquire();
                    System.out.println("save data");
                    // 还许可证
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }
}
