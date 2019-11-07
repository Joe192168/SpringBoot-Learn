package com.joe.shardingjdbc;

import com.joe.shardingjdbc.datasrouce.DBUtil;
import com.joe.shardingjdbc.entity.NewDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ShardingJdbcApplicationTests {

    @Test
    void contextLoads() {
        List<NewDataSource> dataSourceList = new ArrayList<>();
        NewDataSource newDataSource = new NewDataSource();
        newDataSource.setDataDbName("test");
        newDataSource.setDataIp("localhost");
        newDataSource.setDataCode("3306");
        newDataSource.setDataUserName("root");
        newDataSource.setDataUserPassword("root");
        dataSourceList.add(newDataSource);
        String sql = "select * from t_user";
        List<Map<String, Object>> mapList= DBUtil.getConnection(dataSourceList,sql);

        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNumber(2);
        pageRequest.setPageSize(1);

        Page<Map<String,Object>> pageInfo = DBUtil.getPage(dataSourceList,sql,pageRequest);

        List<Map<String,Object>> mapList1 = pageInfo.getContent();

        for (int i = 0; i < mapList1.size(); i++) {
            Map<String,Object> map = mapList1.get(i);
            System.out.println(map.toString());
        }

    }

}
