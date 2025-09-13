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

public class MouseTest extends JFrame implements ActionListener {
	private static final long serialVersionUID = 8643166800318888102L;

	private Container container;
	private JPanel north;
	private JPanel center;
	private JComboBox<String> box;
	private JLabel timeLable, time, centLable, cent;
	private JButton start;

	private JButton[] btns;

	// 时间的倒计时
	private Timer timer;
	private ImageIcon icon;
	private Timer mouseTimer;
	private int level;

	// 用来表示上一次老鼠出现的位置
	private int lastMouse = -1;
	// 表示当前老鼠出现的时候有没有点中加分 false true
	private boolean flag;

	public MouseTest() {
		// 设置窗口的基本属性
		setTitle("打地鼠");
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

		timeLable = new JLabel("time:");
		time = new JLabel("10");
		centLable = new JLabel("cent:");
		cent = new JLabel("0");
		start = new JButton("开始");

		btns = new JButton[9];
		for (int i = 0; i < btns.length; i++) {
			btns[i] = new JButton();
			btns[i].setEnabled(false);
		}

		icon = new ImageIcon("src/com/dj/ch11_3/mouse.jpg");

		// 设置容器的布局管理器
		north.setLayout(new FlowLayout());
		center.setLayout(new GridLayout(3, 3));
		container.setLayout(new BorderLayout());

		// 把组件添加到容器中
		north.add(box);
		north.add(timeLable);
		north.add(time);
		north.add(centLable);
		north.add(cent);
		north.add(start);

		for (int i = 0; i < btns.length; i++) {
			center.add(btns[i]);
		}
		container.add(north, BorderLayout.NORTH);
		container.add(center, BorderLayout.CENTER);
		// 添加事件处理
		start.addActionListener(this);
		for (int i = 0; i < btns.length; i++) {
			btns[i].addActionListener(this);
		}
		timer = new Timer(1000, this);
		level = 1000;
		mouseTimer = new Timer(level, this);
		box.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 通过事件对象获得事件源
		Object source = e.getSource();
		// 如果是start按钮产生事件
		if (start == source) {
			// 把按钮变为可用的 默认是不可点击的
			for (int i = 0; i < btns.length; i++) {
				btns[i].setEnabled(true);
			}
			// 游戏级别不能改变
			box.setEnabled(false);
			// 开始按钮不能再点击
			start.setEnabled(false);
			// 把开始时间重置为10
			time.setText("10");
			// 把分数重置为0
			cent.setText("0");
			// 开启时间倒计时
			timer.start();
			// 启动出现老鼠头像定时器
			mouseTimer.start();
		}
		// 如果是倒计时产生事件
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
		// 如果是老鼠定时器产生事件
		else if (mouseTimer == source) {
			if (lastMouse != -1) {
				btns[lastMouse].setIcon(null);
			}
			int x = (int) (Math.random() * 9);
			btns[x].setIcon(icon);
			// 表示当前老鼠刚出现 还没有点击加分
			flag = false;
			// 记录当前老鼠出现的位置
			lastMouse = x;

		}
		// 如果是下拉列表产生的事件
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
		}
		// 如果是点击老鼠的那些按钮产生的事件
		else {

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
		new MouseTest();
	}
}
