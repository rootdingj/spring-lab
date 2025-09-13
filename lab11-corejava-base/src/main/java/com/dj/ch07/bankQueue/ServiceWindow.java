package com.dj.ch07.bankQueue;

import java.util.Random;
import java.util.concurrent.Executors;

public class ServiceWindow {
	/**客户类型*/
	private CustomerType type = CustomerType.COMMON;
	private int windowId = 1;
	public CustomerType getType() {
		return type;
	}
	public void setType(CustomerType type) {
		this.type = type;
	}
	public int getWindowId() {
		return windowId;
	}
	public void setWindowId(int windowId) {
		this.windowId = windowId;
	}

	public void start(){
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				do{
					switch(type){
					case COMMON:
						getCommonService();
						break;
					case QUICK:
						getQuickService();
						break;
					case VIP:
						getVIPService();
						break;
					}
				}while(true);
			}
		});
	}
	
	public void getCommonService() {
		String windowName = "第"+windowId+"号"+type+"窗口";
		Integer number = 
				NumberMachine.getInstance().getCommonManage().fetchServiceNumber();
		System.out.println(windowName + "正在获取任务");
		if(number!=null){
			System.out.println(windowName+"为第"+number+"个"+"普通客户服务");
			long beginTime = System.currentTimeMillis();
			int maxRand = Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME; 
			int serverTime = new Random().nextInt(maxRand) + 1 + Constants.MIN_SERVICE_TIME; 
			try {
				Thread.sleep(serverTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long endTime = System.currentTimeMillis();
			long costTime = beginTime - endTime;
			System.out.println(windowName+"为第"+number+"个"+"普通客户完成服务");
		}else{
			System.out.println(windowName+"没有取到任务，先休息1秒钟！");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void getQuickService() {
		String windowName = "第"+windowId+"号"+type+"窗口";
		Integer number = 
				NumberMachine.getInstance().getQuickManage().fetchServiceNumber();
		System.out.println(windowName + "正在获取任务");
		if(number!=null){
			System.out.println(windowName+"为第"+number+"个"+type+"客户服务");
			long beginTime = System.currentTimeMillis();
//			int maxRand = Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME; 
//			int serverTime = new Random().nextInt(maxRand) + 1 + Constants.MIN_SERVICE_TIME; 
			try {
				Thread.sleep(Constants.MIN_SERVICE_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long endTime = System.currentTimeMillis();
			long costTime = beginTime - endTime;
			System.out.println(windowName+"为第"+number+"个"+type+"客户完成服务");
		}else{
			System.out.println(windowName+"没有取到任务!");
			getCommonService();
		}
	}
	
	public void getVIPService() {
		String windowName = "第"+windowId+"号"+type+"窗口";
		Integer number = 
				NumberMachine.getInstance().getVipManage().fetchServiceNumber();
		System.out.println(windowName + "正在获取任务");
		if(number!=null){
			System.out.println(windowName+"为第"+number+"个"+type+"客户服务");
			long beginTime = System.currentTimeMillis();
			int maxRand = Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME; 
			int serverTime = new Random().nextInt(maxRand) + 1 + Constants.MIN_SERVICE_TIME; 
			try {
				Thread.sleep(serverTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long endTime = System.currentTimeMillis();
			long costTime = beginTime - endTime;
			System.out.println(windowName+"为第"+number+"个"+type+"客户完成服务");
		}else{
			System.out.println(windowName+"没有取到任务!");
			getCommonService();
		}
	}
}