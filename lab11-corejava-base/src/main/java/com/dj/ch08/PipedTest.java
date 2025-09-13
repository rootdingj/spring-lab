package com.dj.ch08;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedTest {
	
	public static void main(String[] args) {
		try {
			PipedInputStream in = new PipedInputStream();
			PipedOutputStream out = new PipedOutputStream(in);  //连接两个管道的接口(in),只需在两个管道其中的任意一个连接即可
		
			//发送数据线程
			Thread t1 = new MyThread1(out);
			//接收数据线程
			Thread t2 = new MyThread2(in);
			
			t1.start();
			t2.start();
		} catch (Exception e) {
		}
	}
}

//负责发送，向管道中发送数据
class MyThread1 extends Thread{
	private PipedOutputStream out;
	public MyThread1(PipedOutputStream out) {
		this.out = out;
	}
	public void run() {
		try {
			String str = "hello world";
			byte[] bytes = str.getBytes();
			for (byte b : bytes) {
				out.write(b);
				out.flush();
				Thread.sleep(1000);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if (out != null)out.close();
			} catch (Exception e2) {
			}
		}
	}
}

//负责接收，从管道中接收数据
class MyThread2 extends Thread{
	private PipedInputStream in;
	public MyThread2(PipedInputStream in) {
		this.in = in;
	}
	public void run() {
		OutputStream out = System.out;
		try {
			int len = -1;
			while((len=in.read())!=-1){
				out.write(len);
				out.flush();
			}
			System.out.println();
			System.out.println("len="+len);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (in != null)in.close();
			} catch (Exception e2) {
			}
		}
	}
}