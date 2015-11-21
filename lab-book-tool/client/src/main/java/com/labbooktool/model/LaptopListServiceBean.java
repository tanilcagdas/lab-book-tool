package com.labbooktool.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LaptopListServiceBean {

	private List<Laptop> laptopList;

	public List<Laptop> getLaptopList() {
		return laptopList;
	}

	public void setLaptopList(List<Laptop> laptopList) {
		this.laptopList = laptopList;
	}

	

}
