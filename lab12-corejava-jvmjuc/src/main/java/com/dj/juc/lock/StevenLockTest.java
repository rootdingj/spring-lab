 package com.dj.juc.lock;

 public class StevenLockTest {
     static int count = 0;
     
     static StevenLock lock = new StevenLock();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run () {
                try {
                    lock.lock();
                    for (int i = 0; i < 10000; i++) {
                        count++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println("count:"+count);
    }

}
