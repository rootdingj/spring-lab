package com.dj.ch07;
/**
 * ThreadTest2 实现 Runnable 接口
 * */
public class ThreadTest2 implements Runnable{

	public void run() {
		String name = Thread.currentThread().getName();
		for(int i=1;i<10;i++){
			System.out.println(i+":threadName:"+name);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		ThreadTest2 r = new ThreadTest2();  
		Thread t = new Thread(r,"newNm");
		String name = Thread.currentThread().getName();
		long c = System.currentTimeMillis();	
		for(int i=0;i<10;i++){
			System.out.println(i+":mainThreadNm:"+name);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		System.out.println(name);
		long d = System.currentTimeMillis();
		//时间差
		System.out.println(d-c);	
	}
}
