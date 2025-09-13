package com.dj.juc.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        String name = Thread.currentThread().getName();
        System.out.println("Thread-" + name + " 开始执行!");
        int sum = 0;
        for (int i = 0; i <= 1000; i++) {
            sum += i;
        }
        System.out.println("Thread-" + name + " 执行完毕! sum=" + sum);
        return sum;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        FutureTask<Integer> task = new FutureTask<>(new CallableDemo());
//        Thread demo = new Thread(task, "CallableDemo");
//        demo.start();
//        System.out.println("CallableDemo 返回值：" + task.get());
        
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> future = executorService.submit(new CallableDemo());
        System.out.println("Executors 返回值：" + future.get());
    }

}
