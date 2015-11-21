package com.labbooktool.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
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
