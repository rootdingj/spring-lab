package com.dj.ch07.customer;

/**资源管理类*/
public class ResourceManager {
	//管理的两个资源 
	private Resource resourceA = new Resource();  
    private Resource resourceB = new Resource();  
    
    public ResourceManager(){  
        this.resourceA.setValue(0);  
        this.resourceB.setValue(0);  
    }
    
    /**资源的读取*/
    public int read(){
    	//读取A资源数据的时候要上锁,防止别人在此期间修改资源A中的数据
        synchronized(this.resourceA){  
            System.out.println(Thread.currentThread().getName() + "线程拿到了资源 resourceA的对象锁");  
            
           //读取Ｂ资源数据的时候要上锁,防止别人在此期间修改资源Ｂ中的数据
            synchronized(this.resourceB){  
                System.out.println(Thread.currentThread().getName() + "线程拿到了资源 resourceB的对象锁");  
            }  
            //如果俩个锁都拿到了，最后把读到值相加并返回
            return this.resourceA.getValue() + this.resourceB.getValue();  
        }  
    }  
    /**资源的改写*/
    public void write(int a,int b){  
    	
    	//写入Ｂ资源数据的时候要上锁,防止别人在此期间读取资源A中的数据(如果真被读取了那么这个读到数据就不准确了)
        synchronized(this.resourceB){  
            System.out.println(Thread.currentThread().getName() + "线程拿到了资源 resourceB 的对象锁");  
            
           //写入A资源数据的时候要上锁,防止别人在此期间读取资源A中的数据(如果真被读取了那么这个读到数据就不准确了)
            synchronized(this.resourceA){  
                System.out.println(Thread.currentThread().getName() + "线程拿到了资源 resourceA 的对象锁");  
                //如果俩个锁都拿到了，最后再设置他们的值
                this.resourceA.setValue(a);  
                this.resourceB.setValue(b);  
            }  
        }  
    } 
}
