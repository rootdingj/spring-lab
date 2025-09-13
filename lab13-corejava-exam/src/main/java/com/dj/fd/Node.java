package com.dj.fd;

/** 
 * @ClassName Entry  
 * @Description 树节点 
 * @author dingj 
 * @date 2018年7月12日  
 *   
 * @param <E>
 */
public class Node{
	private int count;
	private int fromScore;
	private int toScore;
	private Node left; 
	private Node right; 
	private Node parent;
	
	/** 
	 * <p>Title: </p>
	 * <p>Description: </p> 
	 */ 
	public Node() {
		super();
	}
	
	/** 
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param fromScore
	 * @param toScore 
	 */ 
	public Node(int fromScore, int toScore) {
		super();
		this.fromScore = fromScore;
		this.toScore = toScore;
		this.left = null;
		this.right = null;
	}
	
	/** 
	 * <p>Title: </p>
	 * <p>Description: </p>
	 * @param count
	 * @param fromScore
	 * @param toScore 
	 */ 
	public Node(int count, int fromScore, int toScore) {
		super();
		this.count = count;
		this.fromScore = fromScore;
		this.toScore = toScore;
		this.left = null;
		this.right = null;
	}
	
	/** 
	 * @return count 
	 */
	public int getCount() {
		return count;
	}
	/** 
	 * @param count 要设置的 count 
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/** 
	 * @return fromScore 
	 */
	public int getFromScore() {
		return fromScore;
	}
	/** 
	 * @param fromScore 要设置的 fromScore 
	 */
	public void setFromScore(int fromScore) {
		this.fromScore = fromScore;
	}
	/** 
	 * @return toScore 
	 */
	public int getToScore() {
		return toScore;
	}
	/** 
	 * @param toScore 要设置的 toScore 
	 */
	public void setToScore(int toScore) {
		this.toScore = toScore;
	}
	/** 
	 * @return left 
	 */
	public Node getLeft() {
		return left;
	}
	/** 
	 * @param left 要设置的 left 
	 */
	public void setLeft(Node left) {
		this.left = left;
	}
	/** 
	 * @return right 
	 */
	public Node getRight() {
		return right;
	}
	/** 
	 * @param right 要设置的 right 
	 */
	public void setRight(Node right) {
		this.right = right;
	}
	/** 
	 * @return parent 
	 */
	public Node getParent() {
		return parent;
	}
	/** 
	 * @param parent 要设置的 parent 
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
}
