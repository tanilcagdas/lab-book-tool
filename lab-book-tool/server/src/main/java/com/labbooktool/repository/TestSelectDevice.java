package com.labbooktool.repository;

import java.util.List;

import com.labbooktool.model.Item;
import com.labbooktool.server.AdminIF;
import com.labbooktool.server.ItemAdminImpl;

public class TestSelectDevice {
	
	public static void main(String[] args) {
		//DBsearch db = new DBsearch();
		AdminIF admin = new ItemAdminImpl();
		try {
			List<Item> itemList = admin.search(LabConstants.DEVICES_TABLE);
			System.out.println(itemList.get(0).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
