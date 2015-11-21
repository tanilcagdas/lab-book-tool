package com.lab.controller;

public class IndexBean {
	
	private String loginusername;
	private String loginpassword;
	private boolean userLoggedIn = false;

	public String getLoginUserName() {
		return loginusername;
	}

	public void setLoginUserName(String user) {
		this.loginusername = user;
		if(user==null){
			setUserLoggedIn(false);
		}else{
			setUserLoggedIn(true);
		}
	}

	public String getLoginPassword() {
		return loginpassword;
	}

	public void setLoginPassword(String password) {
		this.loginpassword = password;
	}

	public boolean isUserLoggedIn() {
		return userLoggedIn;
	}

	public void setUserLoggedIn(boolean userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}

	public String login(){
		 
		return "loginSuccess";
		
	}
	public String logout(){
		 
		return "logoutSuccess";
		
	}
}
