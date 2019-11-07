package com.joe.shardingjdbc.util;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

public class DataSourceUtils {

    /**
     * 创建数据源对象
     */
    public static DataSource createDataSource(final String dataSourceName) {
        DruidDataSource dataSource = new DruidDataSource();
        String jdbcUrl =String.format("jdbc:mysql://localhost:3306/%s?useSSL=false&useUnicode=true&characterEncoding=UTF-8",dataSourceName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        // 数据源其它配置（略）
        return dataSource;
    }
}