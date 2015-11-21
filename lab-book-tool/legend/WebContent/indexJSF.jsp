<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to EDLAB book tool</title>
</head>
<link rel="stylesheet" href="pageDesign3.css" type="text/css" />
  
<style type="text/css" >

div.fx 
{
	position:relative;
	
	
	

padding:10px;
border-style:inset;
border-color:blue;
margin:0px;
color: #white;
background-color: #3F547F;

}
div.sx 
{
	position:absolute;
bottom:30px;
right:30px;
padding:10px;

border-style:inset;
border-color:blue;
margin:0px;
color: #white;
background-color: #3F547F;

}</style>

<body>

<% String message=(String)request.getAttribute("message");%>
<%if (message==null){message="";} %>
<center><%=message%></center>
<div class="zx">
<div class="fx">

<!-- 
<center><form name="wellcome form" action= "servlet" 
method="post">
<br>username : <input type="text" name="user" size="7" >
<br>password : <input type="password" name="pwd" size="7" >
<input type="hidden" name="action"  value="LOGIN">
<br><input type="submit" name="submit"  value="log in">
</form></center>
 -->
 <f:view>
<center>
	<h:form>
		<h:panelGrid border="1" columns="2">
			<h:outputText value="username"></h:outputText><h:inputText value="#{indexBean.loginUserName}"></h:inputText>
			<h:outputText value="password"></h:outputText><h:inputSecret value="#{indexBean.loginPassword}"></h:inputSecret>
		</h:panelGrid>
		<h:commandButton action="#{indexBean.login}" value="login" ></h:commandButton>
		
	</h:form>
	</center>
</f:view>
</div>


<center><% String user=(String)request.getAttribute("asd");%>
<%if (user==null){user="";} %>


</center> 
<div class="sx">
created by: tanil cagdas
v0.6.4.101220
</div>
</div>
</body>
</html>