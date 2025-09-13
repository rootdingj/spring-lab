package com.dj.juc.base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CooperationCondition {

    private int number = 10;
    private Lock lock = new ReentrantLock();
    private Condition evenCondition = lock.newCondition();
    private Condition oddCondition = lock.newCondition();

    private class EvenTask implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                while (number >= 0 && (number & 1) == 0) {
                    System.out.println("偶数: " + (number--));
                    oddCondition.signal();
                    evenCondition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private class OddTask implements Runnable {
        @Override
        public void run() {
            lock.lock();
            try {
                while (number >= 0 && (number & 1) == 1) {
                    System.out.println("奇数: " + (number--));
                    evenCondition.signal();
                    oddCondition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        CooperationCondition demo = new CooperationCondition();
        new Thread(demo.new EvenTask()).start();
        new Thread(demo.new OddTask()).start();
    }

}
