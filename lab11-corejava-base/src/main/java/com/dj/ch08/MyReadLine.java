package com.dj.ch08;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
       自定义包装流 
 * */
public class MyReadLine {
	private InputStream in;
	private List<Integer> list;
	public MyReadLine(InputStream in) {
		this.in = in;
		list = new ArrayList<Integer>();
	}
	
	public String readLine() throws IOException{
		list.clear();
		int data = -1;
		byte[] buf = null;
		while((data=in.read())!=-1){
			//过滤回车、换行键，把除回车、换行的值放入list中
			if(data==10){
				break;
			}else if(data!=13){
				list.add(data);
			}
		}
		if(data==-1&&list.size()==0){
			return null;
		}else{
			//将list集合中的数据放入buf字节数组
			buf = new byte[list.size()];
			for(int i=0;i<list.size();i++){
				int x = list.get(1);
				buf[i] = (byte) x;
			}
		}
		return new String(buf,0,buf.length);
	}
}
