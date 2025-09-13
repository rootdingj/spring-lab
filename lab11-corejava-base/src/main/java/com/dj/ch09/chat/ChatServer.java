package com.dj.ch09.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * 多线程聊天服务端
 * */
public class ChatServer {
	private JFrame serFrame;
	private JLabel portLabel,mesLabel,nickNmLabel;
	private JTextField portText,mesText,nickNmText;
	private JButton startButton,mesButton,nickNmButton;
	private JPanel northPanel,south1Panel,south2Panel,south3Panel;
	private JTextArea serArea;
	private JScrollPane scroll;
	
	private String nickNm;
	private ArrayList<PrintWriter> serverOutputStream;
	private int portInt;

	//1、窗口基本属性初始化
	public ChatServer() {
		nickNm = "多线程聊天服务端";
		serFrame = new JFrame();
		portLabel = new JLabel("端口", JLabel.LEFT);
		mesLabel = new JLabel("消息", JLabel.LEFT);
		nickNmLabel = new JLabel("昵称", JLabel.LEFT);
		
		portText = new JTextField(30);
		mesText = new JTextField(30);
		nickNmText = new JTextField(30);
		
		startButton = new JButton("开始");
		mesButton = new JButton("确认");
		nickNmButton = new JButton("确认");
		
		northPanel = new JPanel();
		south1Panel = new JPanel();
		south2Panel = new JPanel();
		south3Panel = new JPanel();
		
		serArea = new JTextArea();
		scroll = new JScrollPane(serArea);
	}

	//2、 组件处理
	public void init(){
		//窗口设置
		serFrame.setTitle("多线程聊天服务端");
		serFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		serFrame.setSize(550, 550);
		
		//北区
		northPanel.add(portLabel);
		northPanel.add(portText);
		northPanel.add(startButton);
		serFrame.getContentPane().add(northPanel, BorderLayout.NORTH);

		//中间
		serArea.setFocusable(false);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		serFrame.getContentPane().add(serArea, BorderLayout.CENTER);
		
		//南区
		south2Panel.add(nickNmLabel);
		south2Panel.add(nickNmText);
		south2Panel.add(nickNmButton);
		south3Panel.add(mesLabel);
		south3Panel.add(mesText);
		south3Panel.add(mesButton);
		south1Panel.setLayout(new BoxLayout(south1Panel, BoxLayout.Y_AXIS));
		south1Panel.add(south2Panel);
		south1Panel.add(south3Panel);
		serFrame.getContentPane().add(south1Panel, BorderLayout.SOUTH);
		
		serFrame.setVisible(true);
	}
	
	public void startUp(){
		init();
		//监听开始按钮
		ActionListener startListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverOutputStream = new ArrayList<PrintWriter>();
				final String tport = portText.getText();
				if(!"".equals(tport)){
					/*portInt = Integer.parseInt(tport);
					//接收消息线程
					Runnable threadServer = new ThreadServer(portInt, serArea);
					Thread server = new Thread(threadServer);
					server.start();*/
					
					Runnable serverThread = new Runnable() {
						public void run() {
							ServerSocket server = null;
							try {
								//1.创建一个ServerSocket对象表示服务器，同时绑定一个端口
								server = new ServerSocket(Integer.parseInt(tport));
								while(true){
									serArea.append("等待客户端连接...\n");
									//2.监听连接请求
									Socket accept = server.accept();
									serArea.append("客户端已连接...\n");
									//3.获取输出流和输入流，开始网络数据的发送和接收
									 PrintWriter pw = new PrintWriter(accept.getOutputStream());
									 serverOutputStream.add(pw);
									 Thread client = new Thread(new ThreadClient(accept, serverOutputStream, serArea));
									 client.start();
								}
							} catch (NumberFormatException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					};
					Thread server = new Thread(serverThread);
					server.start();
				}else{
					JOptionPane.showConfirmDialog(serFrame, "端口号不能为空，请输入正确端口号...");
				}
			}
		};
		startButton.addActionListener(startListener);
		portText.addActionListener(startListener);
		
		//监听昵称
		ActionListener nickNmListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tnickNm = nickNmText.getText();
				if(!"".equals(tnickNm))
					nickNm = tnickNm;
			}
		};
		nickNmButton.addActionListener(nickNmListener);
		nickNmText.addActionListener(nickNmListener);
		nickNmText.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				String tnickNm = nickNmText.getText();
				if(!"".equals(tnickNm))
					nickNm = tnickNm;
			}
			public void focusGained(FocusEvent e) {}
		});
		
		//监听mes按钮，发送消息
		ActionListener mesListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tmes = mesText.getText();
				if(!"".equals(tmes)){
					tmes = nickNm+":"+tmes;
//					new ThreadServer(portInt, serArea).sendMesToClient(tmes);
					sendMesToClient(tmes);
					serArea.append(tmes+"\n");
					mesText.setText("");
				}else{
					JOptionPane.showMessageDialog(serFrame, "内容不能为空！"); 
				}
			}
		};
		mesButton.addActionListener(mesListener); 
		mesText.addActionListener(mesListener); 
	}

	private void sendMesToClient(String mes){
		Iterator<PrintWriter> it = serverOutputStream.iterator();
		while(it.hasNext()){
			PrintWriter pw = it.next();
			pw.println(mes);
			pw.flush();
		}
	}
	public static void main(String[] args) {
		ChatServer cs = new ChatServer();
		cs.startUp();
	}
}


