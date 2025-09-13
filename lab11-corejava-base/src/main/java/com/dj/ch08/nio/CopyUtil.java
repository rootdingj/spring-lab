package com.dj.ch08.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void fastCopy(String src, String dist) throws IOException {
		// 1.获取源文件输入流
		FileInputStream fis = new FileInputStream(src);
		// 2.根据输入流获取通道
		FileChannel fisCh = fis.getChannel();
		// 3.获取目标文件输出流
		FileOutputStream fos = new FileOutputStream(dist);
		// 4.根据输出流获取通道
		FileChannel fosCh = fos.getChannel();
		// 5.创建缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 从输入通道中读取数据到缓冲区中
		while (fisCh.read(buffer) != -1) {
			// 切换读写
			buffer.flip();
			// 把缓冲区的内容写入输出文件中
			fosCh.write(buffer);
			// 清空缓冲区
			buffer.clear();
		}
	}

}
