package com.dj.ch07.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 线程间的协作：</br>
 *    使用条件便于线程间的通信
 *    一个线程可以指定在某种条件下该做什么，条件是通过Lock对象的newCondition()方法而创建的对象
 *    一旦创建了条件，就可以使用await()、signal()..等方法来实现线程之间的通信
 * 警告：没有锁对象就调用这些方法会程序抛出异常
 * await():当前线程等待直到发生某个条件
 * signal():唤醒一个等待线程
 * */
public class ThreadCooperation {
	private static Account account = new Account();
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new DepositTask());
		executor.execute(new WithdrawTask());
		executor.shutdown();
		System.out.println("线程一、线程二的金额...");
	}
	/**
	 * 存钱
	 * */
	public static class DepositTask implements Runnable{
		public void run() {
			try {
				while(true){
					account.deposit((int) (Math.random()*10+1));
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 取钱
	 * */
	public static class WithdrawTask implements Runnable{
		public void run() {
			while(true){
				account.withdraw((int) (Math.random()*10+1));
			}
		}
	}
	/**
	 * 账户
	 * */
	private static class Account{
		private static Lock lock = new ReentrantLock();
		//通过lock对象的newCondition()方法,创建一个条件
		private static Condition newDeposit = lock.newCondition();
		private int balance = 0;
		public int getBalance(){
			return balance;
		}
		/**取钱*/
		public void withdraw(int amount){
			//为了同步这些操作，使用一个具有条件的锁newDeposit(增加到账户的新存款)；
			//使用条件就必须先获得锁
			lock.lock();
			try {
				while(balance<amount){
					System.out.println("等待用户存钱");
					newDeposit.await();
				}
				balance -=amount;
				System.out.println("取了"+amount+"元；还剩"+getBalance()+"元");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
		/**存钱*/
		public void deposit(int amount){
			lock.lock();
			balance+=amount;
			System.out.println("存了"+amount+"元；还剩下"+getBalance()+"元");
			newDeposit.signalAll();
			lock.unlock();
		}
	}
}