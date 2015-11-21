<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<link rel="stylesheet" href="pageDesign3.css" type="text/css" /> 
<body>
<% String user=(String)request.getAttribute("asd");%>
<% String message=(String)request.getAttribute("message");%>
<% String email=(String)request.getAttribute("email");%>
<%if (message==null){message="";} %>
<center><%=message%></center>
<div class="zx">
<div class="ex">
<center><h1>Change Password Page</h1></center>
<form name ="Change Pasword" action="servlet" method="post">

<input  type="hidden" name="user"  value="<%=user%>">
<input  type="hidden" name="action"  value="changePassword">
<table>
<tr><td>old password : </td>
<td><input type="text" name="pwd"  ></input></td></tr>
<tr><td>new password : </td>
<td><input type="text" name="newPwd"  ></input></td>
</tr>
</table><center><input type="submit" name="submit"  value="change password"></input>
</center>
</form>
<center>
<form>
<input  type="hidden" name="user"  value="<%=user%>">
<input type="hidden" name="action" value="toDEVICEs">
<input type="submit" name="submit" value="cancel" ></input>
</form></center>

</div>

<div class="ex">
<center><h1>Change Email </h1></center>
<form name ="Change Email" action="servlet" method="post">

<input  type="hidden" name="user"  value="<%=user%>">
<input  type="hidden" name="action"  value="updateEmail">
<table>
<tr><td> email : </td>
<td><input type="text" name="email"  value="<%=email%>"></input></td></tr>
</table><center><input type="submit" name="submit"  value="update email"></input>
</center>
</form>
<center>
<form>
<input  type="hidden" name="user"  value="<%=user%>">
<input type="hidden" name="action" value="toDEVICEs">
<input type="submit" name="submit" value="cancel" ></input>
</form></center>

</div>


<div class="fx">
<%=user%>
</div>
</div>
</body>
</html>