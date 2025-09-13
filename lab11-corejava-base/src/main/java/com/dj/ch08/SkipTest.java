package com.dj.ch08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SkipTest {
	public static void main(String[] args) {
		InputStream in = null;
		try {
			String filePath = "src/main/java/com/dj/base/ch08/hello";
			/*File file = new File(filePath);
			in = new FileInputStream(file);*/
			
			in = new FileInputStream(filePath);
			System.out.println(in.available());
			in.skip(5);	//从输入流中跳过参数n指定数目的字节。
			int data = -1;
			while((data=in.read())!=-1){
				System.out.write(data);
				System.out.flush();
			}
			System.out.println();
			System.out.println(in.available());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
