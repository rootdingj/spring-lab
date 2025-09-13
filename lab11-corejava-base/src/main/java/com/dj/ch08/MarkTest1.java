package com.dj.ch08;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MarkTest1 {
	public static void main(String[] args) {
		InputStream in = null;
		try {
			in = new ByteArrayInputStream("hello world".getBytes());
			/*
			 	一、如何从流中重复读入数据？
			 	   1.boolean markSupported()，先用markSupported()方法来判断这个流是否支持重复读入数据，
			 	            如果返回true，则表明可以在流上设置标记
			 	   2.void mark(int readLimit)，然后调用mark(int readLimit)方法从流的当前位置开始设置标记，
			 	            在readLimit范围内有效
			 	   3.void reset()，最后调用reset()方法，使输入流重新定位到刚才做了标记的起始位置         
			 * */
			/*System.out.println(in.markSupported());  //检测这个流是不是可以标记
			System.out.write(in.read());
			System.out.write(10);
			System.out.write(in.read());
			System.out.write(10);
			System.out.println("...............");
			in.mark(3);				    //从流的当前位置开始设置标记
			System.out.write(in.read());
			System.out.write(10);
			System.out.write(in.read());
			System.out.write(10);
			System.out.write(in.read());
			System.out.write(10);
			in.reset();					//使输入流重新定位到刚才做了标记的起始位置
			System.out.println("...........");
			System.out.write(in.read());
			System.out.write(10);*/
			
			//2. hello world
			//104  101	108	108	111	32	119	111	114	108	100	
			int data = -1;
			while((data=in.read())!=-1){
				System.out.print(data+"\t");
				System.out.write(data);
				System.out.println();
				System.out.flush();
				if(data==101){
					in.mark(1);		//把标记标在101(e)上面,下次返回重置从101下一个(108)开始(因为101在标记前已经读完了)
				}else if(data==119){
					in.reset();		//reset指针指向111(o),读完111返回在读到这个
				}
				Thread.sleep(1000);
			}
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
