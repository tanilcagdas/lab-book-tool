<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="pageDesign3.css" type="text/css" /> 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lab Devices</title>
</head>
<body >

<% String message=(String)request.getAttribute("message");%>
<%if (message==null){message="";} %>
<center><%=message%></center>

<div class= "fx">
<center><h1>Devices Page</h1></center>










<div class="ex">
 </div> 



		<rich:dataTable id="DeviceTable"
		    value="#{Controller.dataForDevices}" var="Device"
			columnClasses="centeralignclass" styleClass="stddatatable">
			<f:facet name="header">
				<rich:columnGroup>
					<h:column>
						<h:outputText styleClass="headerText" value="User Name" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="Operator" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="Network Attendant" />
					</h:column>
	
					<h:column>
						<h:outputText styleClass="headerText" value="Edit Extension Data" />
					</h:column>
				</rich:columnGroup>
			</f:facet>
				
			<h:column>
			    <h:outputText value="#{Device.id}" />
			</h:column>
			
			<h:column>
				<h:outputText value="#{Device.name}" />
			</h:column>
	
	        <h:column>
				<h:outputText value="#{Device.ip}" />
			</h:column> 
			
			<h:column>
				<h:commandLink action="#{Controller.changeDeviceStatus}" value="#{Device.status}">
        <f:setPropertyActionListener target="#{Controller.selectedID}" value="#{Device.id}" />
        <f:setPropertyActionListener target="#{Controller.selectedStatus}" value="#{Device.status}" />
        <f:setPropertyActionListener target="#{Controller.selectorUser}" value="#{indexBean.loginUserName}" />
        <f:setPropertyActionListener target="#{Controller.selectedTable}" value="DEVICES" />
				</h:commandLink>
			</h:column>
	
		</rich:dataTable>



	


</div>

</body>
</html>