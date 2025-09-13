package com.dj.ch08;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DataOutputStreamTest {
	
	public static void main(String[] args) {
		OutputStream out = null;
		DataOutputStream dos = null;
		try {
			String filePath = "src/main/java/com/dj/base/ch08/hello";
			out = new FileOutputStream(filePath);
			dos = new DataOutputStream(out);
			dos.writeInt(99);
			dos.writeChar('h');
			dos.writeDouble(10.5);
			dos.writeUTF("大家好");
			dos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				//封装流在后面关闭
				if(out!=null)out.close();
				if(dos!=null)dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
