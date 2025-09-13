package com.dj.ch09;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TestUDPServer {
	public static void main(String args[]) throws Exception {
		//1.创建一个UDP的socket对象来表示自己 同时指定一个端口号
		DatagramSocket ds = new DatagramSocket(5678);
		
		//2.建立一个字节数组以接收UDP包
		byte buf[] = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buf, buf.length);
		boolean flag = true;
		while (flag) {
			//3.接收UDP包（数据） 这个方法会让线程阻塞
			ds.receive(dp);
			//4.数据会自动的接收到数据包dp里面封装的字节数组中buf
			ByteArrayInputStream bais = new ByteArrayInputStream(buf);
			DataInputStream dis = new DataInputStream(bais);
			//读出字节数组中的数据
			System.out.println(dis.readLong());
		}
		//5.关闭数据报套接字
		if(ds!=null)ds.close();
	}
}
