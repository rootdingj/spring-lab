package com.dj.ch04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class SetListTest {

	public static void main(String[] args) {
		Collection<Integer> hashSet = new HashSet<Integer>();
		System.out.println("hashset"+getTestTime(hashSet,100000)+"毫秒数");
		
		Collection<Integer> linkedhashSet = new LinkedHashSet();
		System.out.println("linkedhashSet"+getTestTime(linkedhashSet,100000)+"毫秒数");
		
		Collection<Integer> treeSet = new TreeSet();
		System.out.println("treeSet"+getTestTime(treeSet,100000)+"毫秒数");
		
		Collection<Integer> arraylist = new ArrayList();
		System.out.println("arraylist"+getTestTime(arraylist,100000)+"毫秒数");
		
		Collection<Integer> linkedlist = new LinkedList();
		System.out.println("linkedlist"+getTestTime(linkedlist,100000)+"毫秒数");
		
	}
		
	public static long getTestTime(Collection<Integer> c, int size){
		
		long startTime = System.currentTimeMillis();
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<size;i++){
			list.add(i);
		}
		Collections.shuffle(list);
		for(int element:list){
			c.add(element);
		}
		Collections.shuffle(list);
		for(int element:list){
			c.remove(element);
		}
		long endTime = System.currentTimeMillis();
		return endTime-startTime;
	}
	
}
