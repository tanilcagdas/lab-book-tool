package com.labbooktool.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import com.labbooktool.model.Device;
import com.labbooktool.model.Item;
import com.labbooktool.model.Laptop;
import com.labbooktool.repository.LabConstants;

@Named("ItemServiceDummyImpl")
@Profile("dummy")
public class ItemServiceDummyImpl implements AdminIF {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemServiceDummyImpl.class);
	
	private static Map<Integer, Item> map = new HashMap<>();

	private static Integer NEXT_ID = 0;
	
	public ItemServiceDummyImpl() {
		Item i1 = new Device();
		i1.setId(getNextId());
		i1.setName("d1");
		i1.setStatus(LabConstants.AVAILABLE);
		map.put(i1.getId(), i1);
		
		Item i2 = new Device();
		i2.setId(getNextId());
		i2.setName("d2");
		i2.setStatus(LabConstants.AVAILABLE);
		map.put(i2.getId(), i2);
		
		Item i3 = new Laptop();
		i3.setId(getNextId());
		i3.setName("l3");
		i3.setStatus(LabConstants.AVAILABLE);
		map.put(i3.getId(), i3);
		
		Item i4 = new Laptop();
		i4.setId(getNextId());
		i4.setName("l4");
		i4.setStatus(LabConstants.AVAILABLE);
		map.put(i4.getId(), i4);
	}



	@Override
	public List<? extends Item> search(String tableName) {
		try {
			List<Item> results = new ArrayList<>();
			for (Map.Entry<Integer, Item> item : map.entrySet()) {
				if(LabConstants.DEVICES_TABLE.equals(tableName) && item.getValue() instanceof Device){
					results.add(item.getValue());
				}else if(LabConstants.LAPTOPS_TABLE.equals(tableName) && item.getValue() instanceof Laptop){
					results.add( item.getValue());
				}
			}
			return results;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void add(Object obj) {
		
		map.put(getNextId(), (Item) obj);

	}

	private static Integer getNextId() {
		NEXT_ID ++;
		return NEXT_ID ;
	}

	@Override
	public void delete(Object obj) {
		//TODO

	}

	@Override
	public List<Item> findAll() {
		List<Item> results = new ArrayList<>();
		for (Map.Entry<Integer, Item> item : map.entrySet()) {
				results.add(item.getValue());
		}
		return results;
	}

	@Override
	public void reserve(String user, int id, String tableName) throws ClassNotFoundException {
		System.out.println("i will reserve item with id: " + id + " to: " + user);
		updateStatus(user, id, tableName, "reserve");

	}

	@Override
	public void release(String user, int id, String tableName) throws ClassNotFoundException {
		System.out.println("i will release item with id: " + id + " to: " + user);
		updateStatus(user, id, tableName, "release");

	}

	public void updateStatus(String user, int id, String tableName, String command) throws ClassNotFoundException {
		Item item = map.get(id);
		if(command.equals("release")){
			item.setStatus(LabConstants.AVAILABLE);
		}else{
			item.setStatus(user);
		}
		map.remove(id);
		map.put(id, item);
	}
}
