package com.dj.ch06;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型： .泛型可以使类中的属性类型可以由外部决定; .不需要强制类型转换; .泛型可以在*编译期间*进行类型检查; .提供了类型安全的操作
 * 
 */
public class GenericTest {
	
	public static void main(String[] args) {
		Generic<Integer> integer = new Generic<Integer>(1000);
		System.out.println(integer.toString());
		Generic<String> str = new Generic<String>("Hello Generic");
		System.out.println(str.toString());
		System.out.println("-----------------------");
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		for (Integer n : list) {
			System.out.println(n);
		}
	}
}
