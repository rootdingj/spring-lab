package com.dj.ch07.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程同步：</br>
 *        使用synchronized方法或语句比使用相互排斥的显示锁简单些然而，
 *        显示锁对同步具有状态的线程更加灵活、直观(线程间的协作)
 * */
public class AccountWithoutSync {
	private static Account account = new Account();
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		for(int i=0;i<100;i++){
			executor.execute(new AddPennyTask());
		}
		executor.shutdown();
		//测试线程是否终止
		while(!executor.isTerminated()){
		}
		System.out.println("what is balance?"+account.getBalance());
	}
	/**
	 * 创建一个往account中增加penny的线程(静态内部类)</br>
	 * */
	private static class AddPennyTask implements Runnable{
		public void run() {
			account.deposit(1);
		}
	}
	/**
	 * 账户类(静态内部类)</br>
	 * 1、显示锁
	 * 2、信号量：信号量用来限制访问共享资源的线程数。
	 * 		        在访问资源之前，线程必须从信号量处获取许可；访问完资源之后，这个线程必须将许可返回给信号量
	 * 	  Semaphore(numberOfPermits:int):创建一个带指定数目许可的信号量，公平策略为false
	 * 	  Semaphore(numberOfPermits:int,fair:boolean):创建一个带指定数目许可和公平策略的信号量
	 * 	  acquire():获取这个信号量的许可；如果没有许可可用，线程就被锁住直到有可用许可为止
	 * 	  release():释放一个许可给该信号量
	 * 只用一个许可得信号量可以用来模拟一个相互排斥的锁		
	 * */
	private static class Account{
		private int balance=0;
		//方法一：用显示锁
		private static Lock lock = new ReentrantLock();
		//方法二：用信号量
//		private static Semaphore semaphore = new Semaphore(1);
		public int getBalance() {
			return balance;
		}
		/**
		 * 往account中增加penny
		 * */
		public void deposit(int amount) {
			lock.lock();
			try {
//				semaphore.acquire();
				int newBalance = balance+amount; 
				Thread.sleep(5);
				balance = newBalance;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				lock.unlock();//释放锁
//				semaphore.release();
			}
		}
	}
}
