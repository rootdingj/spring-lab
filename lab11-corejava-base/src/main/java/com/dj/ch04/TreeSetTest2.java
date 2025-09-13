package com.dj.ch04;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TreeSetTest2 {

	public static void setSortTest1(){
		Collection t = new TreeSet(new MyComparator());
		t.add("HashSet");
		t.add("ArrayList");
		t.add("TreeMap");
		t.add("HashMap");
		t.add("TreeSet");
		t.add("LinkedList");
		Iterator it = t.iterator();
		while(it.hasNext()){
			Object o = it.next();
			System.out.println(o);
		}
	}
	
	public static void setSortTest2(){
		TreeSet t2 = new TreeSet(new MyComparator2());
		for(int i=1;i<=10;i++){
			Integer i1 = new Integer(i);
			t2.add(i1);
		}
		Iterator it = t2.iterator();
		it = t2.iterator();
		while(it.hasNext()){
			Object o1 = it.next();
			System.out.println(o1);
		}
	}
	
	public static void setSortTest3(){
		TreeSet t3 = new TreeSet(new MyComparator2());
		t3.add(5);
		t3.add(3);
		t3.add(6);
		t3.add(1);
		t3.add(9);
		t3.add(10);
		t3.add(8);
		t3.add(4);
		t3.add(7);
		t3.add(2);
		System.out.println("TreeSet.toString() = "+t3.toString());;
		Iterator it = t3.iterator();
		while(it.hasNext()){
			Object o1 = it.next();
			System.out.println(o1);
		}
	}
	
	public static void setSortTest4(){
		Set<Integer> s = new TreeSet<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				/* 奇偶顺序，奇数先排列
				 * 数值大小和排序方式是两种概念，谁排在前面由自己定义，而在程序物理排序中，
				 * 都是逻辑小的排在后面，逻辑大的排前面
				 */
				//o1为奇数，o2为偶数
				if(o1%2==1&&o2%2==0){
					return -1;			//o1-o2<0,o1(奇数)小排在前面
				}
				//o1为偶数，o2为奇数
				else if(o1%2==0&&o2%2==1){
					return 1;		   //o1-o2>0,o1(偶数)大排在后面
				}
				else if(o1%2==0&&o2%2==0){
					return o1-o2;
				}
				else{
					return -(o1-o2);
				}
			}
		});
		s.add(10);
		s.add(8);
		s.add(2);
		s.add(1);
		s.add(7);
		s.add(6);
		s.add(3);
		s.add(5);
		s.add(4);
		s.add(9);
		for(Object o:s){
			System.out.println(o);
		}
	}
	
	public static void main(String[] args) {
//		setSortTest1();
//		setSortTest2();
//		setSortTest3();
		setSortTest4();
	}
}

class MyComparator implements Comparator{
	@Override
	public int compare(Object o1, Object o2) {
		String s1 = (String)o1;
		String s2 = (String)o2;
		return s1.compareTo(s2);  //升序
	//	return -s1.compareTo(s2); //降序
	}
}

/**
 * 自定义比较器</br> 
 * 当两种方法都存在时，以比较器为主。 
 * 定义一个类，实现Comparator接口，覆盖compare方法
 * */
class MyComparator2 implements Comparator{
	
	@Override
	public int compare(Object o1, Object o2) {
//		int value1 = ((Integer)o1).intValue();
		int num1 = Integer.parseInt(o1.toString());
		int num2 = Integer.parseInt(o2.toString());
		int type1 = num1%2;
		int type2 = num2%2;
		//奇数的余数大于偶数的余数，奇数在前偶数在后
		if(type1>type2)
			return -1;
		else if(type1<type2)
			return 1;
		else
			//偶数降序排列
			if(type1==0){ 
				if(num1>num2)
					return 1;
				else if(num1<num2)
					return -1;
				//奇数升序排列
			}else if(type1==1){ 
				if(num1>num2)
					return -1;
				else if(num1<num2)
					return 1;
			}
	    return 0;  
	}
}
