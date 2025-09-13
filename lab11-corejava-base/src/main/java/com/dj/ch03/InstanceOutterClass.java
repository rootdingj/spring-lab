package com.dj.ch03;

/**
	成员内部类：</br>
		 1、在创建实例内部类的实例时，外部类的实例必须已经存在；</br>
		 2、实例内部类的实例自动持有外部类的实例的引用。在内部类中， 可以直接访问外部类的所有成员，包括成员变量和成员方法；</br>
		 3、外部类实例与内部类实例之间是一对多的关系，一个内部类实例只会引用一个外部类实例，而一个外部类实例对应零个或多个内部类实例；
		         在外部类中不能直接访问内部类的成员，必须通过内部类的实例去访问。</br>
		 4、实例内部类中不能定义静态成员，而只能定义实例成员；</br>
		 5、如果实例内部类B与外部类A包含同名的成员，那么在类B中， this.v表示类B的成员，A.this.v表示类A的成员。            
* */
public class InstanceOutterClass {
	private String name = "dj";
	private static int age;
	
	//成员内部类：把类当作一个成员变量来看
	//内部类开始
	public class InstanceInner{
		private String name = "dj2";
		//成员内部类不能有静态的属性或方法
		//private static int age1;
		public void say(String name){
			//访问这个方法的参数
			System.out.println("name1="+name);
			//访问内部类的成员变量
			System.out.println("name2="+this.name);
			//访问外部类的普通成员变量(***)
			System.out.println("name3="+InstanceOutterClass.this.name);
			//访问外部类的静态变量
			System.out.println("name4="+InstanceOutterClass.age);
			//访问外部类的静态方法
			InstanceOutterClass.hello();
			//访问外部内的普通方法
			InstanceOutterClass.this.say("nihao");
			InstanceOutterClass.this.new InstanceInner();
		}
	}
	//内部类结束
	public void say(String name){
		System.out.println(".....");
		this.new InstanceInner();
		init();
		hello();
	}
	public static void hello(){
		System.out.println("123");
//		say();	//编译不通过
//		init(); //编译不通过
		new InstanceOutterClass().say("12345");
	}
	
	//(***)
	public void init(){
		/*创建内部类对象的方式二：
		 *这么写需要在当前外部类之中的非静态方法中才可以
		 * */
		InstanceInner i = this.new InstanceInner();
		InstanceInner in = new InstanceInner();
		//在外部类中访问内部类的属性或者方法，需要借助内部类的对象
		//外部类中访问内部类的方法
		i.say("dj");
		//外部类中访问内部类的属性
		System.out.println(i.name);
	}
	public static void main(String[] args) {
		//创建内部类对象的方式一：（***静态方法中）
		InstanceOutterClass.InstanceInner ii = new InstanceOutterClass().new InstanceInner();
		InstanceInner ii2 = new InstanceOutterClass().new InstanceInner();
		//创建内部类对象方式三：(先创建外部类对象，再用外部类调用)
		InstanceOutterClass ioc = new InstanceOutterClass();
		InstanceInner ii3 = ioc.new InstanceInner();
		ii3.say("dj");
	}
	
}
