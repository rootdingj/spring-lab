package com.dj.juc.base;

public class RunnableDemo implements Runnable {

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("Thread-" + name + " 开始执行!");
        int sum = 0;
        for (int i = 0; i <= 1000; i++) {
            sum += i;
        }
        System.out.println("Thread-" + name + " 执行完毕! sum=" + sum);
    }

    public static void main(String[] args) {
        Thread demo = new Thread(new RunnableDemo(), "RunnableDemo");
        demo.start();
    }

}
