package com.labbooktool.synchronize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labbooktool.repository.LabBookMongoRepository;
import com.labbooktool.server.AdminIF;
import com.labbooktool.synchronize.model.util.Consumer;
import com.labbooktool.synchronize.model.util.Producer;
import com.labbooktool.synchronize.model.util.SynchronizeQueue;

@Service
public class SynchronizeService {
	
	@Autowired
	AdminIF admin;
	
	@Autowired
	LabBookMongoRepository mongoRepository;
	
	public void fullSyncDBtoMongo(){
		SynchronizeQueue queue = new SynchronizeQueue();
		
		Producer producer = new Producer(queue);
		Thread producerThread = new Thread(producer); 
		producerThread.start();

		Consumer consumer = new Consumer(queue);
		Thread consumerThread = new Thread(consumer); 
		consumerThread.start();
		
		
	}

}
