package com.dj.ch04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class MapTest {

	private static final String Entry = null;

	public static void main(String[] args) {
		HashMap<Integer,String> hashMap = new HashMap();
		hashMap.put(1, "a");
		hashMap.put(2, "b");
		hashMap.put(3, "c");
		hashMap.put(3, "a");
		hashMap.put(5, "d");
//		System.out.println(hashMap.containsKey("1"));
//		System.out.println(hashMap.get(1));
//		System.out.println(hashMap);
//		System.out.println(hashMap.hashCode());
//		System.out.println(hashMap.remove(3));
//		System.out.println(hashMap);
		
		Set set = hashMap.entrySet();
		Iterator it = set.iterator();
		System.out.println("Iterator---------------------");
		while(it.hasNext()){
			Entry entry = (Entry) it.next();
			System.out.println("entry.key="+entry.getKey()+"  entry.value="+entry.getValue());
		}
		System.out.println("entrySet---------------------");
		for(Object o:set){
			System.out.println(o);
		}
		System.out.println("keySet---------------------");
		Set keySet = hashMap.keySet();
		for(Object o:keySet){
			System.out.println(o);
		}
		System.out.println("values---------------------");
		Iterable values = hashMap.values();
		for(Object o:values){
			System.out.println(o);
		}
		System.out.println("TreeMap---------------------");
		TreeMap<String,String> treeMap = new TreeMap();
		treeMap.put("1", "Monday");
		treeMap.put("2", "Tuesday");
		treeMap.put("3", "Wednsday");
		treeMap.put("4", "Thursday");
		Set<String> keySet1 = treeMap.keySet();
		Iterator<String> keyIt = keySet1.iterator();
		while(keyIt.hasNext()){
			String key = keyIt.next();
			System.out.println(key);
			System.out.println(treeMap.get(key));
		}
		
	}
}
