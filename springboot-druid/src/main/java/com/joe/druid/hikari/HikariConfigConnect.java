package com.joe.druid.hikari;

import com.joe.druid.pojo.TransferDataSource;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.util.Properties;

public class HikariConfigConnect {

    public static Connection GetConnect(TransferDataSource transferDataSource) {
        Connection con=null;
        try {
            HikariDataSource config = new HikariDataSource();
            config = new HikariDataSource();
            config.setJdbcUrl(transferDataSource.getAddress());
            config.setDriverClassName(transferDataSource.getDriver());
            config.setUsername(transferDataSource.getUserName());
            config.setPassword(transferDataSource.getPassWord());
            config.setMinimumIdle(5);
            config.setMaximumPoolSize(15);
            config.setAutoCommit(true);
            config.setIdleTimeout(3000);
            config.setPoolName("DatebookHikariCP");
            config.setMaxLifetime(1800000);
            config.setConnectionTimeout(30000);
            //config.setConnectionTestQuery("SELECT 1");
            config.addDataSourceProperty("nullCatalogMeansCurrent", true);
            //解决oracle读取不到REMARKS表注解设置
            Properties properties = new Properties();
            properties.setProperty("remarks", "true");
            properties.setProperty("useInformationSchema", "true");
            config.setDataSourceProperties(properties);

            //分配连接
            con = config.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

}