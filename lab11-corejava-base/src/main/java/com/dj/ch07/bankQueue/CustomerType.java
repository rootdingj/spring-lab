package com.dj.ch07.bankQueue;

public enum CustomerType {
	COMMON,QUICK,VIP;
	public String toString(){
		switch(this){
		case COMMON:
			return "普通";
		case QUICK:
			return "快速";
		case VIP:
			return name();
		}
		return null;
	}
}

