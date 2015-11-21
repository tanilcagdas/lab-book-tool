package com.labbooktool.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Device extends Item{
	
	
		 
	private String ip;	
	private String version;
	
	private String product;
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	

}
