package com.dj.juc.base;

public class ThreadDemo extends Thread {

    public ThreadDemo(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Thread-" + getName() + " 开始执行!");
        int sum = 0;
        for (int i = 0; i <= 1000; i++) {
            sum += i;
        }
        System.out.println("Thread-" + getName() + " 执行完毕! sum=" + sum);
    }

    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo("ThreadDemo");
        demo.start();
    }

}
