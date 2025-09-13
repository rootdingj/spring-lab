package com.dj.ch08;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MarkTest2 {
	public static void main(String[] args) {
		
		InputStream in = null;
		 //InputStream的封装流
		 //BufferedInputStream类覆盖了被过滤的输入流的读数据行为，利用缓冲区来提高读数据的效率
		BufferedInputStream bis = null;
		
		try {
			in = new ByteArrayInputStream("hello world".getBytes()); 
			//BufferedInputStream中嵌套了一个ByteArrayInputStream，并且他的缓冲区大小为5个字节
			bis = new BufferedInputStream(in,5);
			
			System.out.println(bis.markSupported());
			System.out.println("---------------------");
			
			//hello world
			//104  101	108	108	111	32	119	111	114	108	100	
			//51(缓冲区的大小=5，标记的有效范围=1)  成功：缓冲区>标记数||reset在标记数之外
			//54(..........5............=5)  成功...........||.............内
			//15(.........=1,...........=5)  成功:缓冲区<标记数||.............内
			//11(..........1.............1)	  失败:缓冲区<标记数&&reset在标记数之外
			//总结：两个条件满足一个就好了
			int data = -1;
			while((data=bis.read())!=-1){
				System.out.write(data);
				System.out.flush();
				if(data==101){
					bis.mark(5);		
				}else if(data==111){
					bis.reset();		
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(in!=null)in.close();
				if(bis!=null)bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
