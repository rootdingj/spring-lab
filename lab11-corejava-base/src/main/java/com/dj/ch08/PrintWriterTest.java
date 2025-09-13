package com.dj.ch08;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterTest {
	
	public static void main(String[] args) {
		PrintWriter out = null;
		try {
//			out = new PrintWriter("src/main/java/com/dj/base/ch08/c.txt");
//			File file = new File("src/main/java/com/dj/base/ch08/c.txt");
//			out = new PrintWriter(new FileOutputStream("src/main/java/com/dj/base/ch08/c.txt"));
			out = new PrintWriter(new FileWriter("src/main/java/com/dj/base/ch08/c.txt"));
			out.println("test1");
			out.println("test2");
			out.println("test3");
			out.println("test4");
			out.println("test5");
			out.println("tom");
			out.println("zhangsan");
			out.println("lisi");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null)out.close();
		}
	}
}
