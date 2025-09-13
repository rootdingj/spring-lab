package com.dj.juc.base;

/**
 * 安全的线程中断
 * 
 * @author Steven
 * @date 2020/08/09
 */
public class SafeInterruptDemo extends Thread {

    /**
     *  中断标志
     */
    private boolean flag = false;

    /**
     *  将需要中断的线程作为参数传过来用以进行中断
     * 
     * @param t
     */
    public void cancel(Thread t) {
        System.out.println("ready stop currentThread...");
        flag = true;
        // 将需要中断的线程的中断标志位设置为true
        t.interrupt();
        System.out.println(t.getName() + "-cancel() :" + t.isInterrupted());
    }

    @Override
    public void run() {
        int i = 0;
        boolean isInterrupted = Thread.currentThread().isInterrupted();
        System.out.println(getName() + "-run() :" + isInterrupted);
        while (!flag && !isInterrupted) {
            System.out.println(i++);
            try {
                synchronized (this) {
                    if (i > 3) {
                        Thread.sleep(1000 * 60);
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SafeInterruptDemo demo = new SafeInterruptDemo();
        demo.start();
        Thread.sleep(1000L);
        demo.cancel(demo);
    }
}
