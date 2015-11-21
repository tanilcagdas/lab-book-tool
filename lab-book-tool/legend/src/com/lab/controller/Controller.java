package com.lab.controller;

import java.util.ArrayList;
import java.util.List;

import com.lab.model.Device;
import com.lab.model.Laptop;
import com.lab.server.AdminIF;
import com.lab.server.ItemAdminImpl;

public class Controller {
	
	
	
	private List<Device> dataForDevices = new ArrayList<Device>();
	private List<Laptop> dataForLaptops = new ArrayList<Laptop>();
	private int selectedID;
	private String selectedTable=null;
	private String selectorUser=null;
	private String selectedStatus=null;
	
	private AdminIF admin = null;
	

	public List<Device> getDataForDevices() {
		admin = new ItemAdminImpl();
			dataForDevices= admin.search(LabConstants.DEVICES_TABLE);
		return dataForDevices;
	}

	public void setDataForDevices(List<Device> dataForDevices) {
		this.dataForDevices = dataForDevices;
	}
	
	public List<Laptop> getDataForLaptops() {
		admin = new ItemAdminImpl();
		if(dataForLaptops.size()==0){
			dataForLaptops= admin.search(LabConstants.LAPTOPS_TABLE);
		}
		
		return dataForLaptops;
	}

	public void setDataForLaptops(List<Laptop> dataForLaptops) {
		this.dataForLaptops = dataForLaptops;
	}

	public int getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(int selectedID) {
		this.selectedID = selectedID;
	}

	public String getSelectedTable() {
		return selectedTable;
	}

	public void setSelectedTable(String selectedTable) {
		this.selectedTable = selectedTable;
	}

	public String getSelectorUser() {
		return selectorUser;
	}

	public void setSelectorUser(String selectorUser) {
		this.selectorUser = selectorUser;
	}

	public String getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public  List search(){
		
		return null;}
	
	public  List getAllDevices(){
		admin = new ItemAdminImpl();
		List itemList = admin.search();
		
		return itemList;}
	
	public String changeDeviceStatus() throws ClassNotFoundException{
		System.out.println(selectedID);
		System.out.println(selectedTable);
		System.out.println(selectorUser);
		admin = new ItemAdminImpl();
		if(selectedStatus.equalsIgnoreCase(LabConstants.AVAILABLE)){
			admin.reserve(selectorUser, selectedID, selectedTable);
		}else{
			admin.release(selectorUser, selectedID, selectedTable);
		}
		return "success";
	}

}
