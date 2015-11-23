package com.labbooktool.synchronize.model.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.labbooktool.ApplicationContextProvider;
import com.labbooktool.model.Item;
import com.labbooktool.repository.LabBookMongoRepository;

public class Consumer implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

	LabBookMongoRepository mongoRepository;

	private SynchronizeQueue queue;

	public Consumer(SynchronizeQueue queue) {
		this.queue = queue;
	}

	public void run() {
		mongoRepository = (LabBookMongoRepository) ApplicationContextProvider.getApplicationContext()
				.getBean(LabBookMongoRepository.class);
		List<Item> items;
		Item item;
		while (true) {
			try {
				items = new ArrayList<Item>();
				item = (Item) queue.dequeue();
				items.add(item);
				mongoRepository.insertItems(items);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

	}

}
