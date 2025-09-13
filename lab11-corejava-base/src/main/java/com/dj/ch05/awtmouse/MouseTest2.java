package com.dj.ch05.awtmouse;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MouseTest2 extends JFrame implements ActionListener {
	// 给出一些基本要用的东西
	private Container container;
	private JPanel north, center;
	private JComboBox<String> box;
	private JLabel timeLabel, time, centLabel, cent;
	private JButton start;

	private JButton[] btns;

	private Timer timer;
	private ImageIcon icon;
	private Timer mouseTimer;
	private int level;

	private boolean flag;
	// 上一次老鼠出现位置
	private int lastMouse = -1;

	public MouseTest2() {
		// 设置窗口的基本属性
		setTitle("打地鼠入门游戏");
		container = getContentPane();
		setLocation(100, 100);
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		init();
		setVisible(true);

	}

	private void init() {
		// 初始化组件
		north = new JPanel();
		center = new JPanel();
		box = new JComboBox<String>();
		box.addItem("初级");
		box.addItem("中级");
		box.addItem("高级");

		timeLabel = new JLabel("time:");
		time = new JLabel("10");
		centLabel = new JLabel("cent:");
		cent = new JLabel("0");
		start = new JButton("开始");

		btns = new JButton[9];
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
			btns[i].setEnabled(false);// 1
		}

		icon = new ImageIcon("src/com/dj/ch11_3/mouse.jpg");

		// 设置容器的布局管理器
		north.setLayout(new FlowLayout());
		center.setLayout(new GridLayout(3, 3));
		container.setLayout(new BorderLayout());

		// 把组件添加到容器中
		north.add(box);
		north.add(timeLabel);
		north.add(time);
		north.add(centLabel);
		north.add(cent);
		north.add(start);

		for (int i = 0; i < btns.length; i++) {
			center.add(btns[i]);
		}

		container.add(north, BorderLayout.NORTH);
		container.add(center, BorderLayout.CENTER);

		// 添加事件处理
		start.addActionListener(this);

		timer = new Timer(1000, this);
		level = 1000;
		mouseTimer = new Timer(level, this);
		box.addActionListener(this);
		for (int i = 0; i < btns.length; i++) {
			btns[i].addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 通过事件对象获得事件源
		Object source = e.getSource();
		// start按钮产生事件
		if (start == source) {
			// 1.1.按钮设置为可用的
			for (int i = 0; i < btns.length; i++) {
				btns[i].setEnabled(true);
			}
			box.setEnabled(false);// 级别不可动
			start.setEnabled(false);// 开始不可按
			cent.setText("0");
			time.setText("10");
			timer.start();
			mouseTimer.start();
		}
		// 倒计时产生事件
		else if (timer == source) {
			String text = time.getText();
			int num = Integer.parseInt(text);
			if (num <= 0) {

				timer.stop();
				mouseTimer.stop();
				if (lastMouse != -1) {
					btns[lastMouse].setIcon(null);
				}
				box.setEnabled(true);
				start.setEnabled(true);
				for (int i = 0; i < btns.length; i++) {
					btns[i].setEnabled(false);
				}
			} else {
				num--;
				time.setText(num + "");
			}
		}
		// 老鼠定时器产生事件
		else if (mouseTimer == source) {
			if (lastMouse != -1) {
				btns[lastMouse].setIcon(null);
			}
			int x = (int) (Math.random() * 9); // 随机出现一只老鼠在网格中
			btns[x].setIcon(icon);
			flag = false;
			lastMouse = x;
		}

		//
		else if (box == source) {
			String str = (String) box.getSelectedItem();
			if ("初级".equals(str)) {
				level = 1000;
			} else if ("中级".equals(str)) {
				level = 500;
			} else {
				level = 100;
			}
			mouseTimer = new Timer(level, this);

		} else {
			for (int i = 0; i < btns.length; i++) {
				if (btns[i] == source && btns[i].getIcon() != null && !flag) {
					String text = cent.getText();
					int num = Integer.parseInt(text);
					num++;
					cent.setText(num + "");
					flag = true;
					break;
				}
			}
		}
	}

	public static void main(String[] args) {

		new MouseTest2();
	}
}
