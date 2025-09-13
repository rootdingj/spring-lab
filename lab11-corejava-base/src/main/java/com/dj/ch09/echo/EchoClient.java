package com.dj.ch09.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient {

	public static void main(String[] args) {
		clientMain();
		
	}

	public static void clientMain(){
		Socket socket = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		try {
			socket = new Socket("127.0.0.1", 6789);
			Scanner scanner = new Scanner(System.in);
			System.out.println("");
			String msg = scanner.nextLine();
			scanner.close();
			pw = new PrintWriter(socket.getOutputStream());
	        pw.println(msg);
	        pw.flush();
			br = 
				new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
			System.out.println(br.readLine());
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null)br.close();
				if(pw!=null)pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
