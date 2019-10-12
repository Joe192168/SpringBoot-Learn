package com.cxf.webservice.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CommonService", targetNamespace = "http://model.webservice.xncoding.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CommonService {


    /**
     * 
     * @param userName
     * @return
     *     returns com.xncoding.webservice.client.User
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getUser", targetNamespace = "http://model.webservice.xncoding.com/", className = "com.xncoding.webservice.client.GetUser")
    @ResponseWrapper(localName = "getUserResponse", targetNamespace = "http://model.webservice.xncoding.com/", className = "com.xncoding.webservice.client.GetUserResponse")
    public User getUser(
            @WebParam(name = "userName", targetNamespace = "")
                    String userName);

    /**
     * 
     * @param userName
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://model.webservice.xncoding.com/", className = "com.xncoding.webservice.client.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://model.webservice.xncoding.com/", className = "com.xncoding.webservice.client.SayHelloResponse")
    public String sayHello(
            @WebParam(name = "userName", targetNamespace = "")
                    String userName);

}
