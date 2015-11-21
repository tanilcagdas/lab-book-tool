package com.lab;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lab.controller.LabConstants;
import com.lab.model.Device;
import com.lab.model.Item;
import com.lab.model.Laptop;

import db.DBImpl;
import db.DataAccess;
import db.HibernateImpl;
public class DBsearch {
	private static final String SELECT = "SELECT * FROM ";
	private DBImpl dbImpl = DBImpl.getInstance();
	static String command;
	static String link;
	static String link2;
	static String link3;
	static String query;
	String name="";
	static String Email="";
	static String redirectPage;
	List<String> productOptionsList=new ArrayList<String>();
	private static ResultSet resultSet;
	static DataAccess dataAccess=null;
	
	public List<String> getList(String tableName,String user){
		List<String> list=new ArrayList<String>();
		System.out.println("DBsearch.list");
		String order="";
		if(tableName.equalsIgnoreCase("devices")){order=" order by product, name";}
		query=SELECT+tableName+order;
		list=prepareList(tableName, user,query);
		//List<Item> itemList = dbImpl.prepareList(tableName, user,query);
		//List<Item> itemList = HibernateImpl.select(LabConstants.DEVICES_TABLE);
		System.out.println("DBsearch.list.back from preparelist");
	return list;
}
	
	public List<Item> getItemList(String tableName,String user){
		System.out.println("DBsearch.list");
		String order="";
		if(tableName.equalsIgnoreCase("devices")){order=" order by product, name";}
		query=SELECT+tableName+order;
		List<Item> itemList = dbImpl.prepareList(tableName, user,query);
		//List<Item> itemList = HibernateImpl.select(LabConstants.DEVICES_TABLE);
		System.out.println("DBsearch.list.back from preparelist");
	return itemList;
}

	public String getUserOptions() {
		String tableName="users";
		String userOptions="";
		try{
			query = SELECT+tableName;
			dataAccess= DataAccess.getInstance();
			resultSet = dataAccess.executeQuery(query);
	

		while(resultSet.next()){String Name= resultSet.getString("name");	
			userOptions=userOptions+"<option>"+Name+"</option>";
			}
		resultSet.close();
		}catch(SQLException e){
			System.out.println("An exception");
			e.printStackTrace();
			
	}
		return userOptions;
	}
	
	public String getVersionOptions(String product) {
		String tableName="versions";
		String versionOptions="";
		try{query=SELECT+tableName+" where product = '"+product+"'";
			dataAccess= DataAccess.getInstance();
			resultSet = dataAccess.executeQuery(query);
			

		while(resultSet.next()){
		
			String Version= resultSet.getString("version");
		
				
			
			versionOptions=versionOptions+"<option>"+Version+"</option>";
			}
		resultSet.close();
		}catch(SQLException e){
			System.out.println("An exception");
			e.printStackTrace();
			
	}
		return versionOptions;
	}
	
	public String getVersionOptions() {
		String tableName="versions";
		String versionOptions="";
		try{query=SELECT+tableName;
			dataAccess= DataAccess.getInstance();
			resultSet = dataAccess.executeQuery(query);
			

		while(resultSet.next()){
		
			String Version= resultSet.getString("version");
		
				
			
			versionOptions=versionOptions+"<option>"+Version+"</option>";
			}
		resultSet.close();
		}catch(SQLException e){
			System.out.println("An exception");
			e.printStackTrace();
			
	}
		return versionOptions;
	}
	
	public String getProductOptions() {
		String tableName = "versions";
		String productOptions = "";
		try {
			query = SELECT + tableName;
			dataAccess = DataAccess.getInstance();
			resultSet = dataAccess.executeQuery(query);

			while (resultSet.next()) {
				String Product = resultSet.getString("product");
				productOptionsList.add(Product);
			}
			int size = productOptionsList.size();
			String compareProduct = "null";
			boolean productExists;
			for (int listCounter = 0; listCounter < size; listCounter++) {
				productExists = false;
				compareProduct = productOptionsList.get(listCounter).toString();

				for (int compareCounter = 0; compareCounter < listCounter; compareCounter++) {
					if (compareProduct.equalsIgnoreCase(productOptionsList.get(
							compareCounter).toString())) {
						productExists = true;
					}
				}
				if (productExists == false) {
					productOptions = productOptions + "<option>"
							+ compareProduct + "</option>";
				}
			}
			resultSet.close();
		} catch (SQLException e) {
			System.out.println("An exception");
			e.printStackTrace();
		}
		return productOptions;
	}
	
	public static List<String> search(String tableName,String user, String name){
		List<String> searchlist=new ArrayList<String>();
		System.out.println("DBsearch.search parameters are = "+tableName+user+name);
		if (tableName.equalsIgnoreCase("Devices")){query=SELECT+tableName+" WHERE NAME LIKE '%"+ name+"%' or VERSION LIKE '%"+ name+"%' or STATUS LIKE '%"+ name+"%' or PRODUCT LIKE '%"+ name.toUpperCase()+"%' or PRODUCT LIKE '%"+ name+"%'";}
		else if (tableName.equalsIgnoreCase("laptops")){query=SELECT+tableName+" WHERE NAME LIKE '%"+ name+"%' or OS LIKE '%"+ name+"%'or OS LIKE '%"+ name.toUpperCase()+"%' or STATUS LIKE '%"+ name+"%' ";}
		searchlist=prepareList(tableName, user,query);
		System.out.println("DBsearch.search.searchlist: "+searchlist);
		return searchlist;
}
		
	public List<String> prepareListID(String tableName, String user,
			String query, String id) {
		List<String> list=new ArrayList<String>();
		
			
		System.out.println("preparelistID");
		query=SELECT+tableName+" WHERE ID = "+ id;
		list=prepareList(tableName, user, query);
		
		return list;
	}
	
	public static List<String> prepareList(String tableName, String user,
			String query) {
		List<String> list = new ArrayList<String>();
		ArrayList<Item> itemList = new ArrayList<Item>();
		Device device = null;
		Laptop laptop = null;
		
		if (tableName.equalsIgnoreCase("Devices")) {
			redirectPage = "Devices";
		} else if (tableName.equalsIgnoreCase("Laptops")) {
			redirectPage = "laptops";
		}
		int Id=0;
		String Name, Ip, Version, Status, Product, Password, Os;
		Name = Ip = Version = Status = Product = Password = Os = "";
		try {

			
			System.out.println("DBsearch.prepareList.query is : " + query);
			try {
				dataAccess= DataAccess.getInstance();
				resultSet = dataAccess.executeQuery(query);

			} catch (Exception e) {
				System.out.println("table " + tableName
						+ "may not be created. Creating " + tableName);
				DBadd dbadd = new DBadd();
				try {
					if (tableName.equalsIgnoreCase("devices"))
						dbadd.execute(
								"admin",
								"create table "
										+ tableName
										+ "(id int, name varchar(16), ip varchar(15), version varchar(12), status varchar(15), product varchar(30))");
					else if (tableName.equalsIgnoreCase("laptops"))
						dbadd.execute(
								"admin",
								"create table "
										+ tableName
										+ " (id int, name varchar(16), password varchar(15), os varchar(12), status varchar(15))");
					else if (tableName.equalsIgnoreCase("users"))
						dbadd.execute(
								"admin",
								"create table "
										+ tableName
										+ " (id int, name varchar(16), password varchar(15),email varchar(30))");
					else if (tableName.equalsIgnoreCase("versions"))
						dbadd.execute(
								"admin",
								"create table "
										+ tableName
										+ " (id int, version varchar(12), product varchar(30))");
					resultSet = dataAccess.executeQuery(query);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			list.clear();

			while (resultSet.next()) {
				try {
					Id = (int) resultSet.getDouble("id");
				} catch (Exception e) {
				}
				try {
					Name = resultSet.getString("name");
				} catch (Exception e) {
				}
				try {
					Ip = resultSet.getString("ip");
				} catch (Exception e) {
				}
				try {
					Version = resultSet.getString("version");
				} catch (Exception e) {
				}
				try {
					Status = resultSet.getString("status");
				} catch (Exception e) {
				}
				try {
					Product = resultSet.getString("product");
				} catch (Exception e) {
				}
				try {
					Password = resultSet.getString("password");
				} catch (Exception e) {
				}
				try {
					Os = resultSet.getString("os");
				} catch (Exception e) {
				}
				try {
					Email = resultSet.getString("email");
				} catch (Exception e) {
				}
				String button1 = " style=\"background-color: blue; border-color: blue; color: white;  \""; // old
																											// color
																											// #3F547F;
				String button2 = " style=\"background-color: red; border-color: red; color: white;  \"";
				String button3 = " style=\"background-color: green; border-color: green; color: white;  \"";
				if (Status.equalsIgnoreCase(user)) {
					command = "release" + button1;
				} else if (Status.equalsIgnoreCase("available")) {
					command = "reserve" + button3;
				} else if (user.equalsIgnoreCase("admin")
						& !Status.equalsIgnoreCase("available")) {
					command = "release" + button2;
				} else {
					command = "reserved" + button2;
				}

				link3 = "<a href=servlet?redirectPage=" + redirectPage + "&ID="
						+ Id + "&user=" + Name + "&tableName=" + tableName
						+ "&action=resetPassword> Reset Password </a>";
				link = "<form name =\"toLaptops\" action=servlet method=post>"
						+ "<input  type=hidden name=redirectPage  value="
						+ redirectPage + ">"
						+ "<input  type=hidden name=ID  value=" + Id + ">"
						+ "<input  type=hidden name=user  value=" + user + ">"
						+ "<input  type=hidden name=tableName  value="
						+ tableName + ">"
						+ "<input  type=hidden name=action  value=" + command
						+ ">" + "<input type=submit name=submit  value="
						+ command.toUpperCase() + "></input></form>";
				if (query.equalsIgnoreCase("select email from users")) {
					list.add(Email);
				} else if (tableName.equalsIgnoreCase("Devices")) {
					if (tableName.equalsIgnoreCase("Devices")){
						device = new Device();
						device.setId(Id);
						device.setName(Name);
						device.setIp(Ip);
						device.setProduct(Product);
						device.setStatus(Status);
						device.setVersion(Version);
						itemList.add(device);
					}
					link2 = "<a href=servlet?redirectPage=" + redirectPage
							+ "&ID=" + Id + "&user=" + user + "&tableName="
							+ tableName + "&action=updateStatus>" + Version
							+ "</a>";

					if (user.equalsIgnoreCase("admin")) {
						if (!Status.equalsIgnoreCase("available")) {
							command = "release" + button2;
						} else {
							link = link2;
						}
					}

					list.add("<tr><td>" + link + "</td><td> " + Id
							+ "   </td><td> " + Name + "   </td><td> " + Ip
							+ "  </td><td>  " + link2 + "  </td><td>  "
							+ Status + "  </td><td>  " + Product
							+ "  </td></tr>");

				} else if (tableName.equalsIgnoreCase("LAPTOPS")) {
					if (tableName.equalsIgnoreCase("LAPTOPS")){
						laptop = new Laptop();
						laptop.setId(Id);
						laptop.setName(Name);
						laptop.setOs(Ip);
						laptop.setPassword(Product);
						laptop.setStatus(Status);
						itemList.add(device);
					}
					link2 = "<a href=servlet?redirectPage=" + tableName
							+ "&ID=" + Id + "&user=" + user + "&tableName="
							+ tableName + "&action=updateStatus>" + Os + "</a>";
					// if(user.equalsIgnoreCase("admin")){link=link2;}
					if (user.equalsIgnoreCase("admin")) {
						if (!Status.equalsIgnoreCase("available")) {
							command = "release" + button2;
						} else {
							link = link2;
						}
					}
					list.add("<tr><td>" + link + "</td><td>  " + Id
							+ "  </td><td>  " + Name + "   </td><td>  "
							+ Password + "  </td><td>  " + link2
							+ "  </td><td>  " + Status + "</td></tr>");
				} else if (tableName.equalsIgnoreCase("USERS")) {
					list.add("<tr><td>  "
							+ Id
							+ "   </td><td>   "
							+ Name
							+ "   </td><td>   "
							+ link3
							+ "</td><td> "
							+ "<form name=adminemailchangeform action= servlet 	method=post>"
							+ " <input type=text name=email size=25 value="
							+ Email
							+ ">"
							+ "<input type=hidden name=action  value=adminemailchange>"
							+ "<input type=hidden name=username  value=" + Name
							+ ">"
							+ "<input type=hidden name=user  value=admin>"
							+ "<input type=submit name=submit  value=change>"
							+ "</form> </td></tr>");
				} else if (tableName.equalsIgnoreCase("VERSIONS")) {
					list.add("<tr><td>" + Id + "</td><td>  " + Version
							+ "   </td><td>   " + Product + "   </td> </tr>");

				}
			}

			resultSet.close();
		} catch (SQLException e) {
			System.out.println("An exception");
			e.printStackTrace();

		}
		// System.out.println(list);
		return list;
	}
	
	public String prepareItem(String tableName,String user, String query){
		String Item="";
		if (tableName.equalsIgnoreCase("Devices")){redirectPage="Devices";}
			else if(tableName.equalsIgnoreCase("Laptops")){redirectPage="Laptops";}
		String Id,Name,Ip,Version,Status , Product,Password,Os;
		Id=Name=Ip=Version=Status = Product=Password=Os="";
		System.out.println("DBsearch.prepareItem");
		try{
		System.out.println("DBsearch.prapareItem.query : "+query);	
		dataAccess= DataAccess.getInstance();
		resultSet = dataAccess.executeQuery(query);
		
		
		
			
			while(resultSet.next()){
				try{ Id=resultSet.getString("id");}catch (Exception e) {}
				try{ Name= resultSet.getString("name");}catch (Exception e) {}
				try{ Ip=resultSet.getString("ip");}catch (Exception e) {}
				try{ Version= resultSet.getString("version");}catch (Exception e) {}
				try{ Status=resultSet.getString("status");}catch (Exception e) {}
				try{ Product= resultSet.getString("product");}catch (Exception e) {}
				try{ Password= resultSet.getString("password");}catch (Exception e) {}
				try{ Os= resultSet.getString("os");}catch (Exception e) {}
				try{ Email= resultSet.getString("email");}catch (Exception e) {}
			Item=Id+Name+Ip+Version+Status + Product+Password+Os+Email;
			
				
				}
				
					System.out.println("DBsearch.prepareItem.item : "+Item);		
					
			
			resultSet.close();}	
		catch(SQLException e){
			System.out.println("An exception");
			e.printStackTrace();}
		return Item;
	}
		
	public boolean getNewID(int newID) {
	
		boolean IDisUsed=false;
		
		List<String> tableList=new ArrayList<String>();
		tableList.add("devices");
		tableList.add("laptops");
		tableList.add("versions" );
		tableList.add("users" );	
		Iterator<String> i=tableList.iterator();
				while(i.hasNext()){
		String tableName="";
		tableName=(i.next());
			
			try{
				query=SELECT+tableName;
				dataAccess= DataAccess.getInstance();
				resultSet = dataAccess.executeQuery(query);
				
			while(resultSet.next()){
			
				String Id=resultSet.getString("id");
				
				if(Integer.parseInt(Id)==newID){IDisUsed=true; }
			
			}		
				
			resultSet.close();
				
			
			}catch(SQLException e){
				System.out.println("An exception");
				e.printStackTrace();
			}	
		}
		
		return IDisUsed;
	}
	
}
