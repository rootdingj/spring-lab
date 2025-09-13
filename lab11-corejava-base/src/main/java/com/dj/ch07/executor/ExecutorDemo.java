package com.dj.ch07.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * */
public class ExecutorDemo {
	public static void main(String[] args) {
		//创建一个最多线程数为3的线程池执行器,以下三个任务将顺序执行，因为线程池中只有一个线程
//		ExecutorService executor = Executors.newFixedThreadPool(3);
		//为每个等待的任务创建一个新线程，所以，三个任务都并非执行
		ExecutorService executor = Executors.newCachedThreadPool();
//		Executors.newCachedThreadPool()--ExExecutorService-->Executor
		
		executor.execute(new PrintChar('a',5));
		try {
			Thread.sleep(100);
			System.out.println();
			System.out.println("...............................");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.execute(new PrintChar('b',5));
		try {
			Thread.sleep(100);
			System.out.println();
			System.out.println("...............................");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.execute(new PrintNum(5));
		//通知执行器关闭，不再接受新任务，但是线程中现有的未完成的任务将继续执行直到完成
		executor.shutdown();
	}
}
class PrintChar implements Runnable{
	private char charToPrint;
	private int times;
	public PrintChar() {
	}
	public PrintChar(char c, int t) {
		this.charToPrint = c;
		this.times = t;
	}
	public void run() {
		for(int i=0;i<times;i++){
			System.out.print("PrintChar："+charToPrint+"："+i+"\t");
		}
	}
}
class PrintNum implements Runnable{
	private int lastNum;
	public PrintNum() {
	}
	public PrintNum(int n) {
		this.lastNum = n;
	}
	public void run() {
		for(int i=1;i<=lastNum;i++){
			System.out.print("PrintNum："+i+"\t");
		}
	}
}

