package com.dj.juc.pool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskExecutionWebServer {
    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(20, 20, 30, TimeUnit.SECONDS,
        new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.DiscardOldestPolicy());

    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8080);
        while (true) {
            final Socket accept = socket.accept();
            Runnable task = new Runnable() {
                
                @Override
                public void run() {
                    handle(accept);
                }
            };
            pool.execute(task);
        }
    }
    
    private static void handle(Socket accept) {
        System.out.println("handle....");
    }

}
