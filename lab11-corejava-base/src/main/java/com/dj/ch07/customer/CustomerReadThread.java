package com.dj.ch07.customer;

/**读线程*/
public class CustomerReadThread extends Thread{
	//资源管理类，通过此引用可以调用其相关方法对资源进行读写  
	private ResourceManager resourceManger;
      
    public CustomerReadThread(ResourceManager resourceManger){  
        this.resourceManger = resourceManger;  
    }  
    //这个线程类负责不停的读取资源中的数据
    public void run(){  
        while(true){  
            this.resourceManger.read();  
        }  
    }  
}