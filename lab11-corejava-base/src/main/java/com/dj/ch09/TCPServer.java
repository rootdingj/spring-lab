package com.dj.ch09;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


//先运行服务器，在运行客户端
public class TCPServer {

	public static void main(String[] args) {
		//端口在1000~65000之间取
		int port = 9999;
		ServerSocket server = null;
		Socket socket = null;
		try {
			//1.创建一个ServerSocket对象表示服务器，同时绑定一个端口
			server = new ServerSocket(port);
			
			/*
			    2.监听连接请求，如果客户端请求连接，则接受连接，返回通信套接字(socket对象)；
			               通过这个socket对象可以知道客户端程序所在的电脑IP和所在内存的端口号（知道这个连接的客户端是谁） 
			 * */
			System.out.println("服务器端等待客户端的连接...");
			socket = server.accept();
			System.out.println("客户端程序所在的电脑IP为："+socket.getInetAddress().getHostAddress());
			System.out.println("客户端程序所在内存的端口号："+socket.getPort());
			System.out.println("服务器端连接到了客户端...");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				//3.关闭通信套接字
				if(socket!=null)socket.close();
				if(server!=null)server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
