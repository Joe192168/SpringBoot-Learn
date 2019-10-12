package com.cxf.webservice.controller;

import com.cxf.webservice.model.OfficeProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class testController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("getAll")
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