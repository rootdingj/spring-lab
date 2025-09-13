package com.dj.ch03;

/**
 * 匿名内部类:</br>
 * 1、写在成员变量中的匿名内部类 ，接口的实现</br>
 * 2、写在方法中的匿名内部类，这样的内部类只能在这个方法中使用
 */
public class AnonymousOutterClass {
	private String name;
	private static int age;
	/**
	 * 写在成员变量中的匿名内部类（接口的实现，没有名字） 理论支持：当使用接口来声明引用类型变量时，这个引用变量必须引用到实现类的对象。
	 *
	 * 在整个类中都可以使用 SomeThing是定义在这个java文件中的接口
	 */
	private SomeThing s = new SomeThing() {

		@Override
		public void use() {
			System.out.println("写在成员变量中的匿名内部类...");
		}
	};

	public void say() {

		int num = 10;
		final int num2 = 10;
		// 局部变量中的匿名内部类，这样的内部类只能在这个方法中使用
		SomeThing s2 = new SomeThing() {

			@Override
			public void use() {
				System.out.println(AnonymousOutterClass.this.name);
				System.out.println(AnonymousOutterClass.age);
				System.out.println(num2);
			}

			public void say() {

			}
		};
		s2.use();
	}

	public static void main(String[] args) {
		new AnonymousOutterClass().s.use();

	}
}

interface SomeThing {
	void use();
}