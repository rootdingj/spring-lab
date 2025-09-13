package com.dj.ch08;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//整体的模式不会变
public class InputAndOutputStream {
	public static void main(String[] args) {
		InputStream in = System.in;
		OutputStream out = System.out;
		try {		
			byte[] b = new byte[512];
			int len = -1;
			while((len=in.read(b))!=-1){
				out.write(b, 0, len);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
	    }finally{
	    	try {
	    		//关闭流的基本原则：先关闭输入流，在关闭输出流
				  if(in!=null)in.close();
				  if(out!=null)out.close();
			} catch (IOException e) {
				  e.printStackTrace();
			}
		 }
	}
}
