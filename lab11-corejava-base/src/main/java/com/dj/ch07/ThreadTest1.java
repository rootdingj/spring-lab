package com.dj.ch07;
/**
 * ThreadTest 扩展 java.lang.Thread 类
 * */
public class ThreadTest1 extends Thread {

	public void run() {
		Thread currentThread = Thread.currentThread();
		for (int i = 0; i < 10; i++) {
			System.out.println("线程名: " + currentThread.getName());
			System.out.println(i);
			if (i == 5) {
				try {
					// 谁执行谁休眠
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Thread t = new ThreadTest1();
//		System.out.println("main:"+t.getName());
//		System.out.println("main:"+t.getId());
		// 主线程main调用ThreadTest类中的run方法
//		t.run();    
		// 线程类启动线程
		t.start();
	}

}
