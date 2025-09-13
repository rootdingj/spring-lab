package com.dj.ant.ch01;

public class Singleton {
	
	// 使用volatile关键字保其可见性(轻量级)
	private volatile static Singleton instance = null;

	public Singleton() {}
	
	public static Singleton getInstance() {
		if(instance != null) {
			// 不创建
		}else {
			//创建实例之前可能会有一些准备性的耗时工作 
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (Singleton.class) {
				// 二次检查，以保证其不被重复实例化.
				if(instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
}
