package com.dj.ch08;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class StringReaderTest {
	
	public static void main(String[] args) {
		
		StringReader in = null;
		StringWriter out = null;
		try {
			in = new StringReader("hell");
			out = new StringWriter();
			int data = -1;
			int num = 0;
			while((data=in.read())!=-1){
				System.out.println(data);
				out.write(data);
				out.flush();
				num++;
			}
			System.out.println("num="+num);
			System.out.println("out.toString()="+out.toString());
		} catch (IOException e) {
			e.printStackTrace();
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
