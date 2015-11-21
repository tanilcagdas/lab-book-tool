package com.lab;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class servlet extends HttpServlet {
	static List<String> arraylist=new ArrayList<String>();
	static String list="";static String liste="";static String userList="";static String userOptions="";static String versionList="";String query;
	static DBsearch search= new DBsearch();
	static int size;
	private static final long serialVersionUID = 1L;
  
    public servlet() {
    	
        super();
    }
    public void service (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String user=request.getParameter("user");
		String tableName=request.getParameter("tableName");
		String redirectPage=request.getParameter("redirectPage");
		
		if(user==null){redirectPage="index";}
    	
    	    	
    	
		javax.servlet.RequestDispatcher rd;
		
		System.out.println("begining of the page");
			
		String actionparameter= request.getParameter("action");
		if(actionparameter==""||actionparameter==null){redirectPage= "index" ;
		}else{System.out.println("action parameter is : "+actionparameter+" tableName: "+tableName);
		try {
			redirectPage=Switch.switcher(request, response, actionparameter, user, redirectPage, tableName);
		} catch (ClassNotFoundException e) {	e.printStackTrace();		}
		}
		
		System.out.println("end of page servlet" + actionparameter+user+redirectPage);
		request.setAttribute("asd", user);
		if(actionparameter==""){redirectPage= "index" ;
		}else if(redirectPage==null){redirectPage= "Devices" ;
		}else if(redirectPage.equalsIgnoreCase("laptops")){redirectPage="laptops";
		} rd = this.getServletContext().getRequestDispatcher("/"+redirectPage+".jsp");
		rd.forward(request, response);
		
		
	}
    	
	
	}
