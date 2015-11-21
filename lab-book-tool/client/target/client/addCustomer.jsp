<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Customer</title>
</head>
<link rel="stylesheet" href="pageDesign3.css" type="text/css" /> 
<body>
<div class="zx">
<div class="fx">
<f:view>

	<h:form>
		<h:panelGrid border="1" columns="2">
			<h:outputText value="id"></h:outputText><h:inputText value="#{customerBean.id}"></h:inputText>
			<h:outputText value="name"></h:outputText><h:inputText value="#{customerBean.name}"></h:inputText>
		</h:panelGrid>
		<h:commandButton action="#{customerBean.add}" value="Add Customer" ></h:commandButton>
		
	</h:form>
</f:view>

</div></div>
</body>
</html>