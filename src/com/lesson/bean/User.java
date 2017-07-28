package com.lesson.bean;

public class User {
	private String username;
	private String password;
	private String email;
	private int isonline;
	/**
	 * 设备一：手机
	 * 设备二：pc
	 * 设备三：网页
	 */
	private String onlinedevice1;
	private String onlinedevice2;
	private String onlinedevice3;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsonline() {
		return isonline;
	}
	public void setIsonline(int isonline) {
		this.isonline = isonline;
	}
	public String getOnlinedevice1() {
		return onlinedevice1;
	}
	public void setOnlinedevice1(String onlinedevice1) {
		this.onlinedevice1 = onlinedevice1;
	}
	public String getOnlinedevice2() {
		return onlinedevice2;
	}
	public void setOnlinedevice2(String onlinedevice2) {
		this.onlinedevice2 = onlinedevice2;
	}
	public String getOnlinedevice3() {
		return onlinedevice3;
	}
	public void setOnlinedevice3(String onlinedevice3) {
		this.onlinedevice3 = onlinedevice3;
	}
	
}
