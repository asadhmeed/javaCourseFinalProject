package com.asad.couponesController;

public class LogIn {

	private Long userId;
	private String userName;
	private String password;
	
	
	
	public LogIn() {
		super();
	}

	public LogIn(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "LogIn [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}
	
	
}
