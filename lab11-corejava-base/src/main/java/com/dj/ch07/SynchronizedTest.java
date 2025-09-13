package com.dj.ch07;

/**
 * 线程同步
 * 参考： http://blog.csdn.net/ghsau/article/details/7424694
 * Java线程的特性 ： 可见性和有序性、原子性
 * 另外volatile也可以实现线程同步，可以参考并发编程volatile
 * */
public class SynchronizedTest {
	public static void main(String[] args) {
		Student s = new Student();
//		Student s2 = new Student();	
		
		Thread t1 = new MyThread(s);
		Thread t2 = new MyThread(s);
//		Thread t2 = new MyThread(s2);
		
		t1.start();
		t2.start();
	}
}

class MyThread extends Thread{
	private Student s;
	public MyThread() {
	}
	public MyThread(Student s) {
		this.s = s;
	}
	public void run() {
//		s.test();
//		s.test2();
		s.test3();
	}
}

/**   
知识点一：
         多个线程调用同一个对象中的方法的时候,如果方法中有操作到一些成员变量,那么这时候就会产生一些数据安全的问题.
        锁的原理:java中每个对象都有一个内置锁当程序运行到非静态的synchronized同步方法上时，自动获得与正在执行代码类的当前实例（this实例）有关的锁。
 获得一个对象的锁也称为获取锁、锁定对象、在对象上锁定或在对象上同步。
* */
class Student{
	private int a;
	
	/**
	   	知识点三：锁的第二种用法，同步非静态方法；锁为实例对象本身(this)
	 * */
	public synchronized void test(){
		String name =Thread.currentThread().getName();
		for(int i=0;i<10;i++){
			System.out.println("threadName="+name + ": a = " + a);
			a++;
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	      知识点三：锁的方法第一种用法，同步代码块，锁对象在同一时刻只给一个代码块，锁对象默认是this 
	 * */
	public void test2(){
//		Object o = new Object();
		/*	
		 	局部变量，调用一次方法就有一个对象，两个对象就是两把锁，需要两个线程抢的是同一个锁对象，一把锁才能线程同步
		  
			获得当前类的Class对象(3种方法)，一个类有且只有一个Class对象保证是类中独一无二的对象，
			线程才能同步一般情况this就可以的
		 * */
		synchronized (this){  
			String name =Thread.currentThread().getName();
			for (int i = 0; i < 10; i++) {
				System.out.println("threadName="+name + ": a = " + a);
				a++;
				try {
					if(a==5){
						this.wait(100);  //哪个指令先到就听谁的
//						this.wait();    //不加时间就需要notify()来叫醒
					}
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
//			this.notify();
//			this.notifyAll();
		}
	}
	/**
	       知识点四：同步方法三，静态同步方法 ，锁为类对象本身(Student.class)
	                          一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁
	 * */
	public static synchronized void test3(){
		String name = Thread.currentThread().getName();
		for(int i=0;i<10;i++){
			System.out.println("threadname+"+name+": i="+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	//test4等价于test3
	public static int test4(){
	      synchronized(Student.class){
	      }
		return 0;
	}
}