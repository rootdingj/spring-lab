package com.dj.ch08;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BufferedInputStreamTest {
	public static void main(String[] args) {
		
		InputStream in = null;
		BufferedInputStream bis = null;
		String filePath = "src/com/dj/ch13_2/test";
		try {
			in = new FileInputStream(filePath);
			//BufferedInputStream嵌套在FileInputStream上，缓冲区的大小是1kB
			bis = new BufferedInputStream(in, 1024);
			int data = -1;
			long l1 = System.currentTimeMillis();
			/*while((data=in.read())!=-1){   //31毫秒
				System.out.write(data);
			}*/
			while((data=bis.read())!=-1){    //16毫秒
				System.out.write(data);
			} 
			long l2 = System.currentTimeMillis();
			System.out.println(l2-l1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
