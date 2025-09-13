package com.dj.ch05.awt2048;

import java.awt.Color;
import java.awt.Image;
import java.util.List;

import javax.swing.JPanel;

/**
 * 模拟2048小游戏：</br>
 * 游戏规则：滑动屏幕来移动小方块，两个数字一样的小方块相撞时就会相加合成一个方块，
 * 每次操作之后会在空白的方格处随机生成一个2或4的方块，最终得到一个2048的方块就算胜利了， 如果16个格子全部填满无法移动的话GAMEOVER。
 */
public class Game2048 extends JPanel {
	/** 背景颜色 */
	static Color BG_COLOR = new Color(0xbbada0);
	/** 字体 */
	static String FONT_NAME = "Arial";
	/** 方格像素(宽度) */
	static int BLOCK_SIZE = 64;
	/** 方格之间间距像素 */
	static int BLOCKS_MARGIN = 16;
	static int MOVE_TIME = 80;
	static int MOVE_TIMES = 20;

	static int ENLARGE_SIZE = 15;
	static int ENLARGE_TIME = 50;
	static int ENLARGE_TIMES = 10;
////是否赢、输、移动、合并
	boolean isWin, isLose, move, merge;
//动画覆盖
	boolean animateOver = true;
//移动前得数组，上一次移动后的结果数组
	Block[][] blocks, resultBlocks;

	Image image;
//方格最大数值
	int max;
//分数，方格的很坐标、纵坐标
	int score, xx, yy;
//移动、合并方格集合
	public List<Block> moveList;
	public List<Block> mergeList;

	public static void main(String[] args) {
		new Game2048();
	}
}