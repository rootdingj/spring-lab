package com.dj.ch07.executor;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *生产者、消费者问题（自定义队列实现）:</br>
 * 	假设使用缓冲区存储整数，缓冲区的大小是受限的
 * 	缓冲区提供write(int)方法将一个int值添加到缓冲区中，还提供了read()方法从缓冲区中读取和删除一个int值
 * 为了同步操作，使用具有两个条件的锁：notEmpty()和notFull()
 * 		当任务向缓冲区添加一个int时，如果缓冲区是满的，那么任务将会等待notFull状态；
 * 		当.........删除..................空的，............notEmpty状态。
 * */
public class ConsumerProducer {
	private static Buffer buffer = new Buffer();
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new CunsumerTask());
		executor.shutdown();
	}
	
	/**
	 * 生产者：向缓冲区中新增一个int型数据
	 * */	
	private	static class ProducerTask implements Runnable{
		public void run() {
			try {
				int i = 1;
				while(true){
					System.out.println("生产者写入数据"+i);
					buffer.write(i++);
					Thread.sleep((int) (Math.random()*10000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 消费者：向冲缓冲区中删除一个int的数据
	 * */
	private static class CunsumerTask implements Runnable{
		public void run() {
			try {
				while(true){
					System.out.println("消费者读出数据"+buffer.read());
					Thread.sleep((int) (Math.random()*10000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class Buffer{
		/**buffer的大小*/
		private static final int CAPACITY = 1;
		/**缓冲区实际上是一个先进先出的队列*/
		private LinkedList<Integer> queue = new LinkedList();
		
		private static Lock lock =new ReentrantLock();
		//状态和锁捆绑在一起，在应用一个状态之前必须获取一个锁
		private static Condition notEmpty = lock.newCondition();
		private static Condition notFull = lock.newCondition();

		public void write(int value){
			lock.lock();
			try {
				while(queue.size()==CAPACITY){
					System.out.println("等待notFull条件");
					notFull.await(); //造成当前线程在接到信号或被中断之前一直处于等待状态。
				}
				queue.offer(value); //将指定元素添加到此列表的末尾（最后一个元素）
				notEmpty.signal(); //唤醒一个等待线程
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock.unlock();
		}
		public int read(){
			int value = 0;
			lock.lock();
			try {
				while(queue.isEmpty()){
					System.out.println("等待notEmpty条件");
					notEmpty.await();
				}
				value=queue.remove();
				notFull.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lock.unlock();
			return value;
		}
	}
}

