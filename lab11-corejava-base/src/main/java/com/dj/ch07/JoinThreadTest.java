package com.dj.ch07;

public class JoinThreadTest extends Thread{
	
	public void run() {
		String name = getName();
		for(int i=0;i<10;i++){
			System.out.println(i+":threadName:"+name);
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws Exception {
		System.out.println("main start...");
		JoinThreadTest jt = new JoinThreadTest();
		jt.start();
		//main线程中调用了jt.join()，那么main线程必须等待jt这个线程结束后才能结束
		//A线程调用B.join方法,那么就是A线程要等待B线程运行结束后才能接着运行
		//挂起、等待
		jt.join();  
		System.out.println("main over...");
	}
}
