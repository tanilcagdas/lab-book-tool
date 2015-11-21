<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="pageDesign3.css" type="text/css" /> 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lab Devices</title>
</head>
<body >
<% String user=(String)request.getAttribute("asd");%>
<% String message=(String)request.getAttribute("message");%>
<%if (message==null){message="";} %>
<center><%=message%></center>
<div class= "zx">
<div class= "fx">
<center><h1>Devices Page</h1></center>
<table><tr>
<td>


<form name ="toLaptops" action="servlet" method="post">
<input  type="hidden" name="user"  value="<%=user%>">
<input  type="hidden" name="action"  value="toLAPTOPs">
<input type="submit"  name="submit"  value="LAPTOP"></input>
</form>


</td><td>




<form name ="search" action="servlet" method="post">
<input type="text" name="searchName" ></input>
<input  type="hidden" name="user"  value="<%=user%>">
<input  type="hidden" name="redirectPage"  value="Devices">
<input  type="hidden" name="tableName"  value="DEVICES">

<input  type="hidden" name="action"  value="search">
<input type="submit" name="submit"  value="search"></input>
</form></td>
</tr>
</table></div>

<% String list=(String)request.getAttribute("lab");%>
<%if (list==null){list="";} %>
<input type="hidden" name="action"  value="update">
<input type="hidden" name="redirectPage" value="Devices" >
<%System.out.println("display devices form"); %>
<input  type="hidden" name="user"  value="<%=user%>">
<form name ="senduser" action="servlet" method="get">
<input  type="hidden" name="user"  value="<%=user%>">
</form>

<div class="ex">

<table id="list"  cellpadding="1" cellspacing="1" border="3" > 
<tr><th id="NAME">&nbsp;</th><th id="ID"><div  style="text-align:center;">Id</div></th><th id="NAME"><div style="text-align:center;">Name</div></th><th id="IP"><div  style="text-align:center;">Ip</div></th><th id="RUNNINGVERSION"><div style="text-align:center;">Runningversion</div></th><th id="STATUS"><div style="text-align:center;">Status</div></th><th id="PRODUCT"><div style="text-align:center;">Product</div></th></tr> 
<%=list%>
</table></div> 

<div class="fx">
<table>
<tr><td align="right"   >
<%if (user==null){user="";} %>
<%=user%>
</td><td align="right">
<form name ="toChangePassword" action="servlet" method="post">
<input  type="hidden" name="user"  value="<%=user%>">
<input  type="hidden" name="action"  value="toChangePassword">
<input type="submit" name="submit"  value="change password & email"></input>
</form>
</td><td>
<form name="logout" action="index.jsp"><input type="submit" name="submit"   value="log out"></input></form>
</td></tr></table></div>
</div>

</body>
</html>