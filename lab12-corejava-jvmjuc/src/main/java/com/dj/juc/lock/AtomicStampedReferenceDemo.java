 package com.dj.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
     
     private static AtomicInteger atomInt = new AtomicInteger(100);
     private static AtomicStampedReference<Integer> atomStampedRef = new AtomicStampedReference(100, 0);
     
     public static void main(String[] args) throws InterruptedException {
    
         Thread intT1 = new Thread(new Runnable() {
             @Override
             public void run() {
                 atomInt.compareAndSet(100, 101);
                 atomInt.compareAndSet(101, 100);
             }
         });

         Thread intT2 = new Thread(new Runnable() {
             @Override
             public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                boolean result = atomInt.compareAndSet(100, 101);
                System.out.println(result==true? "AtomicInteger CAS 操作成功":"AtomicInteger CAS 操作失败"); // AtomicInteger CAS 操作成功
             }
         });
         intT1.start();intT2.start();
         intT1.join();intT2.join();
         
         Thread refT1 = new Thread(new Runnable() {
             @Override
             public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
                System.out.println("refT1-1 reference:"+atomStampedRef.getReference()+"，stamp:"+atomStampedRef.getStamp());
                
                atomStampedRef.compareAndSet(100, 101, atomStampedRef.getStamp(), atomStampedRef.getStamp() + 1);
                
                System.out.println("refT1-2 reference:"+atomStampedRef.getReference()+"，stamp:"+atomStampedRef.getStamp());
                
                atomStampedRef.compareAndSet(101, 100, atomStampedRef.getStamp(), atomStampedRef.getStamp() + 1);
                
                System.out.println("refT1-3 reference:"+atomStampedRef.getReference()+"，stamp:"+atomStampedRef.getStamp());
             }
         });

         Thread refT2 = new Thread(new Runnable() {
             @Override
             public void run() {
                int stamp = atomStampedRef.getStamp();
                try {
                    // 模拟：线程refT2取atomStampedRef对象后，睡眠了2s,期间线程refT1对atomStampedRef有做修改动作
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                }
                System.out.println("refT2-1 reference:"+atomStampedRef.getReference()+"，stamp:"+stamp);
                boolean result = atomStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println("refT2-2 reference:"+atomStampedRef.getReference()+"，stamp:"+atomStampedRef.getStamp());
                System.out.println(result==true? "AtomicStampedReference CAS 操作成功":"AtomicStampedReference CAS 操作失败"); // AtomicStampedReference CAS 操作失败
             }
         });
         refT1.start(); refT2.start();
    }

}
