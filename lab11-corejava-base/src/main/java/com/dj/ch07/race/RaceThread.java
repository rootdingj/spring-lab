package com.dj.ch07.race;

//龟兔赛跑  使用 Thread
public class RaceThread extends Thread{
	//静态变量 所有对象共享的 flag为true时候比赛结束
	private static boolean flag = false;
	private static String name = "";
	public RaceThread(String name){
		super(name);
	}
	public void run(){
		int rand;
		for(int i=1;i <= 100;i++){
			if(flag){
				break;
			}
			System.out.println(getName() + ":" + i);
			if(i==100){
				name = getName();
			}
			//每次跑完一步都要随机休息一下
			rand = (int)(Math.random()*100 + 1);
			try{
				sleep(rand);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		flag = true;
	}
	public static void main(String[] args){
		Thread rabbit = new RaceThread("Rabbit");	
		Thread turtle = new RaceThread("Turtel");
		System.out.println("Ready!Go!");
		rabbit.start();
		turtle.start();
		try{
			//主线程要等这个两个线程都结束了之后才能结束
			rabbit.join();
			turtle.join();	
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println(name+" to win the match!");
		System.out.println("Game Over!");
	}
}
