package com.dj.ch02;
import static java.lang.Math.PI;

/**
 * 
 * @ClassName: StaticTest1 
 * @Description: static关键字
 * @author Steven 
 * @date 2016年6月7日
 */
public class StaticTest1 {
	// 成员变量
	private String var;
	// 静态变量
	public static int staticVar;

	/**
	 * 1、成员变量和静态变量的区别： a.成员变量，类中的每个实例都有一份，他们之间互不影响；静态变量，每个类在内存中只有一份，能被类的所有实例共享。
	 * 
	 * b.JVM 在加载完类后，创建对象时分为实例变量分配内存；JVM 在加载类的过程中为静态变量分配内存。
	 * 
	 * c.成员变量通过对象的引用访问，静态变量直接通过类名来访问。
	 */
	//构造方法
	public StaticTest1() {
		
		//属性初始化、创建对象
	}
	public StaticTest1(String var) {
		this.var = var;
	}
	

	// 成员方法
	public void testMethod() {

		System.out.println("成员方法。。。");
	}
	// 静态方法
	public static void testStaticMethod() {

		System.out.println("静态方法。。。");
	}
	/**
	 * 2、成员方法和静态方法 
	 * 		a.静态方法不可以直接访问所属类的实例变量和实例方法，可以直接访问所属类的静态变量和静态方法：
	 * 			 .不能使用this关键字;
	 *			 .不能使用super关键字; 
	 *			.静态方法必须被实现。静态方法用来表示某个类所特有的功能 
	 *		b.父类的静态方法不能被子类覆为非静态方法。
	 * 		c.父类的非静态方法不能被子类覆盖为静态方法
	 */
	
	// 匿名代码块
	{
		System.out.println("匿名代码块。。。");
	}
	//静态代码块
	static{
		System.out.println("静态代码块。。。");
	}
	/**
	 * 3.匿名代码块与静态代码块
	 *   .匿名代码块: 创建对象的时候，创建一次对象调用一次;
	 * 	 .静态代码块： 类加载时立马直接执行的代码块；一个类只会进行一次加载，静态代码块也只会执行一次,
	 *   .构造方法： 创建对象的时候，创建一次对象调用一次；一般不在构造器中初始化静态变量。
	 * */

	public static void main(String[] args) {
//		this.name;
//		this.testMethod();
//		this.testStaticMethod();
		testStaticMethod();
		System.out.println(PI);	
		/**
		 * 4.静态导入：
		 * 	   要使用静态成员（方法和变量）就必须提供这个静态成员的类。
		 *    使用静态导入可以使被导入类的静态变量和静态方法在当前类中可以直接使用
		 * 
		 * */

	}

}
