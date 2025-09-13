package com.dj.ch07.bankQueue;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainTest {

	public static void main(String[] args) {
		//产生4个普通窗口
		for(int i = 1;i<5;i++){
			ServiceWindow commonWin = new ServiceWindow();
			commonWin.setWindowId(i);
			commonWin.start();
		}
		//产生1个快速窗口
		ServiceWindow quickWin = new ServiceWindow();
		quickWin.setType(CustomerType.QUICK);
		quickWin.start();
		//产生1个VIP窗口
		ServiceWindow vipWin = new ServiceWindow();
		vipWin.setType(CustomerType.VIP);
		vipWin.start();
		//普通客户取号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable() {
					@Override
					public void run() {
						Integer number = 
								NumberMachine.getInstance().getCommonManage().generateNewNumber();
						System.out.println(number + "号普通客户等待服务！");
					}
				}, 
				0, 
				Constants.COMMON_CUSTOMER_INTERVAL_TIME, 
				TimeUnit.SECONDS
		);
		//快速客户取号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable() {
					@Override
					public void run() {
						Integer number = 
								NumberMachine.getInstance().getQuickManage().generateNewNumber();
						System.out.println(number + "号快速客户等待服务！");
					}
				}, 
				0, 
				Constants.COMMON_CUSTOMER_INTERVAL_TIME*2, 
				TimeUnit.SECONDS
				);
		//VIP客户取号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable() {
					@Override
					public void run() {
						Integer number = 
								NumberMachine.getInstance().getVipManage().generateNewNumber();
						System.out.println(number + "号VIP客户等待服务！");
					}
				}, 
				0, 
				Constants.COMMON_CUSTOMER_INTERVAL_TIME*6, 
				TimeUnit.SECONDS
				);
	}
}