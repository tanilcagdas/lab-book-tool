package db;

import java.util.List;

import com.lab.DBsearch;
import com.lab.controller.LabConstants;
import com.lab.model.Item;
import com.lab.server.AdminIF;
import com.lab.server.ItemAdminImpl;


public class TestSelectDevice {
	
	public static void main(String[] args) {
		DBsearch db = new DBsearch();
		AdminIF admin = new ItemAdminImpl();
		try {
			List<Item> itemList = admin.search(LabConstants.DEVICES_TABLE);
			System.out.println(itemList.get(0).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
