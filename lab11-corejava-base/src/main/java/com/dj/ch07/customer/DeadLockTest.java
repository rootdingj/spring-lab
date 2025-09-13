package com.dj.ch07.customer;

/**死锁测试*/
public class DeadLockTest {
    
    public static void main(String[] args) {
    	//创建一个资源管理器对象 内部默认有俩个可以使用的资源对象
    	ResourceManager resourceManager = new ResourceManager(); 
    	//读线程
    	CustomerReadThread read = new CustomerReadThread(resourceManager);  
    	//写线程
    	CustomerWriteThread write = new CustomerWriteThread(resourceManager,2,4);  
    	
    	//开始不停的读取资源管理器中资源的数据
    	read.start();
    	/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
    	//开始不停的向资源管理器中的资源写入数据
    	write.start();  
	}
}
