package com.dj.ch01;

public class Student implements Cloneable {
	// 姓名
	private String name;
	// 年龄
	private int age;
	// 分数
	private Score score;

	public Student(String name, int age, Score score) {
		super();
		this.name = name;
		this.age = age;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ",mathematics score=" + score.getMathematics()
				+ ",chinese score=" + score.getChinese() + "]";
	}

	// 重写Object类的clone方法
	public Student clone() throws CloneNotSupportedException {
		return (Student) super.clone();
	}

}

class Score {
	private int mathematics;
	private int chinese;

	public Score(int mathematics, int chinese) {
		super();
		this.mathematics = mathematics;
		this.chinese = chinese;
	}

	public int getMathematics() {
		return mathematics;
	}

	public void setMathematics(int mathematics) {
		this.mathematics = mathematics;
	}

	public int getChinese() {
		return chinese;
	}

	public void setChinese(int chinese) {
		this.chinese = chinese;
	}

}
