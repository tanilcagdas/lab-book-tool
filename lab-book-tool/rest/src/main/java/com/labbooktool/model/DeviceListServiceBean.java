package com.labbooktool.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeviceListServiceBean {

	private List<Item> deviceList;

	/**
	 * @return the deviceList
	 */
	public List<Item> getDeviceList() {
		return deviceList;
	}

	/**
	 * @param deviceList
	 *            the deviceList to set
	 */
	public void setDeviceList(List<Item> deviceList) {
		this.deviceList = deviceList;
	}

}
