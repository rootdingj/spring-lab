package com.dj.ch07;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Java 5以后创建线程还有第三种方式：实现Callable接口</br>
 * 该接口中的call方法可以在线程执行结束时产生一个返回值
 * 
 * 第四中方法见线程池Executor ThreadPoolExecutor
 * */
@SuppressWarnings("rawtypes")
public class CallableTest implements Callable {
	private int upperBounds;
	public CallableTest(int upperBounds) {
		this.upperBounds = upperBounds;
	}

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i=0;i<=upperBounds;i++){
			sum +=i;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		List<Future<Integer>> list = new ArrayList();
		ExecutorService service = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			list.add(service.submit(new CallableTest((int)Math.random()*100)));
		}
		int sum = 0;
		for (Future<Integer> future:list){
			while(!future.isDone());
			try {
				sum += future.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(sum);
		}
	}

}
