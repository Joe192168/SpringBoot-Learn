package com.joe.axis.wsdl;

import net.sf.json.JSONArray;
import com.joe.axis.vo.OfficeProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service("springWebService")
public class SpringWebService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String springHello(String name) {
        return "hello " + name;
    }

    public String getAll() {
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
        // 因为我这里需要给WS接口发送的数据是[{"pk_corp":pk_corp},{}...]
        JSONArray  json  =  JSONArray.fromObject(list);
        return json.toString();
    }

}