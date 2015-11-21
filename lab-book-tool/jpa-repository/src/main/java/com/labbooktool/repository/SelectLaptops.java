package com.labbooktool.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.labbooktool.model.Item;
import com.labbooktool.model.Laptop;

public class SelectLaptops {
	static String newquery = "from Laptop LAPTOPS"; 
	
	static List<Item> laptopsList = new ArrayList<Item>(); 

	public static List<Item> select(Session session) {
		laptopsList.clear();
		// Create Select Clause HQL
		String SQL_QUERY = newquery;
		Query query = session.createQuery(SQL_QUERY);
		for (Iterator it = query.iterate(); it.hasNext();) {
			Laptop lap = (Laptop) it.next();
			System.out.println(lap.getName());
			System.out.println(lap.getId());
			System.out.println(lap.getOs());
			System.out.println(lap.getPassword());
			System.out.println(lap.getStatus());
			laptopsList.add(lap);
		}
	return laptopsList;
}

}
