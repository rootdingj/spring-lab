package com.dj.ch04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ArrayListTest {

	public static void main(String[] args) {
		// 数组有序集
		Collection c = new ArrayList();
		// 链表有序集
//		Collection c = new LinkedList(); 
		Collection vector = new Vector();
		c.add("dj1");
		c.add("dj2");
		c.add("dj3");
		c.add("dj4");
		c.add("dj1");
		c.add("dj3");
		System.out.println(c);

		ArrayList list = null;
		if (c instanceof ArrayList) {
			list = (ArrayList) c;
		}
		System.out.println(list.get(0));
		System.out.println(".............");
		System.out.println(c);
		System.out.println(c.contains("dj1"));
		System.out.println(c.isEmpty());
		System.out.println(c.remove("dj3"));
		System.out.println(c.remove("dj1"));
		System.out.println(c.size());
		Object[] o = c.toArray();
		// 返回一个表示集合的数组
		System.out.println(Arrays.toString(o));
		String[] str = (String[]) c.toArray(new String[c.size()]);
		System.out.println(Arrays.toString(str));

		System.out.println(".......集合遍历测试...........");
		// 调用一个迭代器，把集合中的元素一个个遍历出来
		Iterator it = c.iterator();
		while (it.hasNext()) {
			Object o1 = it.next();
			System.out.println(o1);
			it.remove();
		}
		System.out.println(c);
		System.out.println("...........................");
		// 加强for循环，性能很好，但是不可以改变元素的个数（报错,原数据修改异常）
		for (Object o2 : c) {
			System.out.println(o2);
		}
		System.out.println("...........................");
		// 普通for循环,可以嵌套if语句
		for (int i = 0; i < c.size(); i++) {
			System.out.println(((ArrayList) c).get(i));
			if (i == 5)
				c.add("dj6");
		}
		System.out.println("............................");
		List<String> list1 = Arrays.asList("yellow", "red", "green", "blue");
		List<String> list2 = Arrays.asList("yellow", "red", "green", "blue");
//		Collections.sort(list, Collections.reverseOrder());
//		Collections.reverse(list);//逆序排列
//		Collections.shuffle(list);//随机打乱指定的列表
		Collections.reverseOrder();
//		Collections.shuffle(list, new Random(20));
//		Collections.shuffle(list2, new Random(20));
		System.out.println(list);
//		System.out.println(list2);
//		System.out.println();

//		List<Integer> list1 = Arrays.asList(11,21,31,41,51,61,71);
//		System.out.println(Collections.binarySearch(list1, 41));

	}
}
