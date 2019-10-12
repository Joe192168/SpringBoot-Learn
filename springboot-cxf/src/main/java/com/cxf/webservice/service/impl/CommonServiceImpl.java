package com.cxf.webservice.service.impl;

import com.cxf.webservice.model.OfficeProperty;
import com.cxf.webservice.model.User;
import com.cxf.webservice.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
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
        targetNamespace = "http://model.webservice.xncoding.com/", // 与接口中的命名空间一致,一般是接口的包名倒
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
}
