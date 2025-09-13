package com.dj.juc.utilClass;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch 线程计数器
 * 
 * @author Steven
 * @date 2020/08/12
 * 跑步比赛时，假设有 8名运动员参与比赛，裁判员在终点会为这8名运动员分别计时，每当1名运动员到达终点的时候，对于裁判员来说就少了1个计时任务。
 * 直到所有运动员都到达终点了，裁判员的任务也才完成。
 */
public class CountDownLatchDemo {
    /**
     * 运动员数量
     */
    private static final int COUNT_NUM = 8;

    private static CountDownLatch startSignal = new CountDownLatch(1);
    // 用来表示裁判员需要维护的是6个运动员
    private static CountDownLatch endSignal = new CountDownLatch(COUNT_NUM);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT_NUM);
        for (int i = 0; i < COUNT_NUM; i++) {
            executorService.execute(() -> {
                String threadName = Thread.currentThread().getName();
                try {
                    System.out.println("运动员" + threadName + "在等待裁判员响哨...");
                    startSignal.await();
                    System.out.println("运动员" + threadName + "在全力冲刺");
                    endSignal.countDown();
                    System.out.println("运动员" + threadName + "到达终点，startCount：" + startSignal.getCount()
                        + "到达终点，endCount：" + endSignal.getCount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        String mainThread = Thread.currentThread().getName();
        System.out.println("裁判员" + mainThread + "说:跑！！！");
        startSignal.countDown();
        endSignal.await();
        System.out.println("裁判员" + mainThread + "说：所有运动员到达终点，比赛结束！startCount:" + startSignal.getCount()
            + "到达终点，endCount：" + endSignal.getCount());
        executorService.shutdown();
    }
    
}