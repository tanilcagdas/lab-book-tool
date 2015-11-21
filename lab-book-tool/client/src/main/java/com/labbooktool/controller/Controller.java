package com.labbooktool.controller;

import java.util.ArrayList;
import java.util.List;

import com.labbooktool.client.LabBookToolClient;
import com.labbooktool.model.Device;
import com.labbooktool.model.Laptop;

public class Controller {
	
	
	
	private List<Device> dataForDevices = new ArrayList<Device>();
	private List<Laptop> dataForLaptops = new ArrayList<Laptop>();
	private int selectedID;
	private String selectedTable=null;
	private String selectorUser=null;
	private String selectedStatus=null;
	
	private LabBookToolClient client = new LabBookToolClient();
	

	public List<Device> getDataForDevices() {
			dataForDevices= client.getDevices();
		return dataForDevices;
	}

	public void setDataForDevices(List<Device> dataForDevices) {
		this.dataForDevices = dataForDevices;
	}
	
	public List<Laptop> getDataForLaptops() {
		if(dataForLaptops.size()==0){
			dataForLaptops= client.getLaptops();
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
	

	
	public String changeDeviceStatus() throws ClassNotFoundException{
		System.out.println(selectedID);
		System.out.println(selectedTable);
		System.out.println(selectorUser);
		if(selectedStatus.equalsIgnoreCase(LabConstants.AVAILABLE)){
			client.reserve(selectorUser, selectedID, selectedTable);
		}else{
			client.release(selectorUser, selectedID, selectedTable);
		}
		return "success";
	}

}
