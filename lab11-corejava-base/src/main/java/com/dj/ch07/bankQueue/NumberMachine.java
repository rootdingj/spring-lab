package com.dj.ch07.bankQueue;

/**
 * 号码产生机器</br>
 * 	    号码产生机器是三个号码管理对象共享的唯一对象，所以NumberMachine应该定义成单例的
 * */
public class NumberMachine {

	/*
	 * 三个号码管理对象变量
	 * */
	private NumberManage commonManage = new NumberManage();
	private NumberManage quickManage = new NumberManage();
	private NumberManage vipManage = new NumberManage();
	public NumberManage getCommonManage() {
		return commonManage;
	}
	public NumberManage getQuickManage() {
		return quickManage;
	}
	public NumberManage getVipManage() {
		return vipManage;
	}
	/*
	 * 单例套路，NumberMachine的构造方法必须是私有的，如何在其他类中拿到NumberMachine的对象：
	 * 1、私有构造方法
	 * 2、私有的静态成员变量，初始化
	 * 3、公共的静态方法，供其他类获取NumberMachine实例
	 * */
	private NumberMachine(){}
	private static NumberMachine instance = new NumberMachine();
	
	public static NumberMachine getInstance(){
		return instance;
	}
	
}
