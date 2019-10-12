package com.cxf.webservice.service;

import com.cxf.webservice.model.OfficeProperty;
import com.cxf.webservice.model.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * ICommonService
 *
 * @author Joe
 * @version 1.0
 * @since 2018/6/15
 */
@WebService(name = "CommonService", // 暴露服务名称
        targetNamespace = "http://model.webservice.xncoding.com/"// 命名空间,一般是接口的包名倒序
)
public interface ICommonService {
    @WebMethod
//    @WebResult(name = "String", targetNamespace = "")
    public String sayHello(@WebParam(name = "userName") String name);

    @WebMethod
//    @WebResult(name = "String", targetNamespace = "")
    public User getUser(@WebParam(name = "userName") String name);

    @WebMethod
    public List<OfficeProperty> getAll();
}
