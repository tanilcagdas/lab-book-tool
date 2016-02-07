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
		TableEnum tableEnum = TableEnum.valueOf(tableName);
		switch (tableEnum) {
		case DEVICES:
			return deviceDaoImpl;
		case LAPTOPS:
			return laptopDaoImpl;
			

		default:
			break;
		}
		return null;
	}
	
	private enum TableEnum{
		DEVICES,LAPTOPS;
		
		public TableEnum fromString(String tableName){
			return valueOf(tableName);
		}
	}

}
