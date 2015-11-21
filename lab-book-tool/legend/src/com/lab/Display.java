package com.lab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab.model.Item;

public class Display {
	static List<String> arraylist=new ArrayList<String>();
	static String list="";static String liste="";static String userList="";static String userOptions="";static String versionList="";String query;
	static DBsearch search= new DBsearch();
	static int size;
	private static List<Item> itemList;
	public static void DisplayAllLists(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		System.out.println("Display.DisplayAllLists");
		String user=request.getParameter("user");
		userList="";
		versionList="";//burayý kontrol et
		DisplayDevicesList(request, response);
		DisplayLaptopList(request, response);
		System.out.println("Display.DisplayAllLists.requesting users lists");
		arraylist= search.getList("users",user);
		//TODO itemList = search.getItemList("users",user);
		size=arraylist.size();
		if (size != 0)
		System.out.println(arraylist.get(0));
		for (int listCounter=0;listCounter<size;listCounter++){
			userList=userList+ arraylist.get(listCounter).toString();
		}request.setAttribute("users", userList);
		System.out.println("Display.DisplayAllLists.requesting users options");userOptions= search.getUserOptions();request.setAttribute("status", userOptions);
		System.out.println("Display.DisplayAllLists.requesting product options");String productOptions = search.getProductOptions();request.setAttribute("product", productOptions);
		System.out.println("Display.DisplayAllLists.requesting new ID");
		int newID=0;
		boolean IDisUsed=true;
		while(IDisUsed==true){newID++;
		IDisUsed= search.getNewID(newID);
		System.out.println("Display.DisplayAllLists.servlet searched for new ID parameters"+newID+" "+IDisUsed);}
		System.out.println("Display.DisplayAllLists.found new ID, it is : "+newID+""+IDisUsed);
		request.setAttribute("id", String.valueOf(newID));
		
		System.out.println("Display.DisplayAllLists.requesting version lists");
		arraylist= search.getList("versions",user);
		//TODO itemList = search.getItemList("versions",user);
		size=arraylist.size();
		for (int listCounter=0;listCounter<size;listCounter++){
			versionList=versionList+ arraylist.get(listCounter).toString();
		}request.setAttribute("versions", versionList);
	}
	public static void DisplayDevicesList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		list="";
		

		System.out.println("Display.DisplayDevicesList");
		String user=request.getParameter("user");
		arraylist= search.getList("Devices",user);	
		itemList = search.getItemList("Devices",user);
		size=arraylist.size();

		for (int listCounter=0;listCounter<size;listCounter++){			
			//System.out.println("servlet.DisplayDevicesList.list counter & size : "+listCounter+", "+size);
			list= list+arraylist.get(listCounter).toString();}		
		request.setAttribute("lab", list);
				}
	public static void DisplayLaptopList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		liste="";
		System.out.println("Display.DisplayLaptopList");
		String user=request.getParameter("user");
		arraylist=search.getList("LAPTOPS",user);
		itemList = search.getItemList("LAPTOPS",user);
		size=arraylist.size();
		for (int listCounter=0;listCounter<size;listCounter++){
			liste= liste+arraylist.get(listCounter).toString();
		}request.setAttribute("laptop", liste);
		}
	
	public static void DisplayProductOptions(HttpServletRequest request, HttpServletResponse response,String product) 
	throws ServletException, IOException {
		System.out.println("Display.DisplayAllLists.requesting version options");
	
		String versionOptions = search.getVersionOptions(product);
		request.setAttribute("version", versionOptions);
		
	}	public static void DisplayProductOptions(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		System.out.println("Display.DisplayAllLists.requesting version options");
	
		String versionOptions = search.getVersionOptions();
		request.setAttribute("version", versionOptions);
		System.out.println("Display.versionoptions4admin :"+versionOptions );
		
	}
}
