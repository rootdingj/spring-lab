package com.dj.ch07.race;

//龟兔赛跑  使用Runnable
public class RaceRunnable implements Runnable{
	//静态变量 所有对象共享的 flag为true时候比赛结束
	private static boolean flag = false;
	private static String name = "";
	public void run(){
		int i = 1;
		int rand;
		for(;i <= 100;i++){	
			if(flag){
				break;
			}
			System.out.println(Thread.currentThread().getName() + ":" + i);
			rand = (int)(Math.random()*100 + 1);
			try{
				Thread.sleep(rand);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		flag = true;
	}
	public static void main(String[] args){
		Runnable r = new RaceRunnable();
		Thread rabbit = new Thread(r,"Rabbit");	
		Thread turtel = new Thread(r,"Turtel");
		System.out.println("Ready!Go!");
		rabbit.start();
		turtel.start();
		try{
			rabbit.join();
			turtel.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Game Over!");
	}
}
