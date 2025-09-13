package com.dj.ch04;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest {
	public static void main(String[] args) {
		Comparator t = new Comparator() {

			@Override
			public int compare(Object o1, Object o2) {
				Student s1 = (Student) o1;
				Student s2 = (Student) o2;
				return 0;
			}
		};
		Collection c = new TreeSet(t);
		c.add("dj1");
		c.add("dj2");
		c.add("dj3");
		Student stu1 = new Student(11L, "dj1");
		Student stu2 = new Student(12L, "dj2");
		Student stu3 = new Student(13L, "dj3");
		Student stu4 = new Student(14L, "dj4");
		Student stu5 = new Student(15L, "dj5");
		c.add(stu1);
		c.add(stu2);
		c.add(stu3);
		c.add(stu4);
		c.add(stu5);
		for (Object o : c) {
			System.out.println(o);
		}
//		System.out.println(c.contains(""));
//		System.out.println(c.size());
//		System.out.println(c.remove("dj1"));
		Object[] o1 = c.toArray();
		System.out.println(Arrays.toString(o1));
		String[] str = (String[]) c.toArray(new String[c.size()]);
		System.out.println(Arrays.toString(str));

	}
}

class Student {
	private long id;
	private String name;

	public Student(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}