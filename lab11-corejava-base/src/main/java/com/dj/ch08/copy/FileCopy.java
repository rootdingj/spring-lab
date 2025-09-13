package com.dj.ch08.copy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件拷贝
 * */
public class FileCopy {
	
	public FileCopy(){
		throw new AssertionError();
	}	

	/**
	 * 文件拷贝（一）
	 * @param sourceURL 源文件路径
	 * @param tragetURL 目标文件路径
	 * */
	public static void copyFile(String sourceURL,String tragetURL){
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(sourceURL);
			out = new FileOutputStream(tragetURL);
			byte[] buff = new byte[2048];
			int num = 0;
			if((num=in.read(buff))!=-1){
				out.write(buff, 0, num);
				out.flush();
			}
			System.out.println("拷贝成功！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(in != null)in.close();
				if(out != null)out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 文件拷贝（二）
	 * @param sourceURL 源文件路径
	 * @param tragetURL 目标文件路径
	 * 用到Java 7的TWR，使用TWR后可以不用在finally中释放外部资源 ，从而让代码更加优雅。
	 * */
	public static void copyFileNIO(String sourceURL,String tragetURL){
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(sourceURL);
			out = new FileOutputStream(tragetURL);
			FileChannel inChannel = in.getChannel();
			FileChannel outChannel = out.getChannel();
			ByteBuffer buff = ByteBuffer.allocate(2048);
			while(inChannel.read(buff)!=-1){
				buff.flip();
				outChannel.write(buff);
				buff.clear();
			}
			System.out.println("拷贝成功！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}/*finally{
			try {
				if(in != null)in.close();
				if(out != null)out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		copyFile("src/main/java/com/dj/base/ch08/copy/source.txt", "src/main/java/com/dj/base/ch08/copy/traget.txt");
		copyFileNIO("src/main/java/com/dj/base/ch08/copy/source.txt", "src/main/java/com/dj/base/ch08/copy/traget.txt");
	}

}

