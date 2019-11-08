package com.joe.shardingjdbc.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.joe.shardingjdbc.bean.NewDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceUtils {

    /**
     * 创建数据源对象
     */
    public static DataSource createDataSource(NewDataSource newDataSource) {
        DruidDataSource dataSource = new DruidDataSource();
        String jdbcUrl =String.format(getDbtypeByDriver(newDataSource.getDataType()),newDataSource.getDataIp(),newDataSource.getDataPort(),newDataSource.getDataName());
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(newDataSource.getUsername());
        dataSource.setPassword(newDataSource.getPassword());
        //解决oracle读取不到REMARKS表注解设置
        Properties properties = new Properties();
        properties.setProperty("remarks", "true");
        properties.setProperty("useInformationSchema", "true");
        dataSource.setConnectProperties(properties);
        //配置初始化大小、最小、最大
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);
        //连接泄漏监测
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(30);
        //配置获取连接等待超时的时间
        dataSource.setMaxWait(20000);
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(20000);
        return dataSource;
    }

    /**
     * 根据数据库类型得到对应的驱动
     * @param _dbType
     * @return
     */
    public static String getDbtypeByDriver(String _dbType) {
        String _driver = "";
        switch (_dbType) {
            case "oracle":
                _driver = "jdbc:oracle:thin:@%s:%s:%s";
                break;
            case "mysql":
                _driver =  "jdbc:mysql://%s:%s/%s?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
                break;
            case "postgresql":
                _driver =  "jdbc:postgresql://%s:%s/%s";
                break;
            case "sqlserver":
                _driver =  "jdbc:sqlserver://%s:%s;databaseName=%s";
                break;
            case "db2":
                _driver =  "jdbc:db2://%s:%s/%s";
                break;
            default:
                return null;
        }
        return _driver;
    }

}