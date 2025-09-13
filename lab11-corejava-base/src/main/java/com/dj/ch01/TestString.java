package com.dj.ch01;

public class TestString {

	public static void testString() {
		// 若常量池中不存在，则在运行时常量池中创建新的对象"123"，然后在栈中创建对象的引用str1
		String str1 = "123";
		String str2 = new String("123");
		String str3 = new String("123");
		String str4 = "123";
		System.out.println(str1 == str2); // false
		System.out.println(str2 == str3); // false
		System.out.println(str2.equals(str3)); // true
		// 由于运行时常量池中已经存在该对象，直接在栈中创建对象的引用str4即可。
		System.out.println(str1 == str4); // true
		System.out.println("-----------------------");
		String a = "a";
		String b = "b";
		String ab = "ab";
		System.out.println(ab == "a" + "b"); // true
		System.out.println(ab == a + b); // false
		// 字面量"+"拼接是在编译期间进行的，拼接后的字符串存放在字符串池中；
		// 而字符串引用的"+"拼接运算实在运行时进行的，新创建的字符串存放在堆中。
		System.out.println("-----------------------");
		// 使用 intern() 方法在运行过程中将字符串添加到 String Pool 中
		String s1 = new String("intern test");
		String s2 = new String("intern test");
		System.out.println(s1 == s2); // false
		String s3 = s1.intern();
		String s4 = s1.intern();
		System.out.println(s3 == s4); // true

	}

	public static void testStringBuilder() {

	}

	public static void main(String[] args) {
		testString();
	}

}
