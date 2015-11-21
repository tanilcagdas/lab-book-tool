<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%> 
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




		<rich:dataTable id="LaptopTable"
		    value="#{Controller.dataForLaptops}" var="Laptop"
			columnClasses="centeralignclass" styleClass="stddatatable">
			<f:facet name="header">
				<rich:columnGroup>
					<h:column>
						<h:outputText styleClass="headerText" value="ID" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="NAME" />
					</h:column>
					
					<h:column>
						<h:outputText styleClass="headerText" value="OS" />
					</h:column>
	
					<h:column>
						<h:outputText styleClass="headerText" value="STATUS" />
					</h:column>
				</rich:columnGroup>
			</f:facet>
				
			<h:column>
			    <h:outputText value="#{Laptop.id}" />
			</h:column>
			
			<h:column>
				<h:outputText value="#{Laptop.name}" />
			</h:column>
	
	        <h:column>
				<h:outputText value="#{Laptop.os}" />
			</h:column> 
			
			<h:column>
				<h:commandLink action="#{Controller.DataForLaptops}" value="#{Laptop.status}">
					<f:param name="name"  />
				</h:commandLink>
				

			
			</h:column>
	
		</rich:dataTable>
		
		





	



