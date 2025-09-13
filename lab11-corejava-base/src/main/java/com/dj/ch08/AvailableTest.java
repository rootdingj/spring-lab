package com.dj.ch08;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AvailableTest {
	public static void main(String[] args) {
		InputStream in = null;
		try {
			String filePath = "src/main/java/com/dj/base/ch08/hello";
			File file = new File(filePath);
			in = new FileInputStream(file);
			//返回可以从输入流读取的字节数
			int len = in.available();   
			System.out.println(len);
			System.out.println(file.length());
			System.out.println("-------------------------");
			int data = -1;
			while((data=in.read())!=-1){
				System.out.println(in.available());
				System.out.println(data);
				System.out.println(".............");
				Thread.sleep(1000);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			try {
				if(in!=null)in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
  }
}