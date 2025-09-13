package com.dj.ch03;

/**
	静态内部类：</br> 
		1、静态内部类中不能直接访问外部类的非静态属性或者方法， 静态内部类的对象不需要依赖于外部类的对象；</br>
		2、静态内部类可以直接访问外部类的静态成员，如果访问外部类的实例成员，就必须通过外部类的实例去访问；</br>
		3、在静态内部类中可以定义静态成员和实例成员；</br>
		4、可以通过完整的类名直接访问静态内部类的静态成员。
* */
public class StaticOutterClass {
	private String name;
	private static int age;
	
	//静态内部类：看作静态的成员变量
	//内部类开始
	public static class StaticInner{
		private String name;
		//静态内部类中可以有静态的变量或方法
		private static int age;
		public void say(){
			//访问内部类的成员变量
			System.out.println(this.name);
			System.out.println(this.age);
			//可以访问外部类中的静态的属性或方法
			System.out.println(StaticOutterClass.age);
			System.out.println(StaticOutterClass.StaticInner.age);
			StaticOutterClass.hello();	
			//不能访问外部类中的非静态的属性(静态的总是不能随便的访问非静态的)
//			System.out.println(StaticOutterClass.this.name);
//			StaticOutterClass.say();
			new StaticOutterClass().say();
//			StaticOutterClass.this.say;
			new StaticOutterClass().hello();
			StaticOutterClass.hello();
//			StaticOutterClass.this.say();
			new StaticInner();
//			new StaticOutterClass().new StaticInner();
		}
	}
	//内部类结束
	public void say(){
		//外部类中访问静态内部类的静态属性
		System.out.println(StaticInner.age);
		//外部类中访问静态内部类的非静态属性或方法
		//需要借助于静态内部类的对象
		StaticInner st = new StaticInner();
		System.out.println(st.name);
	}
	public static void hello(){
		
	}
	public static void main(String[] args) {
		StaticInner staticInner = new StaticInner();
		staticInner.say();
		StaticInner staticInner2 = new StaticOutterClass.StaticInner();
//		StaticOutterClass.StaticInner	//编译出错
//		new StaticOutterClass().new StaticInner();
		
	}
	
}
