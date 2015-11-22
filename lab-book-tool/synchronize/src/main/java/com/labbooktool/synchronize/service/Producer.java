package com.labbooktool.synchronize.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.labbooktool.model.Device;
import com.labbooktool.repository.LabConstants;
import com.labbooktool.server.AdminIF;
import com.labbooktool.server.ItemAdminImpl;
import com.labbooktool.synchronize.model.util.SynchronizeQueue;
import com.labbooktool.util.ApplicationContextProvider;
import com.labbooktool.util.DevicesConverter;

public class Producer implements Runnable {
	
	AdminIF admin;
	
	@PostConstruct
	public void init(){
		admin = (ItemAdminImpl) ApplicationContextProvider.getApplicationContext().getBean(ItemAdminImpl.class);
	}

	private SynchronizeQueue queue;

	public Producer(SynchronizeQueue queue) {
		this.queue = queue;
	}

	public void run() {
		List results = admin.search(LabConstants.DEVICES_TABLE);
		List<Device> devices = new ArrayList<Device>();
		DevicesConverter.convert(results,devices);
		
		for (Device device : devices) {
			queue.enqueue(device);
		}

	}

}
