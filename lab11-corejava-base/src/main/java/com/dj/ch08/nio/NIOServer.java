package com.dj.ch08.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

	@SuppressWarnings("unused")
	private static String readDataFromSocketChannel(SocketChannel sChannel) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		StringBuilder data = new StringBuilder();
		while (true) {
			buffer.clear();
			int n = sChannel.read(buffer);
			if (n == -1) {
				break;
			}
			buffer.flip();
			int limit = buffer.limit();
			char[] dst = new char[limit];
			for (int i = 0; i < limit; i++) {
				dst[i] = (char) buffer.get(i);
			}
			data.append(dst);
			buffer.clear();
		}
		return data.toString();
	}

	public static void main(String[] args) throws IOException {
		// 1.创建选择器
		Selector selector = Selector.open();
		// 2.将通道注册到选择器上
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		ssChannel.configureBlocking(false);
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		// 3.根据通道关联一个服务端套接字，并绑定IP
		ServerSocket serverSocket = ssChannel.socket();
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
		serverSocket.bind(address);
		while (true) {
			// 4.监听通道 中的IO读取操作
			selector.select();
			// 5.获取到达的事件，并循环处理
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = keys.iterator();
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				if (key.isAcceptable()) {
					ServerSocketChannel ssChannel1 = (ServerSocketChannel) key.channel();
					// 服务器会为每个新连接创建一个 SocketChannel
					SocketChannel sChannel = ssChannel1.accept();
					sChannel.configureBlocking(false);
					// 这个新连接主要用于从客户端读取数据
					sChannel.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) {
					SocketChannel sChannel = (SocketChannel) key.channel();
					System.out.println(readDataFromSocketChannel(sChannel));
					sChannel.close();
				}
				keyIterator.remove();
			}
		}
	}

}
