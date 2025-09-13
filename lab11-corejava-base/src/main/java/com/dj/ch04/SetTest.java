package com.dj.ch04;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest {
	public static void main(String[] args) {
		Collection c = new HashSet();
		c.add("dd1");
		c.add("dd2");
		c.add("dd1");
		c.add("dd3");
		c.add("dd5");
		System.out.println(c);
		System.out.println(c.contains("dd1"));
		System.out.println(c.isEmpty());
		System.out.println(c.remove("dd1"));
		System.out.println(c.size());
		
		Object[] o = c.toArray();
		System.out.println(Arrays.toString(o));
		
		String[] str = (String[]) c.toArray(new String[(c.size())]); 
		System.out.println(Arrays.toString(str));
		
		Iterator it = c.iterator();
		while(it.hasNext()){
			Object ob = it.next();
			System.out.println(ob);
		}
		for(Object o1:c){
			System.out.println(o1);
		}
	}
}
