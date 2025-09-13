package com.dj.ch07.customer;

/**写线程*/
public class CustomerWriteThread extends Thread{
	//资源管理类，通过此引用可以调用其相关方法对资源进行读写  
	private ResourceManager resourceManger;
	/**将要写入资源的数据  */
    private int a,b;
    public CustomerWriteThread(ResourceManager resourceManger,int a,int b){  
        this.resourceManger = resourceManger;  
        this.a = a;  
        this.b = b;  
    }  
    //这个线程类负责不停的将数据写入到资源中
    public void run(){  
        while(true){  
            this.resourceManger.write(a, b);
        }  
    }  
}


