package com.dj.ch02;
/**
final修改符：
		final具有"不可改变的"含义，它可以修饰非抽象类、非抽象成员方法和变量:
		 . 用final修饰的类不能被继承，没有子类；
     	.用final修饰的方法不能被子类的方法覆盖；
     	.用final修饰的变量表示常量，只能被赋一次值；
     
1. final类:
 	. 子类有可能会错误地修改父类的实现细节；
	  . 出于安全，类的实现细节不允许有任何改动；
	  . 在创建对象模型时，确信这个类不会再被扩展；
	  
2. final方法；
  	某些情况下，出于安全原因，父类不允许子类覆盖某个方法， 此时可以把这个方法声明为final类型

3. final变量：
		final修饰的属性(成员变量)赋值的位置:
			非静态的成员变量
			1.声明的同时
			2.匿名代码块
			3.构造器(类中出现的所有构造器)

		静态的成员变量
			1.声明的同时
			2.static代码块		    
			  a. final可以修饰静态变量、实例变量、局部变量；
			  b. final变量都必须显示初始化，否则会导致编译错误；因为他是final所以JVM不会给他默认值（根据先定义、再初始化、后使用的原则）。
				 1) 静态变量，定义变量时进行初始化或者static代码块中赋值；
				 2) 实例变量，可以在定义变量时，或者在构造方法中进行初始化；
			  c. final变量只能赋一次值。
* */
public final class FinalTest {
	public String name;
	public static int age;
	public final String SEX_NV; // 必须初始化，常量用大写字母
	public final String SEX_NAN = "男";
	
	{
		SEX_NV = "女";
//		SEX_NAN = "男";
	}
	static {
//		SEX_NV = "女";  // 
//		SEX_NAN = "男";
	}
	
	public void testMethod() {
		
	}
	public final void testFinalMethod() {
		
	}

}
