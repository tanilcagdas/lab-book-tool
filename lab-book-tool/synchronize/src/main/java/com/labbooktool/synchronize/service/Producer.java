package com.labbooktool.synchronize.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.labbooktool.model.Device;
import com.labbooktool.repository.LabConstants;
import com.labbooktool.server.AdminIF;
import com.labbooktool.synchronize.model.util.SynchronizeQueue;
import com.labbooktool.util.DevicesConverter;

@Component
public class Producer implements Runnable {
	
	@Autowired
	AdminIF admin;

	private SynchronizeQueue queue;

	public Producer(SynchronizeQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		List results = admin.search(LabConstants.DEVICES_TABLE);
		List<Device> devices = new ArrayList<>();
		DevicesConverter.convert(results,devices);
		
		for (Device device : devices) {
			queue.enqueue(device);
		}

	}

}
