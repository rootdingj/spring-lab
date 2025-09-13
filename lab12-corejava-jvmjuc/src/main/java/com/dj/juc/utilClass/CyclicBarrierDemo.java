package com.dj.juc.utilClass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier
 * @author Steven
 * @date 2020/08/12
 * 跑步比赛时，开始比赛前运动员们分别进入自己的跑道并做好准备，待8名运动员都准备完毕后，裁判员才说：各就各位，跑...。
 */
public class CyclicBarrierDemo {
    /**
     * 运动员数量
     */
    private static final int COUNT_NUM = 8;

    // 所有运动员进入赛道并准备好后，裁判员才可以开指令枪
    private static CyclicBarrier barrier = new CyclicBarrier(COUNT_NUM, () -> {
        System.out.println("裁判员: 各就各位，跑...");
    });

    public static void main(String[] args) {
        System.out.println("运动员开始进入赛道...");
        ExecutorService service = Executors.newFixedThreadPool(COUNT_NUM);
        for (int i = 0; i < COUNT_NUM; i++) {
            service.execute(() -> {
                try {
                    String name = Thread.currentThread().getName();
                    System.out.println("运动员"+ name + ":准备完毕！");
                    barrier.await();
                    System.out.println("运动员"+ name + ":开始狂奔...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        
    }

}
