package com.lab;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import sun.net.smtp.SmtpClient;

import com.oreilly.servlet.ParameterParser;

/**
 * Servlet implementation class Mail
 */
@SuppressWarnings({ "deprecation", "unchecked" })
public class Mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String item="";String status=""; String id = "";String tableName = "";String user = "";String query="";
	String smtpHost = "47.168.10.40";
	  static final String FROM = "LabBookTool";
	  static final String TO = "";
	  static List<String> mailList;
    public Mail() {
        super();
        // TODO Auto-generated constructor stub
    }

	
  
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		DBsearch search=new DBsearch();
	response.setContentType("text/plain");
	PrintWriter out = response.getWriter();

	ParameterParser parser = new ParameterParser(request);
	String from = parser.getStringParameter("from", FROM);
	String to = parser.getStringParameter("to", TO);
	mailList=new ArrayList<String>();
	
	
	query="select email from users";
	try {
		mailList=search.prepareList("Devices", user, query);
	} catch (Exception e) {
		e.printStackTrace();
	}
	Iterator<String> i=mailList.iterator();
			while(i.hasNext()){
	
	to=(i.next());
	if (to==null||to.equalsIgnoreCase("")||to.equalsIgnoreCase("null")) {
		System.out.println("Mail.email is null");		
	} else {
		System.out.println("sending mail to : "+to);
	
	try {
	  SmtpClient smtp = new SmtpClient(smtpHost);  // assume localhost
	  smtp.from(from);
	  smtp.to(to);
	  prepareMSG(request, response, from, to, smtp, parser);
	  smtp.closeServer();

	  out.println("Thanks for the submission...");}
	  catch (IOException e) {
	      out.println("There was a problem handling the submission...");
	      e.printStackTrace();
	          }
	

			}
		}
	}



	private void prepareMSG(HttpServletRequest request, HttpServletResponse response,String from, String to,SmtpClient smtp,ParameterParser parser) throws IOException {
		
		PrintStream msg = smtp.startMessage();

		  msg.println("To: " + to);  // so mailers will display the To: address
		  msg.println("Subject: Lab reservation");
		  msg.println();

		  Enumeration<String> enumaration = request.getParameterNames();
		  while (enumaration.hasMoreElements()) {
		    String name = enumaration.nextElement();
		    if (name.equals("to") || name.equals("from")) continue;  // Skip to/from
		    String value = parser.getStringParameter(name, null);
		    if (name.equalsIgnoreCase("id")){id=value;}
		    else if(name.equalsIgnoreCase("tableName")){tableName=value;}
		    else if(name.equalsIgnoreCase("user")){user=value;}
		      //msg.println(name + " = " + value);
		  }
		  DBsearch search=new DBsearch();
		

		 String query="select name from "+tableName+" where id = "+id ;
		 System.out.println("mail.query is : "+query);
		 try {item=search.prepareItem(tableName, user, query);}catch(Exception e){e.printStackTrace();}
		 query="select status from "+tableName+" where id = "+id ;
		 System.out.println("mail.query is : "+query);
		 try {status=search.prepareItem(tableName, user, query);}catch(Exception e){e.printStackTrace();}
		 msg.println();
	if (status.equalsIgnoreCase("available")) {
		msg.println("Status of the Item "+item +" is "+status); 
	} else {msg.println(item+" is reserved by "+user);}
		  
		 msg.println();
		  msg.println("---");
		  msg.println("Sent by " + HttpUtils.getRequestURL(request));
		
	}
}
