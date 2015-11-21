<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>laptop edit</title>
</head>
<link rel="stylesheet" href="pageDesign3.css" type="text/css" /> 
<body>
<% String user=(String)request.getAttribute("asd");%>
<% String id=(String)request.getAttribute("id");%>
<%String tableName=(String)request.getAttribute("tableName"); %>
<%System.out.println("LaptopsEdit.tableName: "+tableName); %>
<% String message=(String)request.getAttribute("message");%>
<%if (message==null){message="";} %>
<center><%=message%></center>
<div class= "zx">
<div class="ex">
<center><h1>Laptops Edit Page</h1></center>
<center>
<form name="update" >
<%String list1= (String)request.getAttribute("lab1"); %>
<%String list2= (String)request.getAttribute("lab2"); %>
<%String list3= (String)request.getAttribute("lab3"); %>
<%String versionOptions=(String)request.getAttribute("version");%>
<input type="text" Name="NAME" Value="<%=list1%>">
<input type="text" Name="password" Value="<%=list2%>">
<input type="text" Name="OS" Value="<%=list3 %>">
<input  type="hidden" name="user"  value="<%=user%>">
<input  type="hidden" name="id"  value="<%=id%>">
<input type="hidden" name="tableName" value="<%=tableName%>">
<input  type="hidden" name="action"  value="UPDATEPARAMETER">
<input type="submit" name="submit"  value="update">

</form></center><center><form>
<input  type="hidden" name="user"  value="<%=user%>">
<input type="hidden" name="action" value="toLAPTOPs">
<input type="submit" name="submit" value="cancel" ></input>
</form></center>
</div>
</div>

</body>
</html>