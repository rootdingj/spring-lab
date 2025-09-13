package com.dj.fd;

public class User implements Comparable<User>{
	private String userId;
	private int score;
	public User() {
		super();
	}
	public User(String userId, int score) {
		super();
		this.userId = userId;
		this.score = score;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", score=" + score + "]";
	}
	
	@Override
	public int compareTo(User o) {
		return this.getScore()-o.getScore();
	}
	
}

