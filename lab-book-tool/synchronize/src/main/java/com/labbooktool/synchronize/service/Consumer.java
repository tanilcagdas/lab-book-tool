package com.labbooktool.synchronize.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.labbooktool.model.Device;
import com.labbooktool.repository.LabBookMongoRepository;
import com.labbooktool.synchronize.model.util.SynchronizeQueue;

public class Consumer implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	LabBookMongoRepository mongoRepository;

	private SynchronizeQueue queue;

	public Consumer(SynchronizeQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				
			List<Device> devices = new ArrayList<>();
			Device device = (Device) queue.dequeue();
			devices.add(device);
			mongoRepository.insertDevices(devices);
			}
			 catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

	}

}
