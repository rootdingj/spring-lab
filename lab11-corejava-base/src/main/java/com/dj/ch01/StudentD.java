package com.dj.ch01;

public class StudentD implements Cloneable {
	// 姓名
	private String name;
	// 年龄
	private int age;
	// 分数
	private ScoreD score;

	public StudentD(String name, int age, ScoreD score) {
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

	public ScoreD getScore() {
		return score;
	}

	public void setScore(ScoreD score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "StudentD [name=" + name + ", age=" + age + ",mathematics score=" + score.getMathematics()
				+ ",chinese score=" + score.getChinese() + "]";
	}

	// 重写Object类的clone方法
	public StudentD clone() throws CloneNotSupportedException {
		// 拷贝StudentD 对象
		StudentD stu = (StudentD) super.clone();
		// 拷贝StudentD 对象 的score属性
		stu.score = stu.getScore().clone();
		return stu;
	}

}

class ScoreD implements Cloneable {
	private int mathematics;
	private int chinese;

	public ScoreD(int mathematics, int chinese) {
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

	// 重写Object类的clone方法
	public ScoreD clone() throws CloneNotSupportedException {
		return (ScoreD) super.clone();
	}
}
