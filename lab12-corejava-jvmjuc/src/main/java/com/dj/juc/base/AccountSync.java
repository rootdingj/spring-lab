package com.dj.juc.base;

public class AccountSync implements Runnable {
    // 共享资源(临界资源)
    int i = 0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase() {
        i++;
    }

    /*@Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }*/
    
    @Override
    public void run() {
        // 使用同步代码块对变量i进行同步操作,锁对象为instance
        synchronized (this) {
            for (int j = 0; j < 1000000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AccountSync instance = new AccountSync();
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(instance.i);
        // 有synchronized 修饰，输出 2000000
        // 没有synchronized 修饰，输出 1076007（不是2000000）
    }

}
