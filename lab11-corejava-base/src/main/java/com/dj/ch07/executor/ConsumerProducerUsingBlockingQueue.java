package com.dj.ch07.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 生产者消费者问题（阻塞队列实现）：</br>
 * 线程阻塞队列：在试图向一个满队列添加元素或者从空队列中删除元素时会导致线程阻塞
 * 		put():在队尾插入一个元素，若队列已满则等待
 * 		take():返回并删除这个队列的头，如果队列为空则等待
 * 三个具体的阻塞队列：
 * 		ArrayBlockingQueue:用数组来实现。必须指定一个容量或者可选的公平性来构造ArrayBlockingQueue
 * 		LinkedBlockingQueue:用链表实现。可用来创建不受限或受限的LinkedBlockingQueue
 * 		PriorityBlockingQueue：优先队列。可以创建不受限的或者受限优先的队列
 * 对于不受限的队列，put方法永远不会阻塞
 * */
public class ConsumerProducerUsingBlockingQueue {
	private static ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue(2);
	
	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(new ProducerTask());
		executor.execute(new ConsumerTask());
		executor.shutdown();
	}
	
	private static class ProducerTask implements Runnable{
		public void run() {
			try {
				int i=1;
				while(true){
					System.out.println("生产者写出数据"+i);
					buffer.put(i++);
					Thread.sleep((int) (Math.random()*10000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private static class ConsumerTask implements Runnable{
		public void run() {
			try {
				while(true){
					System.out.println("消费者读出数据"+buffer.take());
					Thread.sleep((int) (Math.random()*10000));
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
