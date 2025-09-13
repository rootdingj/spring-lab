package com.dj.ch04;

import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;

public class VectorTest {
	public static void main(String[] args) {
		Collection<Integer> c = new Vector<Integer>();
		Queue<String> q = new LinkedList();
		PriorityQueue<String> qq = new PriorityQueue();
		q.offer("dj1");
		q.offer("dj2");
		q.offer("dj3");
		System.out.println(q);
		for(String s:q){
			System.out.println(s);
		}
		while(q.size()>0){
			System.out.print(q.remove()+"\t");
		}
		System.out.println(q);
	   qq.offer("dd1");
	   qq.offer("dd2");
	   qq.offer("dd3");
	   qq.offer("dd4");
		while(qq.size()>0){
			System.out.print(qq.remove()+"\t");
		}
	}
}
