package com.dj.ch08;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DataInputStreamTest {
	public static void main(String[] args) {
		InputStream in = null;
		DataInputStream dis = null;
		String filePath = "src/main/java/com/dj/base/ch08/hello";
		try {
			in = new FileInputStream(filePath);
			dis = new DataInputStream(in);
			System.out.println(dis.readInt());
//			System.out.println(dis.readUTF());
//			System.out.println(dis.readDouble());
//			System.out.println(dis.readLong());
			System.out.println(dis.readByte());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
