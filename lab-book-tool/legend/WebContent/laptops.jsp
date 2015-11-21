<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Laptops</title>
</head>
<link rel="stylesheet" href="pageDesign3.css" type="text/css" /> 

<body>
<% String user=(String)request.getAttribute("asd");%>
<% String message=(String)request.getAttribute("message");%>
<%if (message==null){message="";} %>
<center><%=message%></center><div class= "zx">
<div class= "fx">
<center><h1>Laptops Page</h1></center>
<table><tr>
<td>

<form name ="toDevices" action="servlet" method="post">

<input  type="hidden" name="user"  value="<%=user%>">
<input  type="hidden" name="action"  value="toDEVICEs">
<input type="submit" name="submit"  value="DEVICE"></input>
</form>

</td><td>
<form name ="search" action="servlet" method="post">
<input type="text" name="searchName" ></input>
<input  type="hidden" name="user"  value="<%=user%>">
<input  type="hidden" name="redirectPage"  value="laptops">
<input  type="hidden" name="tableName"  value="LAPTOPS">

<input  type="hidden" name="action"  value="search">
<input type="submit" name="submit"  value="search"></input>
</form>
</td></tr></table></div>



<% String liste=(String)request.getAttribute("laptop");%>
<%if (liste==null){liste="";} %>

<div class="ex">

<table id="list" cellpadding="1" cellspacing="1" border="3" > 
<tr><th id="NAME">&nbsp;</th><th id="ID"><div  style="text-align:center;">Id</div></th><th id="NAME"><div style="text-align:center;">Name</div></th><th id="Password"><div  style="text-align:center;">Password</div></th><th id="OS"><div style="text-align:center;">OS</div></th><th id="STATUS"><div style="text-align:center;">Status</div></th></tr>
<%=liste%>
</table></div> 

<div class="fx">
<table>
<tr><td align="right"   >


<%if (user==null){user="bos";} %>
<%=user%>
</td><td align="right">

<form name ="toChangePassword" action="servlet" method="post">
<input  type="hidden" name="user"  value="<%=user%>">
<input  type="hidden" name="action"  value="toChangePassword">
<input type="submit" name="submit"  value="change password & email"></input>
</form>

</TD><td>

<form name="logout" action="index.jsp"><input type="submit" name="submit"  value="log out"></input></form>

</td></tr></table></div>
</div>

</body>
</html>