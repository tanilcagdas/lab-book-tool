<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lab Devices</title>
</head>
<body >

<% String message=(String)request.getAttribute("message");%>
<%if (message==null){message="";} %>
<center><%=message%></center>


<center><h1>LabBook</h1></center>










<f:view>
<h:form>


		<rich:tabPanel switchType="client" rendered="#{indexBean.userLoggedIn}">

				<rich:tab label="devices" name="Devices">
					<f:subview id="Devices">
						<jsp:include page="DevicesJSF.jsp" />
					</f:subview>
				</rich:tab>
				
				<rich:tab label="laptops" name="Laptops">
					<f:subview id="Laptops">
						<jsp:include page="laptopsJSF.jsp" />
					</f:subview>
				</rich:tab>

			</rich:tabPanel>			

</h:form>
 <center>

<h:panelGroup><h:outputText value="#{indexBean.loginUserName}" ></h:outputText>

	<h:commandButton action="#{indexBean.logout}" value="logout" ></h:commandButton>
		

	</h:panelGroup>
	</center>

	
</f:view>




</body>
</html>