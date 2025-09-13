package com.dj.ch08;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
       从文件中读入，输出到控制台
 * */
public class FileInputStreamTest {
	public static void main(String[] args) {
		FileInputStream in = null;
		OutputStream out =System.out;
		try {
			String filePath = "src/main/java/com/dj/base/ch08/hello";
			File file = new File(filePath);
		    in = new FileInputStream(file);
		    
		    int len = -1;
		    int a = 0;
		    while((len=in.read())!=-1){
		    	a++;
		    	out.write(len);
		    	out.flush();
		    }
		    System.out.println();
		    System.out.println(".........................");
		    System.out.println("a="+a);
		} catch (Exception e) {
		}finally{
			try {
				if(in!=null)in.close();
				if(out!=null)out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
