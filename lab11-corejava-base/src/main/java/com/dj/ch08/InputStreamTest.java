package com.dj.ch08;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamTest {
	public static void main(String[] args) {
		//声明流，控制台输入
		InputStream in = System.in;   
		try {
		    //InputStream的三个read()重载方法
		   /* 
		    1、操作流读写数据，read()
			      一次读一个字节，读的是字节的值，要读出数据（首先得有读入数据啊），所以会有个线程阻塞效果
			     在控制台输入1然后回车，控制台返回49、13、10分别是键盘中1、回车、换行的ASCII码值
		    */ 	         
			int data = in.read();  
			//拿到数据后进行对应的处理数据操作
			while(data!=32){
				data = in.read();
				System.out.println(data);
			}
			
			//2、read(byte[] b);
			/*byte[] b = new byte[5];
			int len = in.read(b);
			//输入a,打印3(a、回车、换行一共三个字节)；输入12345，打印5(数组的长度为5，只能存这么多)
			System.out.println(len);  
			System.out.println("..............");
			for(byte data:b){
				System.out.println(data);
			}
			System.out.println("..................");
			//把字节转成字符串,2位回车(13)+换行(10),另外空格(32)
			//程序中的操作最终都是以字节码的形式向操作系统发送指令
			String str = new String(b, 0, len-2); 	
			System.out.println(str);*/
			
			//3、read(byte[] b, int off,int len);
			/*byte[] b = new byte[5];
			int len = in.read(b, 1, b.length-1);
			System.out.println(len);
			System.out.println(".................");
			for(byte data:b){
				System.out.println(data);
			}*/
				
			//4、	
			/*byte[] b = new byte[10];
			int len = in.read(b);
			while(len!=-1){
				//去除回车、换行所占数组的长度
				if(b[len-1]==10&&b[len-2]==13) len = len-2;   
				String s = new String(b, 0, len);
				System.out.println(s);
				len = in.read(b);
			}*/
			
			//5、自定义模拟命令	
			/*byte[] b = new byte[10];
			int len = -1;
			while((len=in.read(b))!=-1){       //-1,读到了文件的末尾
				if(b[len-1]==10&&b[len-2]==13) len = len-2;   //去除两个回车、换行字符的数组长度
				String s = new String(b, 0, len);
				if("bye".equals(s))break;
				if("ls".equals(s)){
					System.out.println(" a.txt "+" b.txt "+" c.zip "+" Hello.java ");
				}else{
					System.out.println(s);
				}
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				//关闭流
				if(in!=null)in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
