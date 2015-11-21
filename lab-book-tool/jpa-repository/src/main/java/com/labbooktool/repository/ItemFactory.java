package com.labbooktool.repository;

import javax.inject.Inject;
import javax.inject.Named;

import com.labbooktool.repository.DeviceDaoImpl;
import com.labbooktool.repository.ItemRepository;
import com.labbooktool.repository.LabConstants;

@Named
public class ItemFactory {
	
	@Inject
	DeviceDaoImpl deviceDaoImpl;

	@Inject
	LaptopDaoImpl laptopDaoImpl;

	public ItemRepository getRepository(String tableName) {
		switch (tableName) {
		case LabConstants.DEVICES_TABLE:
			return deviceDaoImpl;
		case LabConstants.LAPTOPS_TABLE:
			return laptopDaoImpl;
			

		default:
			break;
		}
		return null;
	}

}
