package com.dj.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * @Auther: steven
 * @Date: 2020/08/28
 * @Description: ZK 分布式锁 demo
 */
public class DistributedLock {

    public static void main(String[] args) {
        CuratorFramework curatorClient = CuratorOperDemo.getInstance();
        InterProcessMutex lock = new InterProcessMutex(curatorClient, "/distributedLock");
        //模拟50个线程抢锁
        for (int i = 0; i < 50; i++) {
            new Thread(new TestThread(i, lock)).start();
        }
    }

    static class TestThread implements Runnable {
        private int seq;
        private InterProcessMutex lock;

        public TestThread(int seq, InterProcessMutex lock) {
            this.seq = seq;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.acquire();
                System.out.println("第"+seq+"线程获取到了锁");
                //等到1秒后释放锁
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
