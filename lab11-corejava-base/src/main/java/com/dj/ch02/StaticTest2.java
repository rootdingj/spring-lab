package com.dj.ch02;

/**
 * 
 * @ClassName: StaticTest2 
 * @Description: new一个对象的时候JVM都做了那些事情
 * @author Steven 
 * @date 2016年6月7日
 */
public class StaticTest2 {
	
	private static String name;
	private int age;
	
	//匿名代码块
	{
		name = "tom";
		age = 30;
		System.out.println("匿名代码块...");	
	}
	//静态代码块
	static{
		name = "tom";
//		age = 12; //false
		//new StaticTest3();
		System.out.println("静态代码块..."+name);	
	}
	//无参构造
	public StaticTest2() {
		this.name = "zhangsan";
		this.age = 20;
		System.out.println("无参构造方法...name="+name+" age="+age);	
	}
	//实例方法
	public void test(){
		name = "zhangsan";
		System.out.println("实例方法..."+name);
	}
	//静态方法
	public static void say(){
		name = "zhangsan";
		System.out.println("静态方法..."+name);
	}

	public static void main(String[] args) {
//		System.out.println(name);     
		new StaticTest2(); 			 
//		say();			  		      
//		new StaticTest3().test();     
	}
	
	/**
	 *  new一个对象的时候JVM都做了那些事情:
	 *  
	 *    一、没有进行过类加载：
	 *  	1.类加载,同时初始化类中静态的属性(赋默认值)
	 *		2.执行静态代码块
	 *		3.分配内存空间,同时初始化非静态的属性(赋默认值)(new了对象，还没把地址传给引用)
	 *		4.调用父类构造器(
	 *			如果有显示赋值的话,注意调用父类构造器之前已经给父类的
	 *			非静态的属性显示赋值,)
	 *		5.父类构造器执行完后,(如果自己声明属性的同时有显示的赋值,那么进行显示赋值把默认值覆盖)
	 *		6.执行匿名代码块
	 *		7.执行构造器
	 *		8.返回内存地址
	 *
	 *    二、之前已经进行了类加载
	 *		1.分配内存空间,同时初始化非静态的属性(赋默认值)
	 *		2.如果声明属性的同时有显示的赋值,那么进行显示赋值把默认值覆盖
	 *		3.执行匿名代码块
	 *		4.执行构造器
	 *		5.返回内存地址
	 * */

}
