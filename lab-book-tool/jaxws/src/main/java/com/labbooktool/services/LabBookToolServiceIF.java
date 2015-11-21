package com.labbooktool.services;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;

@WebService(targetNamespace = "http://com/labbooktool/", name = "labBookToolService")
public interface LabBookToolServiceIF {
	
	
    @Oneway
    @RequestWrapper(localName = "deviceResponse", targetNamespace = "http://com/labbooktool/messages", className = "com.labbooktool.EnerjiKesintisiBildirimiResponse")
    @WebMethod(action = "http://www.siemens.com/aydem_osos_ws/enerjiKesintisiBildirimiResponse")
    public void getDevice(
//        @WebParam(name = "replyHeader", targetNamespace = "http://www.siemens.com/aydem_osos_ws/messages")
//        com.siemens.aydem_osos_ws.messages.ReplyHeader replyHeader
    );

}
