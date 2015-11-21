package com.labbooktool.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeviceListServiceBean {

	private List<Device> deviceList;

	/**
	 * @return the deviceList
	 */
	public List<Device> getDeviceList() {
		return deviceList;
	}

	/**
	 * @param deviceList
	 *            the deviceList to set
	 */
	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}

}
