package com.dj.ch06;

public class ReflectBean {
	private String priName = "pri";
	String defName = "def";
	protected String proName = "pro";
	public String pubName = "pub";
	public ReflectBean(){
		
	}
	public ReflectBean(String priName){
		this.priName = priName;
	}
	public String getPriName() {
		return priName;
	}
	public void setPriName(String priName) {
		this.priName = priName;
	}
	public String getDefName() {
		return defName;
	}
	public void setDefName(String defName) {
		this.defName = defName;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getPubName() {
		return pubName;
	}
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
	public void say(){
		System.out.println("没参，写个反射玩一下....");
	}
	
}
