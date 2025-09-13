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

/**
 * 打地鼠小游戏：</br>
 * 1、定义容器、变量：首先得弄清楚，需要什么，怎么布局把他罗列出来</br>
 * 2、组件处理</br>
 * 2.1、组件初始化</br>
 * 2.2、把组件放入容器中，并配合容器的套用设定布局</br>
 * 2.3、添加事件</br>
 * 3、事件处理</br>
 * 3.1、开始按钮产生的事件</br>
 * 3.2、倒计时产生的事件</br>
 * 3.3、定时器产生的事件</br>
 * 3.4、下拉框产生的事件</br>
 * 3.5、点击老鼠产生的事件</br>
 */
public class MousePlay extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Container container;
	private JPanel north;
	private JPanel center;
	private JComboBox<String> box;
	private JLabel timeLable;
	private JLabel time;
	private JLabel centeLable;
	private JLabel cente; // 分数
	private JButton start; // 开始按钮
	private JButton[] buts; // 九宫格按钮

	private Timer timer; // 计时器
	private Timer mouseTimer; // 出现老鼠头像定时器
	private ImageIcon icon; // 老鼠图标

	private int level; // 游戏难度
//用来表示上一次老鼠出现的位置
	private int lastMouse = -1;
//表示当前老鼠出现的时候有没有点中加分 false true
	private boolean flag;

//1、窗口基本属性初始化
	public MousePlay() {
		setTitle("打地鼠");
		container = getContentPane();
		setLocation(100, 100);
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		init();
		setVisible(true);
	}

//2、 组件处理
	public void init() {
		// 1、初始化组件
		north = new JPanel();
		center = new JPanel();
		box = new JComboBox<String>();
		box.addItem("初级");
		box.addItem("中级");
		box.addItem("高级");
		timeLable = new JLabel("time:");
		time = new JLabel("10");
		centeLable = new JLabel("center:");
		cente = new JLabel("0");
		start = new JButton("开始");
		// 默认老鼠按钮不可用
		buts = new JButton[9];
		for (int i = 0; i < buts.length; i++) {
			buts[i] = new JButton();
			buts[i].setEnabled(false);
		}
		icon = new ImageIcon("src/com/ch04_mouse/mouse.jpg");
		north.setLayout(new FlowLayout());
		center.setLayout(new GridLayout(3, 3));
		container.setLayout(new BorderLayout());

		// 2、把组件添加到容器中
		north.add(box);
		north.add(timeLable);
		north.add(time);
		north.add(centeLable);
		north.add(cente);
		north.add(start);
		for (int i = 0; i < buts.length; i++) {
			center.add(buts[i]);
		}
		container.add(north, BorderLayout.NORTH);
		container.add(center, BorderLayout.CENTER);
		// 3、添加事件
		start.addActionListener(this);
		for (int i = 0; i < buts.length; i++) {
			buts[i].addActionListener(this);
		}
		timer = new Timer(1000, this);
		level = 1000;
		mouseTimer = new Timer(level, this);
		box.addActionListener(this);
	}

//3、事件处理
	public void actionPerformed(ActionEvent e) {
		// 通过事件对象获得事件源
		Object source = e.getSource();
		// 开始按钮产生的事件
		if (start.equals(source)) {
			// 老鼠按钮可用
			for (int i = 0; i < buts.length; i++) {
				buts[i].setEnabled(true);
			}
			// 开始按钮不能再点击
			start.setEnabled(false);
			// 游戏级别不能改变
			box.setEnabled(false);
			// 把开始时间重置为10
			time.setText("10");
			// 把分数重置为0
			cente.setText("0");
			// 开启时间倒计时
			timer.start();
			// 启动出现老鼠头像定时器
			mouseTimer.start();

		}
		// 倒计时产生的事件
		else if (timer.equals(source)) {
			int num = Integer.parseInt(time.getText());
			if (num <= 0) {
				// 定时器停止时
				timer.stop();
				mouseTimer.stop();
				if (lastMouse != -1) {
					buts[lastMouse].setIcon(null);
				}
				box.setEnabled(true);
				start.setEnabled(true);
				for (int i = 0; i < buts.length; i++) {
					buts[i].setEnabled(false);
				}
			} else {
				num--;
				time.setText(num + "");
			}

		}
		// 定时器产生的事件
		else if (mouseTimer.equals(source)) {
			if (lastMouse != -1) {
				buts[lastMouse].setIcon(null);
			}
			int x = (int) (Math.random() * 9);
			buts[x].setIcon(icon);
			// 表示当前老鼠刚出现 还没有点击加分
			flag = false;
			// 记录当前老鼠出现的位置
			lastMouse = x;
		}
		// 下拉框产生的事件
		else if (box.equals(source)) {
			String str = (String) box.getSelectedItem();
			if ("初级".equals(str)) {
				level = 1000;
			} else if ("中级".equals(str)) {
				level = 500;
			} else {
				level = 100;
			}
			mouseTimer = new Timer(level, this);
		}
		// 点击老鼠产生的事件
		else {
			for (int i = 0; i < buts.length; i++) {
				if (buts[i] == source && buts[i].getIcon() != null && !flag) {
					String text = cente.getText();
					int num = Integer.parseInt(text);
					num++;
					cente.setText(num + "");
					flag = true;
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		new MousePlay();
	}
}
