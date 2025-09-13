package com.dj.juc.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {

    // 使用AtomicInteger定义num
    static AtomicInteger num = new AtomicInteger();
    static int n = 0;

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        // 使用incrementAndGet函数进行自增操作（深入看CAS实现源码）
                        System.out.println(num.incrementAndGet());
//                        System.out.println(n++);
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
    }

}
