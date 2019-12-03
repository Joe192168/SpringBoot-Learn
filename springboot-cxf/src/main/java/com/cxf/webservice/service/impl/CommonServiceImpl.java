package com.cxf.webservice.service.impl;

import com.cxf.webservice.model.OfficeProperty;
import com.cxf.webservice.model.SyncMemberDto;
import com.cxf.webservice.model.SystemResources;
import com.cxf.webservice.model.User;
import com.cxf.webservice.service.ICommonService;
import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * CommonServiceImpl
 *
 * @author Joe
 * @version 1.0
 * @since 2018/6/15
 */
@WebService(serviceName = "CommonService", // 与接口中指定的name一致
        targetNamespace = "http://service.webservice.cxf.com/", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.cxf.webservice.service.ICommonService"// 接口地址
)
@Component
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String sayHello(String name) {
        return "Hello ," + name;
    }

    @Override
    public User getUser(String name) {
        return new User(1000L, name, 23);
    }

    @Override
    public List<OfficeProperty> getAll() {
        String sql = "SELECT CODE, OFFICE_NAME, PHONETIC_CODE, CUSTOM_CODE, CLINICAL_OFFICE_ID, POSITION, IS_FINAL_STAGE, ENV_TYPE_ID, RESPONSIBLE_PEOPLE, PREPARATION_BED, OPENING_BED, OFFICE_CLASSIFY_ID, STATISTICS_PROPERTY, CREATE_TIME, UPDATE_TIME, RECORD_STATE FROM T_OFFICE_PROPERTY";
        List<OfficeProperty> list = jdbcTemplate.query(sql,new String[]{},new RowMapper<OfficeProperty>(){
            @Override
            public OfficeProperty mapRow(ResultSet resultSet, int i) throws SQLException {
                OfficeProperty officeProperty = new OfficeProperty();
                officeProperty.setCode(resultSet.getString("CODE"));
                officeProperty.setOfficename(resultSet.getString("OFFICE_NAME"));
                officeProperty.setCustomcode(resultSet.getString("CUSTOM_CODE"));
                officeProperty.setCreatetime(resultSet.getString("CREATE_TIME"));
                return officeProperty;
            }
        });
        return list;
    }

    @Override
    public String getDepartmentApi(String startTime) {
        String xmlstr = null;
        try {
            String sql = "SELECT ID,PARENTID,RESOURCE_TYPE_ID,RESOURCE_NAME,DISPLAY_NAME,DISPLAY_ORDER,DESCRIPTION,DISPLAY_IMG,RESOURCE_LEVEL,IS_INNER_RESOURCE,OWNER,CREATE_TIME,UPDATE_TIME,LAST_EDITOR,RESOURCE_STATE,EXTERNAL_APP_INFO,DERIVE_RESOURCE_ID,CONTENT_INFO FROM T_SYSTEM_RESOURCES WHERE TO_CHAR(CREATE_TIME,'YYYY-MM-DD HH:MM:DD') >= ?";
            List<SystemResources> list = jdbcTemplate.query(sql,new String[]{startTime},new RowMapper<SystemResources>(){
                @Override
                public SystemResources mapRow(ResultSet resultSet, int i) throws SQLException {
                    SystemResources systemResources = new SystemResources();
                    systemResources.setOuterid(resultSet.getBigDecimal("ID"));
                    systemResources.setHiscode("his_"+resultSet.getBigDecimal("ID"));
                    systemResources.setOfficename(resultSet.getString("RESOURCE_NAME"));
                    systemResources.setOuterparentid(resultSet.getBigDecimal("PARENTID"));
                    //systemResources.setOfficelevel(resultSet.getInt("RESOURCE_LEVEL"));
                    return systemResources;
                }
            });
            //创建xStream对象
            XStream xStream = new XStream();
            xStream.processAnnotations(SystemResources.class);
            //调用toXML 将对象转成字符串
            xmlstr = xStream.toXML(list);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return xmlstr;
    }

    @Override
    public String getPersonnelApi(String startTime) {
        String xmlstr = null;
        try {
            String sql = "SELECT TU.ID, TU.USER_NAME, TU.LOGIN_NAME, TU.CODE, TU.CREATE_TIME, RS.ID RESOURCEID FROM T_USERS TU LEFT JOIN T_SYSTEM_RESOURCES RS ON TU.RESOURCEID = RS.ID WHERE TO_CHAR(TU.CREATE_TIME, 'YYYY-MM-DD HH:MM:DD' ) >= ?";
            List<SyncMemberDto> list = jdbcTemplate.query(sql,new String[]{startTime},new RowMapper<SyncMemberDto>(){
                @Override
                public SyncMemberDto mapRow(ResultSet resultSet, int i) throws SQLException {
                    SyncMemberDto syncMemberDto = new SyncMemberDto();
                    syncMemberDto.setOuteruserid(resultSet.getBigDecimal("ID"));
                    syncMemberDto.setHiscode("his_"+resultSet.getBigDecimal("ID"));
                    syncMemberDto.setOuterorganid(resultSet.getBigDecimal("RESOURCEID"));
                    syncMemberDto.setUsername(resultSet.getString("USER_NAME"));
                    syncMemberDto.setLoginname(resultSet.getString("LOGIN_NAME"));
                    syncMemberDto.setCode(resultSet.getString("CODE"));
                    return syncMemberDto;
                }
            });
            //创建xStream对象
            XStream xStream = new XStream();
            xStream.processAnnotations(SyncMemberDto.class);
            //调用toXML 将对象转成字符串
            xmlstr = xStream.toXML(list);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return xmlstr;
    }
}
