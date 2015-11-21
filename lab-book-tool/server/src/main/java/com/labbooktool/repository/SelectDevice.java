package com.labbooktool.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.labbooktool.model.Device;
import com.labbooktool.model.Item;

public class SelectDevice {
	static long longint= 15;
	static String oldquery = "Select insurance.lngInsuranceId,insurance.insuranceName,"
		+ "insurance.investementAmount,insurance.investementDate from Insurance insurance"; 
	static String newquery1 = "Select DEVICES.id,DEVICES.name,"
		+ "DEVICES.status,DEVICES.ip,DEVICES.version,DEVICES.product from Device DEVICES"; 
	static String newquery = "from Device DEVICES"; 
	
	static List<Item> deviceList = new ArrayList<Item>(); 

	public static List<Item> select(Session session) {
		deviceList.clear();
			// Create Select Clause HQL
			String SQL_QUERY = newquery;
			Query query = session.createQuery(SQL_QUERY);
			for (Iterator it = query.iterate(); it.hasNext();) {
				Device dvc = (Device) it.next();
				System.out.println(dvc.getName());
				System.out.println(dvc.getId());
				System.out.println(dvc.getIp());
				System.out.println(dvc.getProduct());
				System.out.println(dvc.getVersion());
				deviceList.add(dvc);
			}
		return deviceList;
	}

}
