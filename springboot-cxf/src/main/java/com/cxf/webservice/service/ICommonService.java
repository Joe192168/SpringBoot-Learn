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
        targetNamespace = "http://service.webservice.cxf.com/"// 命名空间,一般是接口的包名倒序
)
public interface ICommonService {
    @WebMethod(action = "sayHello")
    public String sayHello(@WebParam(name = "name",targetNamespace="http://service.webservice.cxf.com/") String name);

    @WebMethod(action = "getUser")
    public User getUser(@WebParam(name = "name",targetNamespace="http://service.webservice.cxf.com/")String name);

    @WebMethod(action = "getAll")
    public List<OfficeProperty> getAll();

    @WebMethod(action = "getDepartmentApi")
    public String getDepartmentApi(@WebParam(name = "startTime",targetNamespace="http://service.webservice.cxf.com/")String startTime);


    @WebMethod(action = "getPersonnelApi")
    public String getPersonnelApi(@WebParam(name = "startTime",targetNamespace="http://service.webservice.cxf.com/")String startTime);
}
