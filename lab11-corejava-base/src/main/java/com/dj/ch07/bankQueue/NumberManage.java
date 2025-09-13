package com.dj.ch07.bankQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * 号码管理
 * */
public class NumberManage {

	/**下一个号码*/
	private int lastNumber = 1;
	/**号码存放集合*/
	private List<Integer> queueNum = new ArrayList<Integer>();
	
	/**产生一个新号码*/	
	public synchronized Integer generateNewNumber(){
		queueNum.add(lastNumber);
		return lastNumber++;
	}
	/**处理一个服务号码*/	
	public synchronized Integer fetchServiceNumber(){
		Integer number = null;
		if(queueNum.size()>0){
			number = queueNum.remove(0);
		}
		return number;
	}
	
}
