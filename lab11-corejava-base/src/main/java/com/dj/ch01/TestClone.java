package com.dj.ch01;

public class TestClone implements Cloneable {

	public static void testShallowClone() throws CloneNotSupportedException {
		Score score = new Score(99, 90);
		Student orgin = new Student("二牛", 22, score);
		Student clone = orgin.clone();
		System.out.println("orgin：" + orgin.toString());
		System.out.println("clone：" + clone.toString());
		orgin.setName("激动");
		orgin.setAge(1);
		score.setMathematics(100);
		score.setChinese(80);
		System.out.println("orgin：" + orgin.toString());
		System.out.println("clone：" + clone.toString());
		// 基本数据类型、String为深拷贝
		// 对象数组为浅拷贝

	}

	public static void testDeepClone() throws CloneNotSupportedException {
		ScoreD score = new ScoreD(99, 90);
		StudentD orgin = new StudentD("二牛", 22, score);
		StudentD clone = orgin.clone();
		System.out.println("orgin：" + orgin.toString());
		System.out.println("clone：" + clone.toString());
		orgin.setName("激动");
		orgin.setAge(1);
		score.setMathematics(100);
		score.setChinese(80);
		System.out.println("orgin：" + orgin.toString());
		System.out.println("clone：" + clone.toString());

	}

	public static void main(String[] args) throws CloneNotSupportedException {
		testShallowClone();
		System.out.println("-------------------------------------");
		testDeepClone();
	}

}
