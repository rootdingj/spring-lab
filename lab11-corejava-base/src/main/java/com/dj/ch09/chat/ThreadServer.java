package com.dj.ch09.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextArea;

/**
 * 线程服务端
 */
public class ThreadServer implements Runnable {
	/** 服务端输出流 */
	public static ArrayList<PrintWriter> serverOutputStream = null;
	/** 服务端端口 */
	int port;
	/** 服务端会话窗口 */
	JTextArea serArea;

	public ThreadServer() {
	}

	public ThreadServer(int port, JTextArea serArea) {
		this.port = port;
		this.serArea = serArea;
		serverOutputStream = new ArrayList<PrintWriter>();
	}

	public void run() {
		ServerSocket server = null;
		Socket socket = null;
		try {
			// 1.创建一个ServerSocket对象表示服务器，同时绑定一个端口
			server = new ServerSocket(port);
			while (true) {
				serArea.append("等待客户端连接...\n");
				// 2.监听连接请求
				socket = server.accept();
				serArea.append("客户端已连接...\n");
				// 3.获取客户端输出流，开始网络数据的发送和接收
				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				serverOutputStream.add(pw);
				Thread client = new Thread(new ThreadClient(socket, serverOutputStream, serArea));
				client.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** 发送消息给客户端 */
	public void sendMesToClient(String mes) {
		Iterator<PrintWriter> it = serverOutputStream.iterator();
		while (it.hasNext()) {
			PrintWriter pw = it.next();
			pw.println(mes);

			pw.flush();
		}
	}

}
