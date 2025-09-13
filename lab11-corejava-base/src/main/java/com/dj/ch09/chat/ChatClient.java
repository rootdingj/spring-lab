package com.dj.ch09.chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

public class ChatClient {
	private JFrame cliFrame;
	private JLabel ipLabel,portLabel,mesLabel,nickNmLabel;
	private JTextField ipText,portText,mesText,nickNmText;
	private JButton connButton,mesButton,nickNmButton;
	private JPanel northPanel,south1Panel,south2Panel,south3Panel;
	private JTextArea cliArea;
	private JScrollPane scroll;
	
	private BufferedReader br; 
	private PrintWriter pw; 
	private String nickNm; 
	
	public ChatClient() {
		nickNm = "多线程聊天客户端";
		cliFrame = new JFrame();
		ipLabel = new JLabel("IP：", JLabel.LEFT);
		portLabel = new JLabel("端口：", JLabel.LEFT);
		mesLabel = new JLabel("消息：", JLabel.LEFT);
		nickNmLabel = new JLabel("昵称：", JLabel.LEFT);
		
		ipText = new JTextField(10);
		portText = new JTextField(10);
		mesText = new JTextField(30);
		nickNmText = new JTextField(30);
		
		connButton = new JButton("连接");
		mesButton = new JButton("确认");
		nickNmButton = new JButton("确认");
		
		northPanel = new JPanel();
		south1Panel = new JPanel();
		south2Panel = new JPanel();
		south3Panel = new JPanel();
		
		cliArea = new JTextArea();
		scroll = new JScrollPane(cliArea);
	}

	public void init(){
		//窗口设置
		cliFrame.setTitle("多线程聊天客户端");
		cliFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cliFrame.setSize(550, 550);
		
		//北区
		northPanel.add(ipLabel);
		northPanel.add(ipText);
		northPanel.add(portLabel);
		northPanel.add(portText);
		northPanel.add(connButton);
		cliFrame.getContentPane().add(northPanel, BorderLayout.NORTH);

		//中间
		cliArea.setFocusable(false);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		cliFrame.getContentPane().add(cliArea, BorderLayout.CENTER);
		
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
		cliFrame.getContentPane().add(south1Panel, BorderLayout.SOUTH);
		
		cliFrame.setVisible(true);
	}
	
	public void startUp(){
		init();
		//接收消息的线程
		final Runnable receiveThread = new Runnable() {
			public void run() {
				String mes;
				try {
					while((mes=br.readLine())!=null){
						cliArea.append(mes+"\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		
		//监听conn按钮，连接服务器
		connButton.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				String tip = ipText.getText();
				String tport = portText.getText();
				if(!"".equals(tip)&&!"".equals(tport)){
					try {
						Socket socket = new Socket(tip, Integer.parseInt(tport));
						br = new BufferedReader(
								new InputStreamReader(socket.getInputStream()));
						pw = new PrintWriter(socket.getOutputStream());
						cliArea.append("服务器已连接...\n");
						//接收消息线程
						Thread t = new Thread(receiveThread);
						t.start();
					} catch (Exception  e1) {
						e1.printStackTrace();
						 JOptionPane.showMessageDialog(cliFrame, "连接不上服务器!\n请确认 IP和 端口 输入正确。"); 
					}
				}else{
					JOptionPane.showMessageDialog(cliFrame, "请输入正确的IP和端口");
				}				
			}
		});
		
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
					pw.println(nickNm+":"+tmes);
					pw.flush();
					mesText.setText("");
				}else{
					JOptionPane.showMessageDialog(cliFrame, "内容不能为空！"); 
				}
			}
		};
		mesButton.addActionListener(mesListener); 
		mesText.addActionListener(mesListener); 
	}
	
	public static void main(String[] args) {
		ChatClient client = new ChatClient();
		client.startUp();
	}
}
