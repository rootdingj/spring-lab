package com.dj.ch06;

public class Generic<T> {
	private T key;

	public <E> void genericMethod(E e) {
		// TODO
	}

	public Generic(T key) {
		super();
		this.key = key;
	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "Generic [key=" + key + "]";
	}

}
