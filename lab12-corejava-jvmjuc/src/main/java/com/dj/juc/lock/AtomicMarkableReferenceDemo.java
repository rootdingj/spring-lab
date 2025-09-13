 package com.dj.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReferenceDemo {
    
    private static AtomicInteger atomInt = new AtomicInteger(100);
    private static AtomicMarkableReference<Integer> atomMarkRef = new AtomicMarkableReference<Integer>(100, true);
    
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
               System.out.println("refT1-1 reference:"+atomMarkRef.getReference()+"，marked:"+atomMarkRef.isMarked());
               
               atomMarkRef.compareAndSet(100, 101, atomMarkRef.isMarked(), !atomMarkRef.isMarked());
               
               System.out.println("refT1-2 reference:"+atomMarkRef.getReference()+"，marked:"+atomMarkRef.isMarked());
               
               atomMarkRef.compareAndSet(101, 100, atomMarkRef.isMarked(), !atomMarkRef.isMarked());
               
               System.out.println("refT1-3 reference:"+atomMarkRef.getReference()+"，marked:"+atomMarkRef.isMarked());
            }
        });

        Thread refT2 = new Thread(new Runnable() {
            @Override
            public void run() {
               boolean marked = atomMarkRef.isMarked();
               try {
                   // 模拟：线程refT2取atomMarkRef对象后，睡眠了2s,期间线程refT1对atomMarkRef有做修改动作
                   TimeUnit.SECONDS.sleep(2);
               } catch (InterruptedException e) {
               }
               System.out.println("refT2-1 reference:"+atomMarkRef.getReference()+"，marked:"+marked);
               boolean result = atomMarkRef.compareAndSet(100, 101, marked, !marked);
               System.out.println("refT2-2 reference:"+atomMarkRef.getReference()+"，marked:"+atomMarkRef.isMarked());
               System.out.println(result==true? "AtomicMarkableReference CAS 操作成功":"AtomicMarkableReference CAS 操作失败"); // AtomicMarkableReference CAS 操作失败
            }
        });
        refT1.start(); refT2.start();
   }

}
