package com.labbooktool.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Laptop extends Item{
	
	private String password;
	private String os;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String oS) {
		this.os = oS;
	}

}
