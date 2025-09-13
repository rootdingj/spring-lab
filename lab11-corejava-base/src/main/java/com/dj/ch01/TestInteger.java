package com.dj.ch01;

public class TestInteger {

	public static void main(String[] args) {
		/**
		 * new Integer(123) 与 Integer.valueOf(123) 的区别在于： 
		 * 1.new Integer(123) 每次都会新建一个对象；
		 * 2.Integer.valueOf(123) 会使用缓存池中的对象，多次调用会取得同一个对象的引用。
		 */
		Integer a = new Integer(123);
		Integer b = new Integer(123);
		System.out.println(a == b); // false
		Integer x = Integer.valueOf(123);
		Integer y = Integer.valueOf(123);
		System.out.println(x == y); // true
		// 编译器会在自动装箱过程调用 valueOf() 方法，因此多个值相同且值在缓存池范围内的 Integer 实例使用自动装箱来创建，那么就会引用相同的对象。
		Integer n = 123;
		Integer m = 123;
		System.out.println(m == n); // true
	}

}
