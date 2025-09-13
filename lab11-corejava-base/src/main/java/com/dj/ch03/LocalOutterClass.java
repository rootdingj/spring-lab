package com.dj.ch03;

/**
	局部内部类：</br>
		1、局部内部类只能在当前方法中使用；</br>
		2、局部内部类和实例内部类一样，不能包含静态成员；</br>
		3、在局部内部类中定义的内部类也不能被public、protected和private这些访问控制修饰符修饰；</br>
		4、局部内部类和实例内部类一样，可以访问外部类的所有成员，此外，局部内部类还可以访问所在方法中的final类型的参数和变量。
* */
public class LocalOutterClass {
	private String name;
	private static int age;

	public void say(){
		//(局部变量,放在栈区中,用一次会释放一次)方法要处理的，不能还没处理就被改掉了，所以要把他写死了用final修饰，放到常量池中去
		final int num = 10;    
//		public int num;
		name = "dj";
		//局部内部类：把这个类看作一个局部变量
		class LocalInner{
			private int age;
			public void hello(){
				//访问外部类中的成员变量
				System.out.println(LocalOutterClass.this.name);
				//访问外部类中的静态成员变量
				System.out.println(LocalOutterClass.age);
				//访问这个局部内部所在方法中的自己的成员变量(常量)
				System.out.println(num);
				//访问这个局部内部类中的局部变量
				System.out.println(this.age);
				//访问外部类中的方法
				LocalOutterClass.this.test();
				//访问外部类中的静态方法
				LocalOutterClass.go();
			}
		}
		//
		//创建局部内部类的对象
		LocalInner l = new LocalInner();
		//调用局部内部类中方法
		l.hello();
	}
	public void test(){
		System.out.println("普通方法.......");
	}
	public static void go(){
		System.out.println("静态方法.......");
		
	}
	
	public static void main(String[] args) {
		new LocalOutterClass().say();
		
	}
}
