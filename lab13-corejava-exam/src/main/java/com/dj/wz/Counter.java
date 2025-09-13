package com.dj.wz;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private static ThreadLocal<Integer> count = new ThreadLocal<Integer>();
    private Lock lock = new ReentrantLock();

    public Counter() {
        count.set(0);
    }

    public void incr(String userId) {
        int currCount = 0;
        lock.lock();
        currCount = count.get() + 1;
        count.set(currCount);
        lock.unlock();
        long time = System.currentTimeMillis();
        System.out.println("userId:" + userId + ",访问时间 time：" + time + "当前进程访问总次数：" + currCount);
    }

    public int get() {
        return count.get();
    }

}
