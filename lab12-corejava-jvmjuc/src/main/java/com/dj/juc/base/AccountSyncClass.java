package com.dj.juc.base;

public class AccountSyncClass implements Runnable {
    static int i = 0;

    /**
     * 作用于静态方法,锁是当前class对象,也就是 AccountSyncClass 对应的class对象
     */
    public static synchronized void increase() {
        i++;
    }

    /**
     * 非静态,访问时锁不一样不会发生互斥
     */
    public synchronized void increaseObj() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000000; j++) {
//            increase();
            increaseObj();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AccountSyncClass());
        Thread t2 = new Thread(new AccountSyncClass());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
