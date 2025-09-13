package com.dj.ch07;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*
 * Copy-On-Write简称COW，是一种用于程序设计中的优化策略。其基本思路是，从一开始大家都在共享同一个内容，当某个人想要修改这个内容的时候，
 * 才会真正把内容Copy出去形成一个新的内容然后再改，这是一种延时懒惰策略。
 * 
 * */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ArrayListOfMultithread {
	
	/**
	 * 三种遍历方式都报错：
	 * 	 	都是使用Iterator进行遍历，且都是通过ArrayList.remove(Object) 进行删除操作。
	 * 		list在遍历的时候进行删除，会出现重复删除的问题
	 * 		请分析ArrayList源码，为什么会抛出ConcurrentModificationException异常
	 * */
	public static void originalList(){
		ArrayList<String> list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		//遍历方式1
		Iterator<String> it1 = list.iterator();
		while(it1.hasNext()){
			String next = it1.next();
			if(next.equals("3")){
				list.remove(next);
			}
		}
		//遍历方式2
		//list.iterator(): 获取一个迭代器中当前位置的对象
		//it2.hasNext(): 迭代器中后面是否还有东西
		for(Iterator<String> it2 = list.iterator();it2.hasNext();){
			String next = it2.next();
			if(next.equals("3")){
				list.remove(next);
			}
		}
		//遍历方式3
		for (String next : list) {
			if (next.equals("3")) {
				list.remove(next);
			}
		} 
	}
	
	public static void singleThreadTest(){
		ArrayList<String> list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		// 1 使用Iterator提供的remove方法，用于删除当前元素  
		/*for (Iterator<String> it = list.iterator(); it.hasNext();) {
			String next = it.next();
			if (next.equals("3")) {
				it.remove();	//关键
			}
		}*/
		// 2 建一个集合，记录需要删除的元素，之后统一删除             
		/*List<String> templist = new ArrayList<String>();
		for (String value : list) {
			if (value.equals("3")) {
				templist.add(value);
			}
		} 
		// 可以查看removeAll源码，其中使用Iterator进行遍历 
		list.removeAll(templist); */
		
		// 3. 不使用Iterator进行遍历，需要注意的是自己保证索引正常
		for (int i = 0; i < list.size(); i++) {
			String value = list.get(i);
			if (value.equals("3")) {
				list.remove(value);
				i--;  // 因为位置发生改变，所以必须修改i的位置 
			}
		}
		System.out.println("next Value:" + list.toString());
		//4. 使用线程安全CopyOnWriteArrayList进行删除操作
		/*List<String> myList = new CopyOnWriteArrayList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");
		Iterator<String> it = myList.iterator();
		while (it.hasNext()) {
			String value = it.next();
			if (value.equals("3")) {
				myList.remove("4");
				myList.add("6");
				myList.add("7");
			}
		}
		System.out.println("List Value:" + myList.toString());*/
	}
	
	public static void multiThreadTest() {
		final List<String> myList = new CopyOnWriteArrayList<String>();
		myList.add("1");
		myList.add("2");
		myList.add("3");
		myList.add("4");
		myList.add("5");
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (String string : myList) {
					System.out.println("遍历集合 value = " + string);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		//实现
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < myList.size(); i++) {
					String value = myList.get(i);
					System.out.println("删除元素 value = " + value);
					if (value.equals("3")) {
						myList.remove(value);
						System.out.println("移除元素    	value = " + value);
						i--;  // 注意  
					}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public static void main(String[] args) {
//		originalList();
//		singleThreadTest();
		multiThreadTest();
	}

}