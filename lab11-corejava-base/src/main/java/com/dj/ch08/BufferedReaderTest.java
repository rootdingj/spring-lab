package com.dj.ch08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BufferedReaderTest {
	
	public static void main(String[] args) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader in = null;
		try {
			is = System.in;
			isr = new InputStreamReader(is);
			in = new BufferedReader(isr);
			String str = null;
			//(str=in.readLine())!=null
			while(!"bye".equals((str=in.readLine()))){
				System.out.println(Arrays.toString(str.getBytes()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(is!=null)is.close();
				if(isr!=null)isr.close();
				if(in!=null)in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
