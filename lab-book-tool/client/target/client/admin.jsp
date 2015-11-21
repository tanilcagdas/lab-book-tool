<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>admin page</title>
</head>
<link rel="stylesheet" href="pageDesign3.css" type="text/css" /> 
<body>

<% String user=(String)request.getAttribute("asd");%>
<% String userOptions=(String)request.getAttribute("status");%>
<% String versionOptions=(String)request.getAttribute("version");%>
<% String productOptions=(String)request.getAttribute("product");%>
<% String newID=(String)request.getAttribute("id"); %>
<% String message=(String)request.getAttribute("message");%>
<%if (message==null){message="";} %>
<center><%=message%></center>
<div class="zx">
<div class="fx">

<center><h1>Admin Page</h1></center>
<form name="add device" action="servlet"
method="post">
<table><tr><td>table name </td><td>id </td><td> name </td><td> ip </td><td>version </td><td>status </td><td> product </td></tr>
<tr><td><h1>Devices</h1>
</td><td><input type="text" name="id" size="3" value="<%=newID%>">
</td><td><input type="text" name="name" size="3" >
</td><td><input type="text" name="ip" size="3" >
</td><td><select name="version" ><%=versionOptions %></select>
</td><td><select name="status" ><option>available</option><%=userOptions %></select>
</td><td><select name="product" ><%=productOptions %></select>
</td></tr></table><input type="hidden" name="action"  value="ADD">
<input type="hidden" name="tableName" value="devices">
<input type="hidden" name="redirectPage" value="admin" ><%System.out.println("admin.jsp.display device form"); %>
<input  type="hidden" name="user"  value="<%=user%>">
<input type="submit" name="submit"  value="Add Device">
</form>
</div>


<div class="ex">
<% String list=(String)request.getAttribute("lab");%>
<%if (list==null){list="";} %>
<table id="list" summary="" cellpadding="0" cellspacing="0" border="0" > 
<tr><th id="NAME">&nbsp;</th><th id="ID"><div  style="text-align:center;">Id</div></th><th id="NAME"><div style="text-align:center;">Name</div></th><th id="IP"><div  style="text-align:center;">Ip</div></th><th id="RUNNINGVERSION"><div style="text-align:center;">Runningversion</div></th><th id="STATUS"><div style="text-align:center;">Status</div></th><th id="PRODUCT"><div style="text-align:center;">Product</div></th></tr>
<%=list%>
</table> 
</div>

<div class="fx">
<form name="add laptop" action="servlet"
method="post">
<table><tr><td>Table name </td><td>Id </td><td> Name </td><td> Password </td><td>OS </td><td>Status </td></tr>
<tr><td><h1>Laptops</h1>
</td><td><input type="text" name="id" size="3" value="<%=newID%>">
</td><td> <input type="text" name="name" size="3" >
</td><td><input type="text" name="password" size="3" >
</td><td><input type="text" name="os" size="3" >
</td><td><select name="status" ><option>available</option><%=userOptions %></select>
</td></tr></table><input type="hidden" name="action"  value="ADD">
<input type="hidden" name="tableName" value="laptops">
<input type="hidden" name="redirectPage" value="admin" ><%System.out.println("admin.jsp.display laptop form"); %>
<input  type="hidden" name="user"  value="<%=user%>">
<input type="submit" name="submit"  value="Add LAPTOP">
</form>
</div>

<div class="ex">
<% String liste=(String)request.getAttribute("laptop");%>
<%if (liste==null){liste="";} %>
<table id="list" summary="" cellpadding="0" cellspacing="0" border="0" > 
<tr><th id="NAME">&nbsp;</th><th id="ID"><div  style="text-align:center;">Id</div></th><th id="NAME"><div style="text-align:center;">Name</div></th><th id="Password"><div  style="text-align:center;">Password</div></th><th id="OS"><div style="text-align:center;">OS</div></th><th id="STATUS"><div style="text-align:center;">Status</div></th></tr>
<%=liste%>
</table>
</div>



<div class="fx">
<form name="add user" action="servlet" method="post">
<table><tr><td>table name</td><td>id</td><td>name</td><td>password</td><td>email</td></tr>
<tr><td><H1>Users</H1>
</td><td><input type="text" name="id" size="3" value="<%=newID%>">
</td><td><input type="text" name="name" size="3" >
</td><td><input type="text" name="password" size="3" >
</td><td><input type="text" name="email" size="3" >
</td></tr></table><input type="hidden" name="action"  value="ADD">
<input type="hidden" name="tableName" value="users">
<input type="hidden" name="redirectPage" value="admin" ><%System.out.println("admin.jsp.display user form"); %>
<input  type="hidden" name="user"  value="<%=user%>">
<input type="submit" name="submit"  value="Add USER">
</form>
</div>

<div class="ex">
<% String userList=(String)request.getAttribute("users");%>
<%if (userList==null){userList="";} %>
<table id="list" summary="" cellpadding="0" cellspacing="0" border="0" > 
<tr><th id="ID"><div style="text-align:center;"> Id </div></th>
<th id="NAME"><div  style="text-align:center;"> Name </div></th>
<th id="Password"><div  style="text-align:center;"> Password </div></th> 
<th id="Email"><div  style="text-align:center;"> Email </div></th> 
<%=userList%>
</table>
</div>

<div class="fx">
<form name="add version" action="servlet" method="post">
<table><tr><td>table name</td><td>id</td><td>version</td><td>product</td></tr>
<tr><td><H1>Versions</H1>
</td><td><input type="text" name="id" size="3" value="<%=newID%>">
</td><td><input type="text" name="version" size="3" >
</td><td><input type="text" name="product" size="3" >
</td></tr></table><input type="hidden" name="action"  value="ADD">
<input type="hidden" name="tableName" value="versions">
<input type="hidden" name="redirectPage" value="admin" ><%System.out.println("admin.jsp.display version form"); %>
<input  type="hidden" name="user"  value="<%=user%>">
<input type="submit" name="submit"  value="Add VERSION">
</form></div>
<div class="ex">
<% String versionList=(String)request.getAttribute("versions");%>
<%if (versionList==null){versionList="";} %>
<table id="list" summary="" cellpadding="0" cellspacing="0" border="0" > 
<tr><th id="ID"><div  style="text-align:center;">Id</div></th>
<th id="version"><div   style="text-align:center;"> Version</div></th>
<th id="product"><div  style="text-align:center;"> Product</div></th> 
<%=versionList%>
</table>
</div>
<div class="fx">
<form name="deleting form" action="servlet"
method="post">
ID: <input type="text" name="INDEX" size="3" >
from table: <select name=tableName><option>devices</option><option>laptops</option><option>users</option><option>versions</option></select>
<input type="hidden" name="action"  value="REMOVE">
<input type="hidden" name="redirectPage" value="admin" >
<input  type="hidden" name="user"  value="<%=user%>">
<input type="submit" name="submit"  value="remove element">
</form>
<form name="add email to table" action="servlet" method="post">
newCollumn: <input type="text" name="newCollumn" size="9" value="email">
to table: <select name=tableName><option>users</option><option>laptops</option><option>devices</option><option>versions</option></select>
type: <select name=type><option>varchar(30)</option><option>int</option></select>
<input type="hidden" name="action" value="addCollumn">
<input type="hidden" name="redirectPage" value="admin" >
<input  type="hidden" name="user"  value="<%=user%>">
<input type="submit" name="submit"  value="add  collumn">
</form>
</div>
<div class="fx">
<%if (user==null){user="";} %>
<table><tr><td>
<%=user%>
</td><td align="right">
<form name ="toChangePassword" action="servlet" method="post">
<input  type="hidden" name="user"  value="<%=user%>">
<input  type="hidden" name="action"  value="toChangePassword">
<input type="submit" name="submit"  value="change password"></input>
</form>
</td><td>
<form name="logout" action="index.jsp"><input type="submit" name="submit"  value="log out"></input></form>
</td></tr></table></div>
</div>
</body>
</html>