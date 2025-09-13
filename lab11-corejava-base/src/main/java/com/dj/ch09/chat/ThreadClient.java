package com.dj.ch09.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextArea;

/**多线程客户端*/
public class ThreadClient implements Runnable{
	/**客户端输入流*/
	BufferedReader br;
	/**客户端输出流*/
	ArrayList<PrintWriter> clientOutputStream; 
	/**客户端会话窗口*/
	JTextArea cliArea;
	Socket socket;
//	JTextArea serArea;

	public ThreadClient(BufferedReader br,JTextArea cliArea){
		this.br = br;
		this.cliArea = cliArea;
	}
	
	public ThreadClient(Socket socket,ArrayList<PrintWriter> clientOutputStream,JTextArea cliArea) {
		this.socket = socket;
		this.clientOutputStream = clientOutputStream;
		this.cliArea = cliArea;
		try {
			this.br = new BufferedReader(
							new InputStreamReader(
									socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String mes;
		try {
			while((mes=br.readLine())!=null){
				sendMesToServer(mes);
				cliArea.append(mes+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**发送消息给服务端*/
	public void sendMesToServer(String mes){
		Iterator<PrintWriter> it = clientOutputStream.iterator();
		while(it.hasNext()){
			PrintWriter pw = it.next();
			pw.println(mes);
			pw.flush();
		}
	}
}
