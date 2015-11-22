package com.labbooktool.synchronize.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.labbooktool.model.Device;
import com.labbooktool.repository.LabBookMongoRepository;
import com.labbooktool.synchronize.model.util.SynchronizeQueue;
import com.labbooktool.util.ApplicationContextProvider;

public class Consumer implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	LabBookMongoRepository mongoRepository;
	
	@PostConstruct
	public void init(){
		mongoRepository = (LabBookMongoRepository) ApplicationContextProvider.getApplicationContext().getBean(LabBookMongoRepository.class);
	}

	private SynchronizeQueue queue;

	public Consumer(SynchronizeQueue queue) {
		this.queue = queue;
	}

	public void run() {
		while (true) {
			try {
				
			List<Device> devices = new ArrayList<Device> ();
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
