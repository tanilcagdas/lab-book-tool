package com.labbooktool.synchronize.model.util;

import java.util.List;

import com.labbooktool.ApplicationContextProvider;
import com.labbooktool.model.Item;
import com.labbooktool.server.AdminIF;
import com.labbooktool.server.ItemAdminImpl;

public class Producer implements Runnable {
	
	AdminIF admin;
	
	private SynchronizeQueue queue;

	public Producer(SynchronizeQueue queue) {
		this.queue = queue;
	}

	public void run() {
		admin = (ItemAdminImpl) ApplicationContextProvider.getApplicationContext().getBean(ItemAdminImpl.class);
		List<Item> results = admin.findAll();
		for (Item item : results) {
			queue.enqueue(item);
		}
		

	}

}
