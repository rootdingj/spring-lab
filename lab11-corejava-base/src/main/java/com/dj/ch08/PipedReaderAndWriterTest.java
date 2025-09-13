package com.dj.ch08;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class PipedReaderAndWriterTest {
	
	public static void main(String[] args) {
		PipedWriter out = null;
		PipedReader in = null;
		try {
			out = new PipedWriter();
			in = new PipedReader(out);
			//负责写字符的线程
			Thread t1 = new MyThreadA(out);
			//负责读字符的线程
			Thread t2 = new MyThreadB(in);
			t1.start();
			t2.start();
			//谁调用谁等待
			//main线程调用的这个jion方法所以是main线程等待
			t1.join();
			t2.join();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			try {
				if(out!=null)out.close();
				if(in!=null)in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
class MyThreadA extends Thread{
	private PipedWriter out;
	public MyThreadA(PipedWriter out) {
		this.out = out;
	}
	public void run() {
		char[] c = "hello".toCharArray();
		try {
			Thread.sleep(1000);
			out.write(c, 0, c.length);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class MyThreadB extends Thread{
	private PipedReader in;
	public MyThreadB(PipedReader in) {
		this.in = in;
	}
	public void run() {
		char[] c = new char[50];
		try {
			Thread.sleep(3000);
			int len = in.read(c);
			System.out.println(new String(c,0,len));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
