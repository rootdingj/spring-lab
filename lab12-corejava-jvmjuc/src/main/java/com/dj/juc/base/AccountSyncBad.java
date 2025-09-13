package com.dj.juc.base;

public class AccountSyncBad implements Runnable {
    // 共享资源(临界资源)
    static int i = 0;

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
            increase();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(new AccountSyncBad());
        Thread t2=new Thread(new AccountSyncBad());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
        // 有synchronized 修饰，输出 1513957（不是2000000）
        // i 由 static 修饰，是类变量，t1、t2将各自进入各自的对象锁
    }

}
