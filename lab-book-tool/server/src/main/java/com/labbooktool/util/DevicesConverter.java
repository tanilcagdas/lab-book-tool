package com.labbooktool.util;

import java.util.List;

import com.labbooktool.model.Device;
import com.labbooktool.model.Item;

public class DevicesConverter {

	public static void convert(List<Item> results, List<Device> devices) {
		for (Item item : results) {
			Device device = new Device();
			device = (Device) item;
			devices.add(device);
			
		}
		
	}

}
