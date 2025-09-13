package com.dj.ch08;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {
	public static void main(String[] args) {
		FileOutputStream out = null;
		
		try {
			String filePath = "src/main/java/com/dj/base/ch08/b.txt";
			out =  new FileOutputStream(filePath,true);
			String str = "dj......";
			out.write(str.getBytes());
			out.write(10);
			out.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if (out != null)out.close();
			} catch (Exception e2) {
			}
		}
	}
}
