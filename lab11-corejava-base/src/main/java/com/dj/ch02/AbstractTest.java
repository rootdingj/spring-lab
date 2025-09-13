package com.dj.ch02;
/**
abstract 修饰符:
	 abstract 可用来修饰类和成员方法：
		.用abstract修饰的类是抽象类，抽象类不能实例化，即不允许创建抽象类本身的实例。没有用abstract修饰的类称为具体类，具体类可被实例化。
		.用abstract修饰的方法是抽象方法，抽象方法没有方法体。抽象方法用来描述系统具有什么功能 ，但不提供具体的实现。没有abstract修饰的方法称为具体方法，具体方法具有方法体。
语法规则；
1) 抽象类中可以没有抽象方法，但包含了抽象方法的类必须被定义为抽象类；
2) 没有抽象构造方法，也没有抽象静态方法；
3) 抽象类中可以有非抽象的构造方法；
4) 抽象类及抽象方法不能被final修饰符修饰。
5)继承这个抽象类在创建对象，就可以调用方法了，继承过来要实现他的方法{}；
6)有抽象方法的类一定是抽象类，抽象类不一定有抽象方法
 * */
public abstract class AbstractTest {
	
	private String name;
	public static int age;
	
	public void testMethod() {}

	public abstract void testAbstractMethod(); 

	public static void main(String[] args) {

	}
}

class ClassTest extends AbstractTest{

	@Override
	public void testAbstractMethod() {
		// TODO Auto-generated method stub
		
	}
}