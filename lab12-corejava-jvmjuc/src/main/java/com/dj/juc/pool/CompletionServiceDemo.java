package com.dj.juc.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CompletionServiceDemo {
    private static final int POOL_SIZE = 10;

    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 30, TimeUnit.SECONDS,
        new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.DiscardOldestPolicy());

    static class SleepTask implements Callable<Integer> {

        private int sleepSeconds;
        private int returnValue;

        public SleepTask(int sleepSeconds, int returnValue) {
            super();
            this.sleepSeconds = sleepSeconds;
            this.returnValue = returnValue;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("begin to execute.");
            TimeUnit.SECONDS.sleep(sleepSeconds);
            System.out.println("end to execute.");
            return returnValue;
        }
    }

    public static void executorTest() {
        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
        int taskNum = POOL_SIZE;
        for (int i = 1; i <= taskNum; i++) {
            // 睡眠时间
            int sleep = taskNum - i;
            // 返回结果
            int value = i;
            // 向线程池提交任务
            Future<Integer> future = pool.submit(new SleepTask(sleep, value));
            // 保留每个任务的Future
            futureList.add(future);
        }
        // 轮询,获取完成任务的返回结果
        while (taskNum > 0) {
            for (Future<Integer> future : futureList) {
                Integer result = null;
                try {
                    result = future.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                // 任务已经完成
                if (result != null) {
                    System.out.println("result=" + result);
                    // 从future列表中删除已经完成的任务
                    futureList.remove(future);
                    taskNum--;
                    // 此处必须break，否则会抛出并发修改异常。（也可以通过将futureList声明为CopyOnWriteArrayList类型解决）
                    break; // 进行下一次while循环
                }
            }
        }
        // 所有任务已经完成,关闭线程池
        System.out.println("all over.");
        pool.shutdown();
    }

    public static void completionTest() {
        // 构建完成服务
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(pool);
        int taskNum = POOL_SIZE;
        for (int i = 1; i <= taskNum; i++) {
            int sleep = taskNum - i; // 睡眠时间
            int value = i; // 返回结果
            // 向线程池提交任务
            completionService.submit(new SleepTask(sleep, value));
        }
        // 按照完成顺序,打印结果
        for (int i = 0; i < taskNum; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        // 所有任务已经完成,关闭线程池
        System.out.println("all over.");
        pool.shutdown();
    }

    public static void main(String[] args) {
        executorTest();
//        completionTest();

    }

}
