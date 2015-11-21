package com.lab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class Switch {
	@SuppressWarnings("unchecked")
	static List searchList=new ArrayList();
	@SuppressWarnings("unchecked")
	static List totalList=new ArrayList();
	static String list="";static String liste="";static String userList="";
	static String userOptions="";static String versionList="";static String query;
	static String email="";
	static String message="";
	static String newCollumn="";
	static String type="";
	static DBsearch search= new DBsearch();
	static DBupdate dbu=new DBupdate();
	static Mail mail=new Mail();
	private static String itemList;
public static String switcher(HttpServletRequest request, HttpServletResponse response,
		String actionparameter,String user,String redirectPage,String tableName) 
			throws ServletException, IOException, ClassNotFoundException {	
ActionParameter	act=ActionParameter.valueOf(actionparameter);
System.out.println("Switch.switcher act is : "+act);
	
	switch(act){
	
	case LOGIN:
		System.out.println("switch.case.login");
		boolean authorized = false;
		String pwd=request.getParameter("pwd");
		if (user.equals("admin")&pwd.equals("Cet89Kbm")){
			Display.DisplayAllLists(request, response);
			Display.DisplayProductOptions(request, response);
			redirectPage="admin";
			    			
		}else{
		DBuser dbus=new DBuser();
		try { authorized = dbus.login(user,pwd);} catch (Exception e) {e.printStackTrace();}
		request.setAttribute("asd", user);
		message="Welcome "+user;
		if(authorized==true){
			
			 if(user.equalsIgnoreCase("admin")){
				redirectPage="admin";
				Display.DisplayAllLists(request, response);
				Display.DisplayProductOptions(request, response);
				System.out.println("Switch.LOGIN.Back from DisplayDevicesList");
				}
			else{
				
				redirectPage="Devices";
				Display.DisplayDevicesList(request, response);
				}
		}
		
		else{redirectPage="index";message="	The username or password you entered is incorrect ";}
		request.setAttribute("message", message);
		}
		break;
		
	case changePassword:
		
		System.out.println("switch.case.changePassword");
		pwd=request.getParameter("pwd");
		String newPwd=request.getParameter("newPwd");
		ChangePassword cp=new ChangePassword();
		try { 
			if(cp.change(user,pwd,newPwd)){ 
				message = "Password succesfully changed";
				if(user.equalsIgnoreCase("admin")){redirectPage="admin";Display.DisplayAllLists(request, response);Display.DisplayProductOptions(request, response);}
				else{redirectPage="Devices";Display.DisplayDevicesList(request, response);}
			}
			else{
				message=" Couldn't change password";
				redirectPage="changePassword";}
		} catch (Exception e) {e.printStackTrace();}
		
		request.setAttribute("message", message);
		break;
	case toChangePassword:
		System.out.println("switch.case.login.toChangePassword");
		query="select email from users where name = '"+user+"'";
		email=search.prepareItem("Devices", user, query);
		request.setAttribute("email", email);
		System.out.println(email);
		redirectPage="changePassword";
		break;
	
	
	case toLAPTOPs:
		System.out.println("switch.case.toLaptops");
		Display.DisplayLaptopList(request, response);
		redirectPage="laptops";
		break;
	case toDEVICEs:
		if (user.equalsIgnoreCase("admin")) {
			Display.DisplayAllLists(request, response);
			Display.DisplayProductOptions(request, response);
			redirectPage="admin";
			System.out.println("Switch.toDevices but the user is admin");
			
		} else {

		
		System.out.println("Switch.toDevices");
		Display.DisplayDevicesList(request, response);
		redirectPage="Devices";
		}
		break;
		
	case REMOVE:
		System.out.println("switch.case.remove");
		String index = request.getParameter("INDEX");
		 tableName = request.getParameter("tableName");
		 redirectPage=request.getParameter("redirectPage");
		
		System.out.println(redirectPage+" "+tableName+" "+user+" " +index);
		DBdelete dbd=new DBdelete();
		try {dbd.delete(tableName,index);} catch (Exception e) {e.printStackTrace();}
		Display.DisplayAllLists(request, response);Display.DisplayProductOptions(request, response);
		break;
		
	case ADD:
		System.out.println("switch.case."+actionparameter);
		 tableName=request.getParameter("tableName");
		 redirectPage=request.getParameter("redirectPage");
		System.out.println(user+" servlet will add element to "+tableName);
		int id=Integer.parseInt(request.getParameter("id"));
		
		String name=request.getParameter("name");
		String status=request.getParameter("status");
		DBadd db=new DBadd();
		if(search.getNewID(id)){
			Display.DisplayAllLists(request, response);Display.DisplayProductOptions(request, response);redirectPage="admin";
			message="ID is used, Element could not be added"; request.setAttribute("message", message);
		}/*ID kullanildigi durumda eklemeden cikar. */
		
		else{
		if (tableName.equalsIgnoreCase("Devices")){
			
			String ip=request.getParameter("ip");
			String version=request.getParameter("version");
			String product=request.getParameter("product");
			if ((name.equalsIgnoreCase("")) || (ip.equalsIgnoreCase(""))||(version.equalsIgnoreCase(""))||(status.equalsIgnoreCase(""))||(product.equalsIgnoreCase(""))) {
				message="one or more textboxes seem to be blank..";System.out.println("Switch.ADD.Devices.one or more fillings are blank");
			} else {				
				try {db.injectDevice(tableName,id,name, ip,version,status,product) ;} catch (Exception e) {e.printStackTrace();}
					System.out.println("added device from servlet redirecting page");
						System.out.println(list);
						}
		}else if(tableName.equalsIgnoreCase("LAPTOPS")){
			String password=request.getParameter("password");
			String os=request.getParameter("os");
			if ((name.equalsIgnoreCase("")) || (password.equalsIgnoreCase(""))||(os.equalsIgnoreCase(""))||(status.equalsIgnoreCase(""))) {
				message="one or more textboxes seem to be blank..";System.out.println("Switch.ADD.Laptops.one or more fillings are blank");
			} else {
				try {db.injectLaptop(tableName,id,name, password,os,status) ;} catch (Exception e) {e.printStackTrace();}
					System.out.println("added laptop from servlet redirecting page");
						System.out.println(liste);
						}
		}else if(tableName.equalsIgnoreCase("USERS")){
			String password=request.getParameter("password");
			email=request.getParameter("email");
			if ((name.equalsIgnoreCase("")) || (password.equalsIgnoreCase(""))) {
				message="one or more textboxes seem to be blank..";System.out.println("Switch.ADD.Users.one or more fillings are blank");
			} else {			
				try {db.injectUser(tableName,id,name, password,email) ;} catch (Exception e) {e.printStackTrace();}
					System.out.println("added user from servlet redirecting page");
						System.out.println(userList);
						}
		}else if(tableName.equalsIgnoreCase("VERSIONS")){
			String version=request.getParameter("version");
			String product=request.getParameter("product");
			if ((version.equalsIgnoreCase(""))||(product.equalsIgnoreCase(""))) {
				message="one or more textboxes seem to be blank..";System.out.println("Switch.ADD.Versions.one or more fillings are blank");
			} else {				
			try {db.injectVersion(tableName,id,version,product) ;} catch (Exception e) {e.printStackTrace();}
			System.out.println("added version from servlet redirecting page");
				System.out.println(versionList);
			}
		}Display.DisplayAllLists(request, response);Display.DisplayProductOptions(request, response);
		}request.setAttribute("message", message);
				break;
	case reserved:
		request.setAttribute("message", "the item is reserved !");
	if (tableName.equalsIgnoreCase("devices")) {redirectPage="Devices";Display.DisplayDevicesList(request, response);
	} else if(tableName.equalsIgnoreCase("laptops")){redirectPage="laptops";Display.DisplayLaptopList(request, response);
	}
	break;
	
	case reserve:
		
		System.out.println("switch.case.reserve");
		String Sid=request.getParameter("ID");
		/*if(!status.equalsIgnoreCase("available")){request.setAttribute("message", "the item is reserved !");}
		else{}*/
		try {dbu.reserve(user,Sid,tableName);
			} catch (Exception e) {
			e.printStackTrace();
		}	message="reserved item with ID "+Sid+"";	request.setAttribute("message", message);
//		try {
//			mail.service(request, response);
//		} catch (ServletException e) {
//			
//			e.printStackTrace();	}
		if(user.equalsIgnoreCase("admin")){redirectPage="admin";Display.DisplayAllLists(request, response);Display.DisplayProductOptions(request, response);
		}else if (tableName.equalsIgnoreCase("devices")) {redirectPage="Devices";Display.DisplayDevicesList(request, response);
		} else if(tableName.equalsIgnoreCase("laptops")){redirectPage="laptops";Display.DisplayLaptopList(request, response);
		}
		break;
		
	case release:
		System.out.println("switch.case.release");
		 Sid=request.getParameter("ID");
		
		try {dbu.release(user,Sid,tableName);
			} catch (Exception e) {
			e.printStackTrace();
		}	message="released item with ID "+Sid+"";	request.setAttribute("message", message);
//		try {
//			mail.service(request, response);
//		} catch (ServletException e) {
//			
//			e.printStackTrace();	}
		if(user.equalsIgnoreCase("admin")){redirectPage="admin";Display.DisplayAllLists(request, response);Display.DisplayProductOptions(request, response);
		}else if (tableName.equalsIgnoreCase("devices")) {redirectPage="Devices";Display.DisplayDevicesList(request, response);
		} else if(tableName.equalsIgnoreCase("laptops")){redirectPage="laptops";Display.DisplayLaptopList(request, response);
		}
		break;
		
	case updateStatus:
		System.out.println("switch.case.updateStatus");
		String List1;
		String List2,List3,product = null;
		Sid=request.getParameter("ID");
		DBsearch search=new DBsearch();
		
	  if (tableName.equalsIgnoreCase("Devices")) {
		query="SELECT Name FROM "+tableName+" WHERE id = "+ Sid;
		System.out.println("query from updatestatus is : "+query);
			try {List1=search.prepareItem(tableName, user,  query);request.setAttribute("lab1", List1);
				} catch (Exception e) {
					e.printStackTrace();}
				 redirectPage="DevicesEdit";
				
			query="SELECT Ip FROM "+tableName+" WHERE id = "+ Sid;
			System.out.println("query from updatestatus is : "+query);
				try {List2=search.prepareItem(tableName, user,  query);request.setAttribute("lab2", List2);
					} catch (Exception e) {
						e.printStackTrace();}
					 redirectPage="DevicesEdit";
					 
				query="SELECT Version FROM "+tableName+" WHERE id = "+ Sid;
				System.out.println("query from updatestatus is : "+query);
					try {List3=search.prepareItem(tableName, user,  query);request.setAttribute("lab3", List3);
						} catch (Exception e) {	e.printStackTrace();}
						query="SELECT product FROM "+tableName+" WHERE id = "+ Sid;
						System.out.println("query from updatestatus is : "+query);
						try {product=search.prepareItem(tableName, user,  query);
						} catch (Exception e) {	e.printStackTrace();}
						 redirectPage="DevicesEdit";
						 System.out.println("requesting version options");
						 String versionOptions = search.getVersionOptions(product);request.setAttribute("version", versionOptions);
							
						
	  }
				else if (tableName.equalsIgnoreCase("Laptops")) { redirectPage="laptopsEdit";
				query="SELECT Name FROM "+tableName+" WHERE id = "+ Sid;
				System.out.println("query from updatestatus is : "+query);
					try {List1=search.prepareItem(tableName, user,  query);request.setAttribute("lab1", List1);
						} catch (Exception e) {
							e.printStackTrace();}
						 redirectPage="DevicesEdit";
						
					query="SELECT Password FROM "+tableName+" WHERE id = "+ Sid;
					System.out.println("query from updatestatus is : "+query);
						try {List2=search.prepareItem(tableName, user,  query);request.setAttribute("lab2", List2);
							} catch (Exception e) {
								e.printStackTrace();}
							 redirectPage="DevicesEdit";
							 
						query="SELECT Os FROM "+tableName+" WHERE id = "+ Sid;
						System.out.println("query from updatestatus is : "+query);
							try {List3=search.prepareItem(tableName, user,  query);request.setAttribute("lab3", List3);
								} catch (Exception e) {
									e.printStackTrace();}
								 redirectPage="laptopsEdit";
								 //System.out.println("requesting version options");String versionOptions = search.getVersionOptions();request.setAttribute("version", versionOptions);
									
								liste= search.getList("Laptops",user).get(0).toString();request.setAttribute("laptop", liste);
								itemList = search.getItemList("Laptops",user).get(0).toString();request.setAttribute("laptop", liste);
								}
	  request.setAttribute("id", Sid);
	  request.setAttribute("tableName", tableName);
	  System.out.println("switch.case.updateStatus.tableName: "+tableName);
	  break;
	case UPDATEPARAMETER:
		String ip="";
		name="";
		String version="";
		System.out.println("switch.updateparameter");
		
		name=request.getParameter("NAME");
		Sid= request.getParameter("id");
		if (tableName.equalsIgnoreCase("devices")) {
			ip=request.getParameter("IP");
			version=request.getParameter("VERSION");
			redirectPage="Devices";
			dbu.update(tableName, Sid, name, ip, version);
			Display.DisplayDevicesList(request, response);
		} else if(tableName.equalsIgnoreCase("laptops")){
			String password=request.getParameter("password");
			String os=request.getParameter("OS");
			ip=password;
			version=os;
			redirectPage="laptops";
			dbu.update(tableName, Sid, name, ip, version);
			Display.DisplayLaptopList(request, response);

		}if(user.equalsIgnoreCase("admin")){redirectPage="admin";Display.DisplayAllLists(request, response);Display.DisplayProductOptions(request, response);}
		
		
	
	
		
		
		
		
		break;
	case addCollumn:
		db=new DBadd();
		  newCollumn=request.getParameter("newCollumn");
		 type=request.getParameter("type");
		 if(type==null){type="varchar";}
		query="ALTER TABLE "+tableName+" ADD "+newCollumn+" "+type+" ";
		db.execute(tableName,  query);
		redirectPage="admin";
		Display.DisplayAllLists(request, response);
		Display.DisplayProductOptions(request, response);
		break;
		
	case adminemailchange:
		System.out.println("Switch.adminemailchange");
		email=request.getParameter("email");
		String username=request.getParameter("username");
		try {
			dbu.updateEmail(username,email);
		} catch (Exception e) {
			e.printStackTrace();
		}if (user.equalsIgnoreCase("admin")) {
			redirectPage="admin";
			Display.DisplayAllLists(request, response);
			Display.DisplayProductOptions(request, response);
		} else {
		redirectPage="Devices";
		Display.DisplayDevicesList(request, response);}
		break;
		
	case updateEmail:
		System.out.println("Switch.updateEmail");
		email=request.getParameter("email");
		try {
			dbu.updateEmail(user,email);
		} catch (Exception e) {
			e.printStackTrace();
		}if (user.equalsIgnoreCase("admin")) {
			redirectPage="admin";
			Display.DisplayAllLists(request, response);
			Display.DisplayProductOptions(request, response);
		} else {
		redirectPage="Devices";
		Display.DisplayDevicesList(request, response);}
		break;
	case resetPassword:
		System.out.println("Switch.resetPassword");
		try {
			dbu.resetPassword(user);
		} catch (Exception e) {
			e.printStackTrace();
		}redirectPage="admin";
		Display.DisplayAllLists(request, response);
		Display.DisplayProductOptions(request, response);
		break;
		
	case search:
		System.out.println("switch.case.search");
		 tableName=  request.getParameter("tableName");
		String searchName = request.getParameter("searchName");
		 redirectPage= request.getParameter("redirectPage");
		System.out.println("this is servlet action is search parameters: "+tableName+searchName+redirectPage);
		list="";
		try{totalList.clear();searchList.clear();}catch (Exception e) {	}
		SpaceChecker(searchName,request);
		totalList=ListCleaner(totalList);
		int size=totalList.size();
			System.out.println("totalList size: "+size);
			list="";
			
			for (int listCounter=0;listCounter<size;listCounter++){
				System.out.println("servlet is requesting search list. list counter is: "+listCounter+" size: "+size);
				list= list+totalList.get(listCounter).toString();
				if (tableName.equalsIgnoreCase("Devices")) {request.setAttribute("lab", list);
					
				} else if(tableName.equalsIgnoreCase("laptops")){
					liste=list;
					request.setAttribute("laptop", liste);
				}
			}
		System.out.println("servlet search list: "+list);
		System.out.println("servlet search liste: "+liste);
		
	break;
		
	default:
		
System.out.println("action parameter is null or empty");
System.out.println("action parameter is not known"+actionparameter+" "+user+" "+redirectPage);
		
		redirectPage="index";
		response.sendRedirect("index.jsp");
		
		break;
	}
	return redirectPage;
	}
@SuppressWarnings("unchecked")
private static List search(String searchName,HttpServletRequest request) {
	
	String tableName=request.getParameter("tableName");
	String user=request.getParameter("user");
	searchList=search.search(tableName,user,searchName);
	
	
	int size=searchList.size();
	System.out.println("searchList size: "+size);
	for (int i=0;i<size;i++){totalList.add(searchList.get(i));}
	return searchList;
	
}

private static void SpaceChecker(String searchName,HttpServletRequest request) {
	
	if (searchName.contains(" ")) {
		System.out.println("spaceChecker.containsSpace");
		searchList=search(searchName.substring(0,(searchName.indexOf(" "))),request);
		SpaceChecker((searchName.substring(searchName.indexOf(" ")+1)),request);
	} else {
		System.out.println("spaceChecker.doesntcontainSpace");
		searchList=search(searchName,request);
		}
	}
@SuppressWarnings("unchecked")
private static List ListCleaner(List cleanList) {
	System.out.println("list Cleaner");
	int size=cleanList.size();System.out.println("cleanList.size: "+size);
	for (int i=0;i<size;i++){
		for (int j=i+1;j<size;j++){
			if(cleanList.get(i).equals(cleanList.get(j))){
				
				cleanList.add(0, cleanList.get(j)); System.out.println("add "+j+" : "+cleanList.get(j));System.out.println("to 0 : "+cleanList.get(0));
				 System.out.println("removing "+j+1+" : "+cleanList.get(j+1));
				 		cleanList.remove(j+1);
				 System.out.println("removing "+i+1+" : "+cleanList.get(i+1));
				 		cleanList.remove(i+1);
				 		size=size-1;
				
				}
			else{System.out.println(i+" "+cleanList.get(i));System.out.println(j+" "+cleanList.get(j));}
		}
	}
	return cleanList;
}
}
	
