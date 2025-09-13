package com.dj.ch08;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamTest {
	public static void main(String[] args) {
		OutputStream out = System.out;   //输出到控制台
		try {
			//1、write(int a)
			/*out.write(97);
			//流的内部有缓冲区，资源一般先经过缓冲区，所以要清空缓冲区中的数据
			out.flush();  */
			
			//2、write(byte[] b)
			/*byte[] b = {97,98,99,100,111};
			String str = "hello world";
			byte[] c = str.getBytes();
			out.write(b);
			System.out.println();
			out.write(c);
			out.flush();*/
					
			//3、write(byte[] b,int off,int len)
			byte[] b = new byte[7];
			b[0] = 97;
			b[1] = 98;
			b[2] = 99;
			b[3] = 100;
			b[4] = 101;
			out.write(b,1,3);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(out!=null)out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
