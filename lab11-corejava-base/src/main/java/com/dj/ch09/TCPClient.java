package com.dj.ch09;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	public static void main(String[] args) {
		String ip = "127.0.0.1";
		int port = 9999;
		Socket socket = null;
		try {
			/*
			        创建一个网络连接
			        连接的IP是127.0.0.1
			         如果这个socket对象能创建出来则说明客户端和服务器已经连接
			 * */
			socket = new Socket(ip, port);
			System.out.println("客户端已经成功的连接到了服务器");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(socket!=null)socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
