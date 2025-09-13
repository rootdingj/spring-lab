package com.dj.ch05.awt2048;

import java.awt.Color;

/**
 * 方格
 * */
public class Block {
	/**方格的值*/
	int value;
	/**是否合并方格*/
	boolean isMerged;
	/**移动的方向*/
	String moveDirection;
	/**移动的像素*/
	int movePixels;
	
	public Block() {
		this(0);
	}
	
	public Block(int value) {
		this.value = value;
		this.setDefault();
	}
	public void setDefault() {
		isMerged = false;
		moveDirection = new String("none");
		movePixels = 0;
	}
	public boolean isEmpty() {
		return value == 0;
	}
	
	//获取之前的背景颜色
	public Color getForeGround() {
		return value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2);
	}
	//设置背景颜色
	public Color getBackground() {
		switch (value) {
			case 2:
				return new Color(0xeee4da);
			case 4:
				return new Color(0xede0c8);
			case 8:
				return new Color(0xf2b179);
			case 16:
				return new Color(0xf59563);
			case 32:
				return new Color(0xf67c5f);
			case 64:
				return new Color(0xf65e3b);
			case 128:
				return new Color(0xedcf72);
			case 256:
				return new Color(0xedcc61);
			case 512:
				return new Color(0xedc850);
			case 1024:
				return new Color(0xedc53f);
			case 2048:
				return new Color(0xedc22e);
			case 4096:
				return new Color(0x65da92);
			case 8192:
				return new Color(0x5abc65);
			case 16384:
				return new Color(0x248c51);
			default:
				return new Color(0xcdc1b4);
		}
	}
	
	/************变量的get、set方法改良**************/
	public void getDoubleValue() {
		this.value *= 2;
	}
	public boolean isMerged() {
		return isMerged;
	}
	public void setMerged() {
		this.isMerged = true;
	}
	public String getMoveDirection() {
		return moveDirection;
	}
	public void setMoveDirection(String moveDirection) {
		this.moveDirection = new String(moveDirection);
	}
	public int getMovePixels() {
		return movePixels;
	}
	public void setMovePixels(int mps) {
		movePixels = mps * (Game2048.BLOCK_SIZE + Game2048.BLOCKS_MARGIN) / Game2048.MOVE_TIMES;
	}
	
}
