package com.dj.ch07;


public class InterruptTest extends Thread{
	
	public void run() {
		String name = getName();
		for(int i=0;i<10;i++){
			System.out.println(i+":threadName:"+name);
			if(i==5){
				try {
					Thread.sleep(1000*10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) throws Exception {
		InterruptTest ow = new InterruptTest();
		ow.start();
		Thread.sleep(10);
		// 中断某个线程，中断的结果线程是死亡、还是等待新的任务或是继续运行至下一步，就取决于这个程序本身。
		System.out.println(ow.interrupted());
		System.out.println(".....1");
//		ow.interrupt();
//		ow.interrupt();
		
	}
}
