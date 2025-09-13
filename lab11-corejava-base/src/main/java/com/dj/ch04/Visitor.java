package com.dj.ch04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Visitor {
	public static void print(Collection c){
		Iterator it = c.iterator();
		while(it.hasNext()){
			Object element = it.next();
			System.out.println(element);
		}
		System.out.println(".........................");
		
	}
	public static void main(String[] args) {
		Set set = new HashSet();
		set.add("dj");
		set.add("dj1");
		set.add("dj2");
		set.add("dj2");
		set.add("dj2");
		print(set);
		System.out.println("...................");
		
		List list = new ArrayList();
		list.add("hello1");
		list.add("hello2");
		list.add("hello2");
		list.add("hello3");
		print(list);
		System.out.println("...................");
		
		Map map = new HashMap();
		map.put("dj", "123");
		map.put("abc", "321");
		map.put("dj1", "123");  
		print(map.entrySet());
	}
}
