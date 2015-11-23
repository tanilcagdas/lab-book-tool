package com.labbooktool.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LaptopListServiceBean {

	private List<Item> laptopList;

	public List<Item> getLaptopList() {
		return laptopList;
	}

	public void setLaptopList(List<Item> laptopList) {
		this.laptopList = laptopList;
	}

	

}
