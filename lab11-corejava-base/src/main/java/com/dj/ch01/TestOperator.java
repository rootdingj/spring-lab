package com.dj.ch01;

public class TestOperator {

	public static void main(String[] args) {
		short s1 = 1;
//		s1 = s1 + 1;  // 1 是 int 类型，不能隐式地将 int 类型下转型为 short 类型
		s1 += 1;	  // 隐式类型转换，相当于 s1 = (short) (s1 + 1);
		s1++;

//		 float f = 1.1;	// 编译不通过，浮点型默认为 double 
		float f1 = 1.1f;
		float f2 = (float) 1.1;
		int i = (int) f1;
		System.out.println(i); // 1

		short s = 99;
		char c = (char) s;
		System.out.println(c); // c
		byte b = (byte) c;
		System.out.println(b); // 99
		int n = (int) c;
		System.out.println(n); // 99
	}

}
