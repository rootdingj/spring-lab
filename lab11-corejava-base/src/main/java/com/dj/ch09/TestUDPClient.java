package com.dj.ch09;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TestUDPClient {
	public static void main(String args[]) throws Exception {
		long n = 123L;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeLong(n);
		//1.创建一个UDP的socket对象来表示自己 同时指定一个端口号
		DatagramSocket ds = new DatagramSocket(9999);
		
		//2.把要发送的字节数组中的数据封装到数据包中(DatagramPacket),同时指明发送的目标的ip和端口号
		//准备好一个要发送的字节数组
		byte[] buf = baos.toByteArray();
		DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress("127.0.0.1", 5678));
		
		//3.把这个数据包从自己绑定的端口号出发出,发出到目标地址
		ds.send(dp);
		
		//4.关闭数据报套接字
		ds.close();
	}
}
